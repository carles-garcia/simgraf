import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class DirectedGraphT<V, E extends Edge<V>> extends AbstractGraphT<V,E> {
    private HashMap<V, HashMap<V,EdgeList>> list;
    private HashMap<V, AtomicInteger> indegrees; //atomic to update efficiently
    private HashMap<V, AtomicInteger> outdegrees; //atomic to update efficiently
    private int size; // number of edges


    private class EdgeList {
        HashSet<E> incoming;
        HashSet<E> outgoing;

        EdgeList() {
            incoming = new HashSet<>();
            outgoing = new HashSet<>();
        }
    }

    /*
        If the vertex isn't contained in the graph (and this is true for a null reference) throws
        an IllegalArgumenException
         */
    private void checkContained(V vertex) {
        if (!contains(vertex)) throw new IllegalArgumentException(Errors.VERTEX_NOT_CONTAINED.toString());
    }

    public DirectedGraphT(boolean loopsAllowed, boolean multigraph) {
        super(loopsAllowed,multigraph);
        list = new HashMap<>();
        indegrees = new HashMap<>();
        outdegrees = new HashMap<>();
        size = 0;
    }

    public void add(V vertex) {
        Objects.requireNonNull(vertex, Errors.ADD_NULL_VERTEX.toString());
        if (!list.containsKey(vertex)) {
            list.put(vertex, new HashMap<V,EdgeList>());
            indegrees.put(vertex, new AtomicInteger());
            outdegrees.put(vertex, new AtomicInteger());
        }
        else throw new IllegalArgumentException(Errors.VERTEX_CONTAINED.toString());
    }

    public void remove(V vertex) {
        Objects.requireNonNull(vertex, Errors.VERTEX_NOT_CONTAINED.toString());
        for (V adjV : list.get(vertex).keySet()) {
            indegrees.get(adjV).getAndAdd(-list.get(adjV).get(vertex).incoming.size());
            outdegrees.get(adjV).getAndAdd(-list.get(adjV).get(vertex).outgoing.size());
            list.get(adjV).remove(vertex);
        }
        list.remove(vertex);
    }

    public void add(E edge) {
        Objects.requireNonNull(edge, Errors.ADD_NULL_EDGE.toString());
        if (!loopsAllowed() && edge.getVertexA().equals(edge.getVertexB()))
            throw new IllegalArgumentException(Errors.LOOPS_NOT_ALLOWED.toString());

        boolean adjacent = areAdjacent(edge.getVertexA(), edge.getVertexB());
        if (isMultigraph() || !adjacent) {
            if (!adjacent) {
                list.get(edge.getVertexA()).put(edge.getVertexB(),new EdgeList());
                list.get(edge.getVertexB()).put(edge.getVertexA(),new EdgeList());
            }
            if (!list.get(edge.getVertexA()).get(edge.getVertexB()).outgoing.add(edge))
                throw new IllegalArgumentException(Errors.EDGE_CONTAINED.toString());
            list.get(edge.getVertexB()).get(edge.getVertexA()).incoming.add(edge);
            ++size;

            outdegrees.get(edge.getVertexA()).getAndIncrement();
            indegrees.get(edge.getVertexB()).getAndIncrement();
        }
        else throw new IllegalArgumentException(Errors.ADD_EXISTING_EDGE.toString());
    }

    public void remove(E edge) {
        // must update adjacencies
        Objects.requireNonNull(edge, Errors.REMOVE_NULL_EDGE.toString());
        if (!areAdjacent(edge.getVertexA(), edge.getVertexB()))
            throw new IllegalArgumentException(Errors.EDGE_NOT_CONTAINED.toString());

        if (!list.get(edge.getVertexA()).get(edge.getVertexB()).outgoing.remove(edge))
            throw new IllegalArgumentException(Errors.EDGE_NOT_CONTAINED.toString());
        if (list.get(edge.getVertexA()).get(edge.getVertexB()).outgoing.isEmpty()
                && list.get(edge.getVertexA()).get(edge.getVertexB()).incoming.isEmpty()) {
            list.get(edge.getVertexA()).remove(edge.getVertexB());
            list.get(edge.getVertexB()).remove(edge.getVertexA());
        }
        else list.get(edge.getVertexB()).get(edge.getVertexA()).incoming.remove(edge);

        --size;

        outdegrees.get(edge.getVertexA()).getAndDecrement();
        indegrees.get(edge.getVertexB()).getAndDecrement();
    }


}
