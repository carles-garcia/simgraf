import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ListMultigraph<V, E extends Edge<V>>
        extends ListGraph<V,E> implements InterfaceMultigraph<V,E> {


    /**
     * {@inheritDoc}
     */
    public void add(E edge) {
        Objects.requireNonNull(edge, "Tried to add a null edge");
        // in a simple graph, here would be the check for loops
        // Should be optimised considering which vertex has less edges
        checkContained(edge.getVertexA());
        checkContained(edge.getVertexB());
        edgeList.get(edge.getVertexA()).add(edge);
        edgeList.get(edge.getVertexB()).add(edge);
        ++size;
    }


    public Set<E> getEdges(V vertexA, V vertexB) {
        checkContained(vertexA);
        checkContained(vertexB);
        HashSet<E> hs = new HashSet<>();
        for (E edge : edgeList.get(vertexA)) {
            if (edge.getVertexA().equals(vertexA) ?
                    edge.getVertexB().equals(vertexB) : edge.getVertexA().equals(vertexB))
                hs.add(edge);
        }
        return hs;
    }

    /**
     * Numer of edges between two vertices
     * @param vertexA Has to be contained in the graph.
     * @param vertexB Has to be contained in the graph.
     * @return int number of edges between vertexA and vertexB
     */
    public int numberOfEdges(V vertexA, V vertexB) {
        checkContained(vertexA);
        checkContained(vertexB);
        int count = 0;
        for (E edge : edgeList.get(vertexA)) {
            if (edge.getVertexA().equals(vertexA) ?
                    edge.getVertexB().equals(vertexB) : edge.getVertexA().equals(vertexB))
                ++count;
        }
        return count;
    }

}