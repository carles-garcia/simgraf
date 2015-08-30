package algorithms;//Created on 04/08/15.

import graphs.DirectedGraph;
import graphs.WeightedEdge;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Dijkstra algorithm
 * @param <V> type of the vertices of the graph
 * @param <E> type of the edges of the graph. Have to extend {@link WeightedEdge}
 */
public class Dijkstra<V, E extends WeightedEdge<V>> {
    private HashMap<V, Double> distances;
    private HashMap<V, V> predecessors;
    private HashSet<V> visited;
    private final Double INFINITY = Double.POSITIVE_INFINITY;
    //todo: graph iterators?

    /**
     * Execute algorithm starting from source vertex
     * @param graph graph to traverse
     * @param source vertex to start from
     */
    public Dijkstra(DirectedGraph<V,E> graph, V source) {
        distances = new HashMap<>();
        predecessors = new HashMap<>();
        visited = new HashSet<>();
        for (V vertex : graph.getVertices()) {
            distances.put(vertex, INFINITY);
            predecessors.put(vertex, null);
        }
        distances.put(source, 0.0);

        PriorityQueue<V> q = new PriorityQueue<V>(graph.order(), new Comparator<V>() {
            public int compare(V vertex1, V vertex2) {
                return distances.get(vertex1).compareTo(distances.get(vertex2));
            }
        });

        q.add(source);
        while (!q.isEmpty()) {
            V a = q.remove();
            if (!visited.contains(a)) {
                visited.add(a);
                for (E edge : graph.getOutgoingEdges(a)) {
                    Double newD = distances.get(a) + edge.getWeight();
                    V b = edge.getVertexB();
                    if (newD < distances.get(b)) {
                        distances.put(b, newD);
                        predecessors.put(b,a);
                        q.add(b);
                    }
                }
            }
        }
    }

    /**
     * Get map containing the shortest distance from the source vertex to each vertex.
     * @return HashMap of the vertices as keys and the distances as values
     */
    public HashMap<V, Double> getDistances() {
        return distances;
    }

    /**
     * Get map containing the predecessor of each vertex in the shortest path from the source vertex.
     * If the predecessor is null, there is no path.
     * @return HashMap of the vertices as keys and their predecessors as values
     */
    public HashMap<V, V> getPredecessors() {
        return predecessors;
    }

    /**
     * Get set of the visited vertices
     * @return HashSet of the visited vertices
     */
    public HashSet<V> getVisited() {
        return visited;
    }
}
