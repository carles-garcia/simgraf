import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class UndirectedGraphT<V, E extends Edge<V>> extends AbstractGraphT<V,E> {
    /*
    vertices contains the vertices as keys. The values are HashMaps that have the adjacent vertices as keys
    and the edge lists as values.
     */
    private HashMap<V, VertexInfo> vertices;
    //private HashMap<V, AtomicInteger> degrees; //atomic to update efficiently
    private int size;

    private class VertexInfo {
        private HashMap<V,HashSet<E>> list;
        HashSet<E> loops;
        private int degree;

        VertexInfo() {
            list = new HashMap<>();
            if (loopsAllowed()) loops = new HashSet<>();
            degree = 0;
        }
    }

    /*
        If the vertex isn't contained in the graph (and this is true for a null reference) throws
        an IllegalArgumenException
         */
    private void checkContained(V vertex) {
        if (!contains(vertex)) throw new IllegalArgumentException(Errors.VERTEX_NOT_CONTAINED.toString());
    }

    public UndirectedGraphT(boolean loopsAllowed, boolean multigraph) {
        super(loopsAllowed,multigraph);
        vertices = new HashMap<>();
        size = 0;
    }

    public void add(V vertex) {
        Objects.requireNonNull(vertex, Errors.ADD_NULL_VERTEX.toString());
        if (!vertices.containsKey(vertex)) vertices.put(vertex, new VertexInfo());
        else throw new IllegalArgumentException(Errors.VERTEX_CONTAINED.toString());
    }

    public void remove(V vertex) {
        Objects.requireNonNull(vertex, Errors.VERTEX_NOT_CONTAINED.toString());
        for (V adjV : vertices.get(vertex).list.keySet()) {
            vertices.get(adjV).degree -= vertices.get(adjV).list.get(vertex).size();
            vertices.get(adjV).list.remove(vertex);
        }
        vertices.remove(vertex);
    }

    public void add(E edge) {
        Objects.requireNonNull(edge, Errors.ADD_NULL_EDGE.toString());
        if (!loopsAllowed() && edge.getVertexA().equals(edge.getVertexB()))
            throw new IllegalArgumentException(Errors.LOOPS_NOT_ALLOWED.toString());

        boolean adjacent = areAdjacent(edge.getVertexA(), edge.getVertexB());
        if (isMultigraph() || !adjacent) {
            if (!adjacent) {
                vertices.get(edge.getVertexA()).put(edge.getVertexB(),new HashSet<E>());
                vertices.get(edge.getVertexB()).put(edge.getVertexA(),new HashSet<E>());
            }
            if (!vertices.get(edge.getVertexA()).get(edge.getVertexB()).add(edge))
                throw new IllegalArgumentException(Errors.EDGE_CONTAINED.toString());
            vertices.get(edge.getVertexB()).get(edge.getVertexA()).add(edge);
            ++size;

            ++degrees.get(edge.getVertexA());
            ++
        }
        else throw new IllegalArgumentException(Errors.ADD_EXISTING_EDGE.toString());
    }

    public void remove(E edge) {
        // must update adjacencies
        Objects.requireNonNull(edge, Errors.REMOVE_NULL_EDGE.toString());
        if (!areAdjacent(edge.getVertexA(), edge.getVertexB()))
            throw new IllegalArgumentException(Errors.EDGE_NOT_CONTAINED.toString());

        if (!vertices.get(edge.getVertexA()).get(edge.getVertexB()).remove(edge))
            throw new IllegalArgumentException(Errors.EDGE_NOT_CONTAINED.toString());
        if (vertices.get(edge.getVertexA()).get(edge.getVertexB()).isEmpty())
            vertices.get(edge.getVertexA()).remove(edge.getVertexB());

        vertices.get(edge.getVertexB()).get(edge.getVertexA()).remove(edge);
        if (vertices.get(edge.getVertexB()).get(edge.getVertexA()).isEmpty())
            vertices.get(edge.getVertexB()).remove(edge.getVertexA());

        --size;
    }

    public E getEdge(V vertexA, V vertexB) {
        if (!areAdjacent(vertexA,vertexB))
            throw new IllegalArgumentException(Errors.NOT_ADJACENT.toString());
        return vertices.get(vertexA).get(vertexB).iterator().next();
    }

    public boolean areAdjacent(V vertexA, V vertexB) {
        checkContained(vertexA);
        checkContained(vertexB);
        return vertices.get(vertexA).containsKey(vertexB);
    }

    public boolean contains(V vertex) {
        return vertices.containsKey(vertex);
    }

    public Set<E> getEdges() {
        HashSet<E> hs = new HashSet<>();
        for (HashMap<V,HashSet<E>> hm : vertices.values()) {
            for (HashSet<E> hse : hm.values()) {
                hs.addAll(hse);
            }
        }
        return hs;
    }

    // could maintain a variable instead of calculating
    public int degree(V vertex) {
        checkContained(vertex);
        int deg = 0;
        for (HashSet<E> hs : vertices.get(vertex).values()) {
            deg += hs.size();
        }
        // If there are loops count them twice
        HashSet<E> hs;
        if ((hs = vertices.get(vertex).get(vertex)) != null) {
            deg += hs.size();
        }
        return deg;
    }

    public Set<V> getNeighbours(V vertex) {
        checkContained(vertex);
        return vertices.get(vertex).keySet();
    }

    public Set<E> getEdges(V vertex) {
        checkContained(vertex);
        HashSet<E> hs = new HashSet<>();
        for (HashSet<E> hse : vertices.get(vertex).values()) {
            hs.addAll(hse);
        }
        return hs;
    }

    public int order() {
        return vertices.size();
    }

    public int size() {
        return size;
    }

    public Set<E> getEdges(V vertexA, V vertexB) {
        checkContained(vertexA);
        checkContained(vertexB);
        return vertices.get(vertexA).get(vertexB);
    }

    public int numberOfEdges(V vertexA, V vertexB) {
        checkContained(vertexA);
        checkContained(vertexB);
        return vertices.get(vertexA).get(vertexB).size();
    }

}
