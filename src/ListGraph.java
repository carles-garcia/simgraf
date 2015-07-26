import java.util.*;

public abstract class ListGraph<V, E extends Edge<V>> implements InterfaceGraph<V,E> {
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
    private HashMap<V, HashSet<E>> edgeList;
    private int size; // number of edges

    /*
    If the vertex isn't contained in the graph (and this is true for a null reference) throws
    an IllegalArgumenException
     */
    private void checkContained(V vertex) {
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
     * Add vertex to the graph
     * @param vertex Must not be null. Must not be already contained in the graph.
     */
    public void add(V vertex) {
        // Null vertices can't be added, therefore a graph will never contain null vertices
        Objects.requireNonNull(vertex, "Tried to add a null vertex");
        if (!edgeList.containsKey(vertex)) edgeList.put(vertex, new HashSet<E>());
        else throw new IllegalArgumentException("The vertex is already contained in the graph");
    }

    /**
     * Remove vertex and its incident edges from the graph
     * @param vertex has to be contained in the graph
     */
    public void remove(V vertex) {
        for (E edge : Objects.requireNonNull(edgeList.get(vertex), "Tried to remove a vertex not contained in the graph")) {
            edgeList.get
                    (edge.getVertex1().equals(vertex) ? edge.getVertex2() : edge.getVertex1()).remove(edge);
        }
        edgeList.remove(vertex);
    }

    /**
     * Add an edge to the graph.
     * @param edge Must not be null. Its endpoints have to be contained in the graph and can't be already adjacent.
     */
    public void add(E edge) {
        Objects.requireNonNull(edge, "Tried to add a null edge");
        // in a simple graph, here would be the check for loops
        if (!areAdjacent(edge.getVertex1(), edge.getVertex2())) {
            edgeList.get(edge.getVertex1()).add(edge);
            edgeList.get(edge.getVertex2()).add(edge);
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
        if (!areAdjacent(edge.getVertex1(), edge.getVertex2())
                | !edgeList.get(edge.getVertex1()).remove(edge)
                | !edgeList.get(edge.getVertex2()).remove(edge))
            throw new IllegalArgumentException("Tried to remove an edge not contained in the graph");
        --size;
    }

    /**
     * Get edge between two adjacent vertices
     * @param vertex1 Must not be null.
     * @param vertex2 Must not be null.
     * @return edge between vertex1 and vertex2.
     */
    public E getEdge(V vertex1, V vertex2) {
        if (!contains(vertex1)) throw new IllegalArgumentException("vertex1 is not contained in the graph");
        if (!contains(vertex2)) throw new IllegalArgumentException("vertex2 is not contained in the graph");
        for (E edge : edgeList.get(vertex1)) {
            if (edge.getVertex1().equals(vertex1) ?
                    edge.getVertex2().equals(vertex2) : edge.getVertex1().equals(vertex2))
                return edge;
        }
        throw new IllegalArgumentException("vertex1 & vertex2 are not adjacent");
    }

    /**
     * Returns true if two vertices are adjacent
     * @param vertex1 Has to be contained in the graph.
     * @param vertex2 Has to be contained in the graph.
     * @return True if vertex1 and vertex2 are adjacent.
     */
    public boolean areAdjacent(V vertex1, V vertex2) {
        /*
        This method checks if the vertices are contained in the graph, therefore any method using this one
        doesn't need to check it again.
         */
        // Should be optimised considering which vertex has less edges
        if (!contains(vertex1)) throw new IllegalArgumentException("vertex1 is not contained in the graph");
        else if (!contains(vertex2)) throw new IllegalArgumentException("vertex2 is not contained in the graph");
        for (E edge : edgeList.get(vertex1)) {
            if (edge.getVertex1().equals(vertex1) ?
                    edge.getVertex2().equals(vertex2) : edge.getVertex1().equals(vertex2))
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
            hs.add(edge.getVertex1().equals(vertex) ? edge.getVertex2() : edge.getVertex1());
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
