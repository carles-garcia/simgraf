import java.util.HashSet;
import java.util.Set;

public class MixedSimpleGraphS<V, E extends AbstractEdge<V>>
        extends AbstractSimpleGraphS<V,E> implements InterfaceMixedGraph<V,E> //maybe its needed a mixed interface
{
    public int indegree(V vertex) {
        int ind = 0;
        for (E edge : edgeList.get(vertex)) {
            if (edge.getEdgeType() == EdgeType.UNDIRECTED) ++ind;
            else if (edge.getVertexB().equals(vertex)) ++ind;
        }
        return ind;
    }

    public int outdegree(V vertex) {
        int out = 0;
        for (E edge : edgeList.get(vertex)) {
            if (edge.getEdgeType() == EdgeType.UNDIRECTED) ++out;
            else if (edge.getVertexA().equals(vertex)) ++out;
        }
        return out;
    }

    // todo: should undirected edges be counted?
    public Set<E> getIncomingArcs(V vertex) {
        HashSet<E> hs = new HashSet<>();
        for (E edge : edgeList.get(vertex)) {
            if (edge.getEdgeType() == EdgeType.DIRECTED
                    && edge.getVertexB().equals(vertex))
                hs.add(edge);
        }
        return hs;
    }
    public Set<E> getOutgoingArcs(V vertex) {
        HashSet<E> hs = new HashSet<>();
        for (E edge : edgeList.get(vertex)) {
            if (edge.getEdgeType() == EdgeType.DIRECTED
                    && edge.getVertexA().equals(vertex))
                hs.add(edge);
        }
        return hs;
    }

}
