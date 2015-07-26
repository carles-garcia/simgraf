import java.util.Collection;

public interface InterfaceGraph<V, E extends Edge<V>> {
    /**
     * Add vertex to the graph
     * @param vertex Must not be null. Must not be already contained in the graph.
     */
    void add(V vertex);

    /**
     * Remove vertex and its incident edges from the graph
     * @param vertex has to be contained in the graph
     */
    void remove(V vertex);

    /**
     * Add an edge to the graph.
     * @param edge Must not be null. Its endpoints have to be contained in the graph and can't be already adjacent.
     */
    void add(E edge);
    void remove(E edge);
    E getEdge(V vertexA, V vertexB);

    boolean areAdjacent(V vertexA, V vertexB);
    boolean contains(V vertex);

    Collection<V> getVertices();
    Collection<E> getEdges();

    int degree(V vertex);

    Collection<V> getNeighbours(V vertex);
    Collection<E> getEdges(V vertex);

    int order(); // number of vertices
    int size(); // number of edges
}
