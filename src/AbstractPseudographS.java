import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class AbstractPseudographS<V, E extends Edge<V>>
         extends AbstractGraphS<V,E> implements InterfaceMultigraph<V,E> {
//pseudodigraph?

    /**
     * Add an edge to the graph.
     * @param edge Must not be null. Its endpoints have to be contained in the graph.
     */
    public void add(E edge) {
        Objects.requireNonNull(edge, Errors.ADD_NULL_EDGE.toString());
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