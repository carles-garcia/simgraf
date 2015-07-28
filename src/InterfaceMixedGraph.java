import java.util.Collection;

public interface InterfaceMixedGraph<V, E extends AbstractEdge<V>>
        extends InterfaceGraph<V,E> {
    int indegree(V vertex);
    int outdegree(V vertex);
    Collection<E> getIncomingArcs(V vertex);
    Collection<E> getOutgoingArcs(V vertex);
}
