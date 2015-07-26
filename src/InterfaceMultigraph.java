import java.util.Collection;

public interface InterfaceMultigraph<V, E extends Edge<V>> extends InterfaceGraph<V, E> {
    Collection<E> getEdges(V vertex1, V vertex2);
    int numberOfEdges(V vertex1, V vertex2);

}
