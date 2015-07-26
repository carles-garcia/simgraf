import java.util.Collection;

public interface InterfaceMultigraph<V, E extends Edge<V>> extends InterfaceGraph<V, E> {

    /**
     * Add an edge to the graph.
     * @param edge Must not be null. Its endpoints have to be contained in the graph.
     */
    void add(E edge);

    Collection<E> getEdges(V vertexA, V vertexB);
    int numberOfEdges(V vertexA, V vertexB);

}
