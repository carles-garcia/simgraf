public class WeightedArc<V> extends  WeightedEdge<V> {
    public WeightedArc(V from, V to, Double weight) {
        super(from,to,weight);
    }

    public V getFrom() { return getVertex1(); }
    public V getTo() { return getVertex2(); }
}
