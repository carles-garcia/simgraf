package graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class UndirectedGraph<V, E extends Edge<V>> extends AbstractGraph<V,E> {
    /*
    list contains the vertices as keys. The values are HashMaps that have the adjacent vertices as keys
    and the sets of edges between them as values.
     */
    private HashMap<V, HashMap<V,HashSet<E>>> list;
    private HashMap<V, AtomicInteger> degrees; //atomic to update efficiently
    private int size;

    /* If the vertex isn't contained in the graph (and this is true for a null reference) throws
    an IllegalArgumentException
     */
    private void checkContained(V vertex) {
        if (!contains(vertex)) throw new IllegalArgumentException(Errors.VERTEX_NOT_CONTAINED.toString());
    }

    /* PUBLIC METHODS */

    /**
     * Constructor for the undirected graph.
     * @param loopsAllowed True if loops are allowed
     * @param multigraph True if multiple edges between two vertices are allowed
     */
    public UndirectedGraph(boolean loopsAllowed, boolean multigraph) {
        super(loopsAllowed,multigraph);
        list = new HashMap<>();
        degrees = new HashMap<>();
        size = 0;
    }

    /* Overrides */

    public void add(V vertex) {
        Objects.requireNonNull(vertex, Errors.ADD_NULL_VERTEX.toString());
        if (!list.containsKey(vertex)) {
            list.put(vertex, new HashMap<V, HashSet<E>>());
            degrees.put(vertex, new AtomicInteger());
        }
        else throw new IllegalArgumentException(Errors.VERTEX_CONTAINED.toString());
    }

    public void remove(V vertex) {
        Objects.requireNonNull(vertex, Errors.VERTEX_NOT_CONTAINED.toString());
        for (V adjV : list.get(vertex).keySet()) {
            degrees.get(adjV).getAndAdd(-list.get(adjV).get(vertex).size());
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
                list.get(edge.getVertexA()).put(edge.getVertexB(),new HashSet<E>());
                list.get(edge.getVertexB()).put(edge.getVertexA(),new HashSet<E>());
            }
            if (!list.get(edge.getVertexA()).get(edge.getVertexB()).add(edge))
                throw new IllegalArgumentException(Errors.EDGE_CONTAINED.toString());
            list.get(edge.getVertexB()).get(edge.getVertexA()).add(edge);
            ++size;

            degrees.get(edge.getVertexA()).getAndIncrement();
            degrees.get(edge.getVertexB()).getAndIncrement();
        }
        else throw new IllegalArgumentException(Errors.ADD_EXISTING_EDGE.toString());
    }

    public void remove(E edge) {

        Objects.requireNonNull(edge, Errors.REMOVE_NULL_EDGE.toString());
        if (!areAdjacent(edge.getVertexA(), edge.getVertexB()))
            throw new IllegalArgumentException(Errors.EDGE_NOT_CONTAINED.toString());

        if (!list.get(edge.getVertexA()).get(edge.getVertexB()).remove(edge))
            throw new IllegalArgumentException(Errors.EDGE_NOT_CONTAINED.toString());
        // If it's empty, then vertexB is also empty.
        if (list.get(edge.getVertexA()).get(edge.getVertexB()).isEmpty()) {
            list.get(edge.getVertexA()).remove(edge.getVertexB());
            list.get(edge.getVertexB()).remove(edge.getVertexA());
        }
        else list.get(edge.getVertexB()).get(edge.getVertexA()).remove(edge);

        --size;

        degrees.get(edge.getVertexA()).getAndDecrement();
        degrees.get(edge.getVertexB()).getAndDecrement();
    }

    public E getEdge(V vertexA, V vertexB) {
        if (!areAdjacent(vertexA,vertexB))
            throw new IllegalArgumentException(Errors.NOT_ADJACENT.toString());
        return list.get(vertexA).get(vertexB).iterator().next();
    }

    public boolean areAdjacent(V vertexA, V vertexB) {
        checkContained(vertexA);
        checkContained(vertexB);
        return list.get(vertexA).containsKey(vertexB);
    }

    public boolean contains(V vertex) {
        return list.containsKey(vertex);
    }

    public Set<V> getVertices() {
        return list.keySet();
    }

    public Set<E> getEdges() {
        HashSet<E> hs = new HashSet<>();
        for (HashMap<V,HashSet<E>> hm : list.values()) {
            for (HashSet<E> hse : hm.values()) {
                hs.addAll(hse);
            }
        }
        return hs;
    }

    public Set<V> getNeighbours(V vertex) {
        checkContained(vertex);
        return list.get(vertex).keySet();
    }


    public int order() {
        return list.size();
    }

    public int size() {
        return size;
    }


    /* Original methods */

    /**
     * Returns the degree of a vertex (number of incident edges. Loops count as two)
     * @param vertex to get degree of
     * @return integer indicating the degree
     */
    public int degree(V vertex) {
        checkContained(vertex);
        return degrees.get(vertex).get();
    }

    /**
     * Get all incident edges to the vertex
     * @param vertex to get incident edges
     * @return set containing all incident edges to the vertex
     */
    public Set<E> getEdges(V vertex) {
        checkContained(vertex);
        HashSet<E> hs = new HashSet<>();
        for (HashSet<E> hse : list.get(vertex).values()) {
            hs.addAll(hse);
        }
        return hs;
    }

    /**
     * Get all edges between two vertices
     * @param vertexA first vertex
     * @param vertexB second vertex
     * @return set containing all edges between vertexA and vertexB
     */
    public Set<E> getEdges(V vertexA, V vertexB) {
        checkContained(vertexA);
        checkContained(vertexB);
        return list.get(vertexA).get(vertexB);
    }

    /**
     * Number of edges between two vertices
     * @param vertexA first vertex
     * @param vertexB second vertex
     * @return integer indicating number of edges between vertexA and vertexB
     */
    public int numberOfEdges(V vertexA, V vertexB) {
        checkContained(vertexA);
        checkContained(vertexB);
        return list.get(vertexA).get(vertexB).size();
    }

}
