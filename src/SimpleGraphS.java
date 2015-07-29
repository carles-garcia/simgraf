/**
 * Undirected graph that doesn't allow loops.
 * Implementation optimised for space.
 * @param <V> Vertices type
 * @param <E> Undirected edge, must extend {@link Edge}
 */
public class SimpleGraphS<V, E extends Edge<V>> extends AbstractSimpleGraphS<V,E> {
    public SimpleGraphS() {
        super();
    }
}
