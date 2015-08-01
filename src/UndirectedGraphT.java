import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class UndirectedGraphT<V, E extends Edge<V>> extends AbstractGraphT<V,E> {
    private HashMap<V, HashMap<V,EdgeList>> list;
    private int size;

    private class EdgeList {
        HashSet<E> edges;
        int loops;

        EdgeList() {
            edges = new HashSet<>();
            loops = 0;
        }
    }

    public UndirectedGraphT(boolean loopsAllowed, boolean multigraph) {
        super(loopsAllowed,multigraph);
        list = new HashMap<>();
        size = 0;
    }

    public void add(V vertex) {
        Objects.requireNonNull(vertex, Errors.ADD_NULL_VERTEX.toString());
        if (!list.containsKey(vertex)) list.put(vertex, new HashMap<V, EdgeList>());
        else throw new IllegalArgumentException(Errors.VERTEX_CONTAINED.toString());
    }

    public void remove(V vertex) {
        Objects.requireNonNull(vertex, Errors.VERTEX_NOT_CONTAINED.toString());
        for (V adjV : list.get(vertex).keySet()) {
            list.get(adjV).remove(vertex);
        }
        list.remove(vertex);
    }

    public void add(E edge) {
        Objects.requireNonNull(edge, Errors.ADD_NULL_EDGE.toString());
        boolean loop = edge.getVertexA().equals(edge.getVertexB());
        if (!loopsAllowed() && loop)
            throw new IllegalArgumentException(Errors.LOOPS_NOT_ALLOWED.toString());

        boolean adjacent = areAdjacent(edge.getVertexA(), edge.getVertexB());
        if (isMultigraph() || !adjacent) {
            if (!adjacent) {
                list.get(edge.getVertexA()).put(edge.getVertexB(),new EdgeList());
                list.get(edge.getVertexB()).put(edge.getVertexA(),new EdgeList());
            }
            if (!list.get(edge.getVertexA()).get(edge.getVertexB()).edges.add(edge))
                throw new IllegalArgumentException(Errors.EDGE_CONTAINED.toString());
            list.get(edge.getVertexB()).get(edge.getVertexA()).edges.add(edge);
            if (loop) ++list.get(edge.getVertexA()).get(edge.getVertexB()).loops;
            ++size;
        }
        else throw new IllegalArgumentException(Errors.ADD_EXISTING_EDGE.toString());
    }
}
