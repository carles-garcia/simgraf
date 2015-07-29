import java.util.Collection;

public interface InterfaceMultigraph<V, E extends Edge<V>> extends InterfaceGraph<V, E> {

    /**
     * Add an edge to the graph.
     * @param edge Must not be null. Its endpoints have to be contained in the graph.
     */
    void add(E edge);

    /**
     * Get all edges between two vertices
     * @param vertexA Has to be contained in the graph.
     * @param vertexB Has to be contained in the graph.
     * @return collection containing all edges between vertexA and vertexB
     */
    Collection<E> getEdges(V vertexA, V vertexB);

    /**
     * Number of edges between two vertices
     * @param vertexA Has to be contained in the graph.
     * @param vertexB Has to be contained in the graph.
     * @return int number of edges between vertexA and vertexB
     */
    int numberOfEdges(V vertexA, V vertexB);

}
