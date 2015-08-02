package graphs;

import java.util.Collection;

public abstract class AbstractGraphT<V, E extends Edge<V>> {
    private boolean loopsAllowed;
    private boolean multigraph;

    /**
     * Constructor for the graph.
     * @param loopsAllowed True if loops are allowed
     * @param multigraph True if multiple edges between two vertices are allowed
     */
    public AbstractGraphT(boolean loopsAllowed, boolean multigraph) {
        this.loopsAllowed = loopsAllowed;
        this.multigraph = multigraph;
    }

    /**
     * Returns if loops are allowed in the graph
     * @return True is loops are allowed
     */
    public boolean loopsAllowed() {
        return loopsAllowed;
    }

    /**
     * Returns if the graph is a multigraph
     * @return True if multiple edges between two vertices are allowed
     */
    public boolean isMultigraph() {
        return  multigraph;
    }

    /**
     * Add vertex to the graph
     * @param vertex must not be null. Must not be already contained in the graph.
     */
    public abstract void add(V vertex);

    /**
     * Remove vertex and its incident edges from the graph
     * @param vertex has to be contained in the graph
     */
    public abstract void remove(V vertex);

    /**
     * Add an edge to the graph.
     * @param edge must not be null. Its endpoints have to be contained in the graph.
     */
    public abstract void add(E edge);

    /**
     * Remove edge from the graph
     * @param edge must not be null. Has to be contained in the graph.
     */
    public abstract void remove(E edge);

    /**
     * Get an edge between two adjacent vertices
     * @param vertexA Must not be null.
     * @param vertexB Must not be null.
     * @return edge between vertexA and vertexB.
     */
    public abstract E getEdge(V vertexA, V vertexB);

    /**
     * Returns true if two vertices are adjacent
     * (if there is an edge between the two vertices)
     * @param vertexA has to be contained in the graph.
     * @param vertexB has to be contained in the graph.
     * @return true if vertexA and vertexB are adjacent.
     */
    public abstract boolean areAdjacent(V vertexA, V vertexB);

    /**
     * Returns true if the vertex is contained in the graph
     * @param vertex vertex to test
     * @return true if found
     */
    public abstract boolean contains(V vertex);

    /**
     * Get all vertices in the graph
     * @return collection containing all vertices
     */
    public abstract Collection<V> getVertices();

    /**
     * Get all edges in the graph
     * @return collection containing all edges
     */
    public abstract Collection<E> getEdges();

    /**
     * Get neighbours of a vertex
     * A neighbour of a vertex is another vertex that can be reached from it through an edge.
     * @param vertex to get neighbours of
     * @return collection containing neighbours of the vertex
     */
    public abstract Collection<V> getNeighbours(V vertex);

    /**
     * Order of the graph (number of vertices contained)
     * @return integer indicating order of the graph
     */
    public abstract int order();

    /**
     * Size of the graph (number of edges contained)
     * @return integer indicating size of the graph
     */
    public abstract int size();


}
