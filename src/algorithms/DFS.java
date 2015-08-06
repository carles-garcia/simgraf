package algorithms;

import graphs.AbstractGraph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Depth-First Search algorithm
 * @param <V> type of the vertices of the graph to search
 */
public class DFS<V> {
    private HashSet<V> visited;
    private HashMap<V, AtomicInteger> distances;  // atomic to update efficiently
    private HashMap<V, V> predecessors;
    private final int INFINITY = -1;

    /**
     * Traverses all the graph starting from source vertex
     * @param graph graph to traverse
     * @param source vertex to start from
     */
    public DFS(AbstractGraph<V,?> graph, V source) {
        initialise(graph, source);
        DFS(graph, source);
    }

    /**
     * Find a path from a source vertex to another vertex.
     * This version doesn't traverse all the graph. It stops when the vertex to find is found.
     * @param graph graph
     * @param source vertex to start from
     * @param to_find vertex to find
     */
    public DFS(AbstractGraph<V,?> graph, V source, V to_find) {
        initialise(graph,source);
        DFS_2(graph,source,to_find);
    }

    private void DFS(AbstractGraph<V,?> graph, V vertex) {
        visited.add(vertex);
        for (V adjV : graph.getNeighbours(vertex)) {
            if (!visited.contains(adjV)) {
                predecessors.put(adjV, vertex);
                distances.get(adjV).set(distances.get(vertex).get() + 1);
                DFS(graph, adjV);
            }
        }
    }

    private boolean DFS_2(AbstractGraph<V,?> graph, V vertex, V to_find) {
        visited.add(vertex);
        for (V adjV : graph.getNeighbours(vertex)) {
            if (!visited.contains(adjV)) {
                predecessors.put(adjV, vertex);
                distances.get(adjV).set(distances.get(vertex).get() + 1);
                if (adjV == to_find || DFS_2(graph, adjV, to_find)) return true;
            }
        }
        return false;
    }

    private void initialise(AbstractGraph<V,?> graph, V source) {
        visited = new HashSet<>();
        distances = new HashMap<>();
        predecessors = new HashMap<>();
        for (V vertex : graph.getVertices()) {
            distances.put(vertex, new AtomicInteger(INFINITY));
            predecessors.put(vertex,null);
        }
        distances.get(source).set(0);
    }

    /**
     * Get set of the visited vertices
     * @return HashSet of the visited vertices
     */
    public HashSet<V> getVisited() {
        return visited;
    }

    /**
     * Get map containing the distance from the source vertex of each vertex.
     * @return HashMap of the vertices as keys and the distances as values
     */
    public HashMap<V, AtomicInteger> getDistances() {
        return distances;
    }

    /**
     * Get map containing the predecessor of each vertex in the shortest path to the source vertex.
     * If the predecessor is null, there is no path or the graph wasn't fully traversed.
     * @return HashMap of the vertices as keys and their predecessors as values
     */
    public HashMap<V, V> getPredecessors() {
        return predecessors;
    }
}
