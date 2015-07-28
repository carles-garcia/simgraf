/**
 * Undirected graph that allows loops.
 * Implementation optimised for space.
 * @param <V> Vertices type
 * @param <E> Undirected edge, must extend {@link Edge}
 */
public class GraphS<V, E extends Edge<V>> extends AbstractGraphS<V,E> {
    public GraphS() {
        super();
    }
}
