package graphs;

/**
 * Weighted edge
 * @param <V> type of the vertices that an edge connects
 */
public class WeightedEdge<V> extends Edge<V>  {
    private Double weight;

    /**
     * Basic constructor
     * In directed graphs vertexA represents the origin while vertexB represents
     * the destination.
     * In undirected graphs it doesn't matter.
     * @param vertexA (vertex origin for directed graphs)
     * @param vertexB (vertex destination for directed graphs)
     * @param weight double indicating the weight
     */
    public WeightedEdge(V vertexA, V vertexB, Double weight) {
        super(vertexA,vertexB);
        this.weight = weight;
    }

    /**
     * Copy constructor
     * @param original edge to copy
     */
    public WeightedEdge(WeightedEdge<V> original) {
        super(original);
        weight = original.getWeight();
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getWeight() {
        return weight;
    }
}
