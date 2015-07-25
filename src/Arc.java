public class Arc<V> extends Edge<V> {
    public Arc(V from, V to) {
        super(from,to);
    }

    public V getFrom() { return getVertex1(); }
    public V getTo() { return getVertex2(); }
}

//alternative: A and B