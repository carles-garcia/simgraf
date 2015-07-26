import java.util.Collection;

public interface InterfaceMultigraph<V, E extends Edge<V>> extends InterfaceGraph<V, E> {
    Collection<E> getEdges(V vertexA, V vertexB);
    int numberOfEdges(V vertexA, V vertexB);

}
