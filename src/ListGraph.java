import java.util.*;

public class ListGraph<V, E extends Edge<V>> implements InterfaceGraph<V,E> {
    /*
    Incidence list representation.
    Order of insertion of vertices is not saved

    Analysis of memory cost:
    (1 + 2*degree) references to each vertex
    A graph with 100 vertices would contain at least 100 references

    An implementation with adjacency list:
    (|V| + 2*degree) references to each vertex
    A graph with 100 vertices would contain at least 100.000 references

     */
    protected HashMap<V, HashSet<E>> edgeList;
    protected int size; // number of edges

    /*
    If the vertex isn't contained in the graph (and this is true for a null reference) throws
    an IllegalArgumenException
     */
    protected void checkContained(V vertex) {
        if (!contains(vertex)) throw new IllegalArgumentException("The vertex is not contained in the graph");
    }

    /**
     * Default constructor
     */
    public ListGraph() {
        edgeList = new HashMap<>();
        size = 0;
    }

    /**
     * {@inheritDoc}
     */
    public void add(V vertex) {
        // Null vertices can't be added, therefore a graph will never contain null vertices
        Objects.requireNonNull(vertex, "Tried to add a null vertex");
        if (!edgeList.containsKey(vertex)) edgeList.put(vertex, new HashSet<E>());
        else throw new IllegalArgumentException("The vertex is already contained in the graph");
    }

    /**
     * {@inheritDoc}
     */
    public void remove(V vertex) {
        for (E edge : Objects.requireNonNull(edgeList.get(vertex), "Tried to remove a vertex not contained in the graph")) {
            edgeList.get
                    (edge.getVertexA().equals(vertex) ? edge.getVertexB() : edge.getVertexA()).remove(edge);
        }
        edgeList.remove(vertex);
    }


    public void add(E edge) {
        Objects.requireNonNull(edge, "Tried to add a null edge");
        // in a simple graph, here would be the check for loops
        if (!areAdjacent(edge.getVertexA(), edge.getVertexB())) {
            edgeList.get(edge.getVertexA()).add(edge);
            edgeList.get(edge.getVertexB()).add(edge);
            ++size;
        }
        else throw new IllegalArgumentException("There is already an edge between the parameter's endpoints");
    }

    /**
     * Remove edge from the graph
     * @param edge Must not be null. Has to be contained in the graph.
     */
    public void remove(E edge) {
        Objects.requireNonNull(edge, "Tried to remove a null edge");
        if (!areAdjacent(edge.getVertexA(), edge.getVertexB())
                | !edgeList.get(edge.getVertexA()).remove(edge)
                | !edgeList.get(edge.getVertexB()).remove(edge))
            throw new IllegalArgumentException("Tried to remove an edge not contained in the graph");
        --size;
    }

    /**
     * Get edge between two adjacent vertices
     * @param vertexA Must not be null.
     * @param vertexB Must not be null.
     * @return edge between vertexA and vertexB.
     */
    public E getEdge(V vertexA, V vertexB) {
        checkContained(vertexA);
        checkContained(vertexB);
        for (E edge : edgeList.get(vertexA)) {
            if (edge.getVertexA().equals(vertexA) ?
                    edge.getVertexB().equals(vertexB) : edge.getVertexA().equals(vertexB))
                return edge;
        }
        throw new IllegalArgumentException("vertexA & vertexB are not adjacent");
    }

    /**
     * Returns true if two vertices are adjacent
     * @param vertexA Has to be contained in the graph.
     * @param vertexB Has to be contained in the graph.
     * @return True if vertexA and vertexB are adjacent.
     */
    public boolean areAdjacent(V vertexA, V vertexB) {
        /*
        This method checks if the vertices are contained in the graph, therefore any method using this one
        doesn't need to check it again.
         */
        checkContained(vertexA);
        checkContained(vertexB);
        // Should be optimised considering which vertex has less edges
        for (E edge : edgeList.get(vertexA)) {
            if (edge.getVertexA().equals(vertexA) ?
                    edge.getVertexB().equals(vertexB) : edge.getVertexA().equals(vertexB))
                return true;
        }
        return false;
    }

    /**
     * Returns true if the vertex is contained in the graph
     * @param vertex vertex to test
     * @return True if found
     */
    public boolean contains(V vertex) {
        return edgeList.containsKey(vertex);
    }

    /**
     * Get all vertices in the graph
     * @return set containing all vertices
     */
    public Set<V> getVertices() {
        // Should specify about order
        return edgeList.keySet();
    }

    /**
     * Get all edges in the graph
     * @return set containing all edges
     */
    public Set<E> getEdges() {
        HashSet<E> edges = new HashSet<>();
        for (HashSet<E> s: edgeList.values()) {
            for (E edge : s) {
                edges.add(edge);
            }
        }
        return edges;
    }

    /**
     * Get degree of a vertex (number of incident edges).
     * @param vertex Has to be contained in the graph.
     * @return degree
     */
    public int degree(V vertex) {
        checkContained(vertex);
        return edgeList.get(vertex).size();
    }

    /**
     * Get neighbours of a vertex (adjacent vertices)
     * @param vertex Has to be contained in the graph.
     * @return set containing neighbours
     */
    public Set<V> getNeighbours(V vertex) {
        checkContained(vertex);
        HashSet<V> hs = new HashSet<>();
        for (E edge: edgeList.get(vertex))
            hs.add(edge.getVertexA().equals(vertex) ? edge.getVertexB() : edge.getVertexA());
        return hs;
    }

    /**
     * Get incident edges to a vertex
     * @param vertex Has to be contained in the graph.
     * @return set containing the edges
     */
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

}
