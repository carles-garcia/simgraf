import java.util.Collection;

public interface Graph<V, E extends Edge<V>> {
    void add(V vertex);
    void remove(V Vertex);

    void add(E edge);
    void remove(E edge);
    E getEdge(V vertex1, V vertex2);

    boolean areAdjacent(V vertex1, V vertex2);
    boolean contains(V vertex);

    Collection<V> getVertices();
    Collection<E> getEdges();

    int degree(V vertex);

    Collection<V> getNeighbours(V vertex);
    Collection<E> getEdges(V vertex);
}
