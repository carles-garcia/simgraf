import java.util.Collection;

public interface InterfaceDirectedGraph<V, E extends Arc<V>> extends InterfaceGraph<V,E> {

    int indegree(V vertex);
    int outdegree(V vertex);
    Collection<E> getIncomingArcs(V vertex);
    Collection<E> getOutgoingArcs(V vertex);
}
