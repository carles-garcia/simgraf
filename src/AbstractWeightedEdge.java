public abstract class AbstractWeightedEdge extends AbstractEdge {
    private Double weight;

    public AbstractWeightedEdge(Double weight) {
        this.weight = weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getWeight() {
        return weight;
    }
}
