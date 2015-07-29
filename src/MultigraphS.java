/**
 * Undirected graph that allows multiple edges between two vertices but doesn't allow loops.
 * Implementation optimised for space.
 * @param <V> Vertices type
 * @param <E> Undirected edge, must extend {@link Edge}
 */
public class MultigraphS<V, E extends Edge<V>> extends AbstractMultigraphS<V,E> {
    public MultigraphS() {
        super();
    }
}