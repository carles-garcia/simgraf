public class WeightedEdge<V> extends Edge<V> implements InterfaceWeighted  {
    private Double weight;

    public WeightedEdge(V vertexA, V vertexB, Double weight) {
        super(vertexA,vertexB);
        this.weight = weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getWeight() {
        return weight;
    }
}
