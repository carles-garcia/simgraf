import java.util.Objects;

public abstract class MultigraphS<V, E extends AbstractEdge<V>>
        extends PseudographS<V,E> {
    /**
     * Add an edge to the graph.
     * @param edge Must not be null. Its endpoints have to be contained in the graph
     *             and can't be the same (no loops)
     */
    public void add(E edge) {
        Objects.requireNonNull(edge, Errors.ADD_NULL_EDGE.toString());
        if (edge.getVertexA().equals(edge.getVertexB()))
            throw new IllegalArgumentException(Errors.LOOPS_NOT_ALLOWED.toString());
        // Should be optimised considering which vertex has less edges
        checkContained(edge.getVertexA());
        checkContained(edge.getVertexB());
        edgeList.get(edge.getVertexA()).add(edge);
        edgeList.get(edge.getVertexB()).add(edge);
        ++size;
    }
}

