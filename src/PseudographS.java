/**
 * Undirected graph that allows loops and multiple edges between two vertices.
 * Implementation optimised for space.
 * @param <V> Vertices type
 * @param <E> Undirected edge, must extend {@link Edge}
 */
public class PseudographS<V, E extends Edge<V>> extends AbstractPseudographS<V,E> {
    public PseudographS() {
        super();
    }
}