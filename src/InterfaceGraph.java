import java.util.Collection;

public interface InterfaceGraph<V, E extends Edge<V>> {
    void add(V vertex);
    void remove(V Vertex);

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
