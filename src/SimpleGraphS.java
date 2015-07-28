import java.util.Objects;

public abstract class SimpleGraphS<V, E extends AbstractEdge<V>> extends GraphS<V,E> {
    /**
     * Add an edge to the graph.
     * @param edge Must not be null. Its endpoints have to be contained in the graph,
     *             can't be the same (no loops) and can't be already adjacent.
     */
    public void add(E edge) {
        Objects.requireNonNull(edge, Errors.ADD_NULL_EDGE.toString());
        if (edge.getVertexA().equals(edge.getVertexB()))
            throw new IllegalArgumentException(Errors.LOOPS_NOT_ALLOWED.toString());
        if (!areAdjacent(edge.getVertexA(), edge.getVertexB())) {
            edgeList.get(edge.getVertexA()).add(edge);
            edgeList.get(edge.getVertexB()).add(edge);
            ++size;
        }
        else throw new IllegalArgumentException(Errors.ADD_EXISTING_EDGE.toString());
    }
}
