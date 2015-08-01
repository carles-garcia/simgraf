package graphs;

public abstract class AbstractGraphT<V, E extends Edge<V>> {
    private boolean loopsAllowed;
    private boolean multigraph;

    public AbstractGraphT(boolean loopsAllowed, boolean multigraph) {
        this.loopsAllowed = loopsAllowed;
        this.multigraph = multigraph;
    }

    public boolean loopsAllowed() {
        return loopsAllowed;
    }

    public boolean isMultigraph() {
        return  multigraph;
    }

}
