import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class DirectedGraphS<V, E extends Arc<V>>
        extends AbstractGraphS<V,E> implements InterfaceDirectedGraph<V,E> {
    public int indegree(V vertex) {
        int ind = 0;
        for (E edge : edgeList.get(vertex)) {
            if (edge.getVertexB().equals(vertex)) ++ind;
        }
        return ind;
    }

    public int outdegree(V vertex) {
        int out = 0;
        for (E edge : edgeList.get(vertex)) {
            if (edge.getVertexA().equals(vertex)) ++out;
        }
        return out;
    }

    public Set<E> getIncomingArcs(V vertex) {
        HashSet<E> hs = new HashSet<>();
        for (E edge : edgeList.get(vertex)) {
            if (edge.getVertexB().equals(vertex))
                hs.add(edge);
        }
        return hs;
    }
    public Set<E> getOutgoingArcs(V vertex) {
        HashSet<E> hs = new HashSet<>();
        for (E edge : edgeList.get(vertex)) {
            if (edge.getVertexA().equals(vertex))
                hs.add(edge);
        }
        return hs;
    }
}
