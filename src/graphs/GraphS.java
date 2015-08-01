package graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @param <V>
 * @param <E> Edge class. Has to extend {@link Edge}
 */
public class GraphS<V, E extends Edge<V>> implements InterfaceGraph<V,E> {
    private HashMap<V, HashSet<E>> edgeList;
    private int size; // number of edges
    private EdgeType edgeType;
    private boolean loopsAllowed;
    private boolean multigraph;

    /* PRIVATE METHODS */

    /*
    If the vertex isn't contained in the graph (and this is true for a null reference) throws
    an IllegalArgumenException
     */
    private void checkContained(V vertex) {
        if (!contains(vertex)) throw new IllegalArgumentException(Errors.VERTEX_NOT_CONTAINED.toString());
    }

    /* PUBLIC METHODS */

    /**
     * Graph constructor.
     * @param edgeType Type of edges allowed in the graph. Null if the graph will be mixed.
     * @param loopsAllowed True if loops are allowed
     * @param multigraph True if multiple edges between two vertices are allowed
     */
    public GraphS(EdgeType edgeType, boolean loopsAllowed, boolean multigraph) {
        edgeList = new HashMap<>();
        size = 0;
        this.edgeType = edgeType;
        this.loopsAllowed = loopsAllowed;
        this.multigraph = multigraph;
    }

    //done
    public void add(V vertex) {
        // Null vertices can't be added, therefore a graph will never contain null vertices
        Objects.requireNonNull(vertex, Errors.ADD_NULL_VERTEX.toString());
        if (!edgeList.containsKey(vertex)) edgeList.put(vertex, new HashSet<E>());
        else throw new IllegalArgumentException(Errors.VERTEX_CONTAINED.toString());
    }

    //done
    public void remove(V vertex) {
        for (E edge : Objects.requireNonNull(edgeList.get(vertex), Errors.VERTEX_NOT_CONTAINED.toString())) {
            edgeList.get
                    (edge.getVertexA().equals(vertex) ? edge.getVertexB() : edge.getVertexA()).remove(edge);
        }
        edgeList.remove(vertex);
    }

    // modified depending on subclass
    public void add(E edge) {
        Objects.requireNonNull(edge, Errors.ADD_NULL_EDGE.toString());
        if (edgeType != null && edge.getType() != edgeType)
            throw new IllegalArgumentException(Errors.WRONG_EDGE_TYPE.toString());

        if (!loopsAllowed && edge.getVertexA().equals(edge.getVertexB()))
            throw new IllegalArgumentException(Errors.LOOPS_NOT_ALLOWED.toString());

        if (multigraph || !areAdjacent(edge.getVertexA(), edge.getVertexB())) {
            if (!edgeList.get(edge.getVertexA()).add(edge))
                throw new IllegalArgumentException(Errors.EDGE_CONTAINED.toString());
            edgeList.get(edge.getVertexB()).add(edge);
            ++size;
        }
        else throw new IllegalArgumentException(Errors.ADD_EXISTING_EDGE.toString());
    }

    public void remove(E edge) {
        Objects.requireNonNull(edge, Errors.REMOVE_NULL_EDGE.toString());
        if (!areAdjacent(edge.getVertexA(), edge.getVertexB())
                | !edgeList.get(edge.getVertexA()).remove(edge)
                | !edgeList.get(edge.getVertexB()).remove(edge))
            throw new IllegalArgumentException(Errors.EDGE_NOT_CONTAINED.toString());
        --size;
    }

    public E getEdge(V vertexA, V vertexB) {
        checkContained(vertexA);
        checkContained(vertexB);
        for (E edge : edgeList.get(vertexA)) {
            if (edge.getVertexA().equals(vertexA) ?
                    edge.getVertexB().equals(vertexB) : edge.getVertexA().equals(vertexB))
                return edge;
        }
        throw new IllegalArgumentException(Errors.NOT_ADJACENT.toString());
    }

    public boolean areAdjacent(V vertexA, V vertexB) {
        /*
        This method checks if the vertices are contained in the graph, therefore any method using this one
        doesn't need to check it again.
         */
        checkContained(vertexA);
        checkContained(vertexB);
        // Optimised considering which vertex has less edges
        V lessEdges = edgeList.get(vertexA).size() < edgeList.get(vertexB).size() ? vertexA : vertexB;
        for (E edge : edgeList.get(lessEdges)) {
            if (edge.getVertexA().equals(vertexA) ?
                    edge.getVertexB().equals(vertexB) : edge.getVertexA().equals(vertexB))
                return true;
        }
        return false;
    }

    public boolean contains(V vertex) {
        return edgeList.containsKey(vertex);
    }

    public Set<V> getVertices() {
        // Should specify about order
        return edgeList.keySet();
    }

    public Set<E> getEdges() {
        HashSet<E> edges = new HashSet<>();
        for (HashSet<E> s: edgeList.values()) {
            for (E edge : s) {
                edges.add(edge);
            }
        }
        return edges;
    }

    public int degree(V vertex) {
        checkContained(vertex);
        return edgeList.get(vertex).size();
    }

    public Set<V> getNeighbours(V vertex) {
        checkContained(vertex);
        HashSet<V> hs = new HashSet<>();
        for (E edge: edgeList.get(vertex))
            hs.add(edge.getVertexA().equals(vertex) ? edge.getVertexB() : edge.getVertexA());
        return hs;
    }


    public Set<E> getEdges(V vertex) {
        checkContained(vertex);
        return edgeList.get(vertex);
    }


    public int order() {
        return edgeList.size();
    }


    public int size() {
        return size;
    }

    // todo: should undirected edges be counted
    public int indegree(V vertex) {
        int ind = 0;
        for (E edge : edgeList.get(vertex)) {
            if (edge.getVertexB().equals(vertex)) ++ind;
        }
        return ind;
    }

    public int outdegree(V vertex) {
        int out = 0;
        for (E edge : edgeList.get(vertex)) {
            if (edge.getVertexA().equals(vertex)) ++out;
        }
        return out;
    }

    //optimise depending edgetype
    public Set<E> getIncomingArcs(V vertex) {
        HashSet<E> hs = new HashSet<>();
        for (E edge : edgeList.get(vertex)) {
            if (edge.getVertexB().equals(vertex))
                hs.add(edge);
        }
        return hs;
    }
    public Set<E> getOutgoingArcs(V vertex) {
        HashSet<E> hs = new HashSet<>();
        for (E edge : edgeList.get(vertex)) {
            if (edge.getVertexA().equals(vertex))
                hs.add(edge);
        }
        return hs;
    }

    public Set<E> getEdges(V vertexA, V vertexB) {
        checkContained(vertexA);
        checkContained(vertexB);
        HashSet<E> hs = new HashSet<>();
        for (E edge : edgeList.get(vertexA)) {
            if (edge.getVertexA().equals(vertexA) ?
                    edge.getVertexB().equals(vertexB) : edge.getVertexA().equals(vertexB))
                hs.add(edge);
        }
        return hs;
    }

    public int numberOfEdges(V vertexA, V vertexB) {
        checkContained(vertexA);
        checkContained(vertexB);
        int count = 0;
        for (E edge : edgeList.get(vertexA)) {
            if (edge.getVertexA().equals(vertexA) ?
                    edge.getVertexB().equals(vertexB) : edge.getVertexA().equals(vertexB))
                ++count;
        }
        return count;
    }

}

