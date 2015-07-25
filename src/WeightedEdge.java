public class WeightedEdge<V> extends Edge<V> {
    private Double weight;

    public WeightedEdge(V vertex1, V vertex2, Double weight) {
        super(vertex1,vertex2);
        this.weight = weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getWeight() {
        return weight;
    }
}
