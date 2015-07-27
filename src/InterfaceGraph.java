import java.util.Collection;
import java.util.Set;

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

    /**
     * Remove edge from the graph
     * @param edge Must not be null. Has to be contained in the graph.
     */
    void remove(E edge);

    /**
     * Get an edge between two adjacent vertices
     * @param vertexA Must not be null.
     * @param vertexB Must not be null.
     * @return edge between vertexA and vertexB.
     */
    E getEdge(V vertexA, V vertexB);

    /**
     * Returns true if two vertices are adjacent
     * (if there is an edge between two vertices)
     * @param vertexA Has to be contained in the graph.
     * @param vertexB Has to be contained in the graph.
     * @return True if vertexA and vertexB are adjacent.
     */
    boolean areAdjacent(V vertexA, V vertexB);

    /**
     * Returns true if the vertex is contained in the graph
     * @param vertex vertex to test
     * @return True if found
     */
    boolean contains(V vertex);

    /**
     * Get all vertices in the graph
     * @return set containing all vertices
     */
    Set<V> getVertices();

    /**
     * Get all edges in the graph
     * @return set containing all edges
     */
    Set<E> getEdges();

    /**
     * Get degree of a vertex (number of incident edges).
     * @param vertex Has to be contained in the graph.
     * @return int degree
     */
    int degree(V vertex);

    /**
     * Get neighbours of a vertex (adjacent vertices)
     * @param vertex Has to be contained in the graph.
     * @return set containing neighbours
     */
    Set<V> getNeighbours(V vertex);

    /**
     * Get incident edges to a vertex
     * @param vertex Has to be contained in the graph.
     * @return set containing the edges
     */
    Set<E> getEdges(V vertex);

    int order(); // number of vertices
    int size(); // number of edges
}
