package graphs;

import graphs.Edge;

import java.util.Collection;

public interface InterfaceGraph<V, E extends Edge<V>> {

    /**
     * Get degree of a vertex (number of incident edges).
     * @param vertex Has to be contained in the graph.
     * @return int degree
     */
    int degree(V vertex);

    /**
     * Get neighbours of a vertex (adjacent vertices)
     * @param vertex Has to be contained in the graph.
     * @return collection containing neighbours
     */
    Collection<V> getNeighbours(V vertex);

    /**
     * Get incident edges to a vertex
     * @param vertex Has to be contained in the graph.
     * @return collection containing the edges
     */
    Collection<E> getEdges(V vertex);

    int order(); // number of vertices
    int size(); // number of edges

    int indegree(V vertex);

    int outdegree(V vertex);

    Collection<E> getIncomingArcs(V vertex);

    Collection<E> getOutgoingArcs(V vertex);

    /**
     * Get all edges between two vertices
     * @param vertexA Has to be contained in the graph.
     * @param vertexB Has to be contained in the graph.
     * @return collection containing all edges between vertexA and vertexB
     */
    Collection<E> getEdges(V vertexA, V vertexB);

    /**
     * Number of edges between two vertices
     * @param vertexA Has to be contained in the graph.
     * @param vertexB Has to be contained in the graph.
     * @return int number of edges between vertexA and vertexB
     */
    int numberOfEdges(V vertexA, V vertexB);
}
