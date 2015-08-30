package algorithms;

import graphs.AbstractGraph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Breadth-First Search algorithm
 * @param <V> type of the vertices of the graph to search
 */
public class BFS<V> {
    private HashSet<V> visited; //linkedHashSet would mantain order
    private HashMap<V, AtomicInteger> distances;  // atomic to update efficiently
    private HashMap<V, V> predecessors;

    private final int INFINITY = -1;
    //todo: throw exceptions in algorithms

    /**
     * Traverses all the graph starting from source vertex
     * @param graph graph to traverse
     * @param source vertex to start from
     */
    public BFS(AbstractGraph<V,?> graph, V source) {
        Queue<V> q = new LinkedList<>();
        initialise(graph, source, q);

        while (!q.isEmpty()) {
            V v = q.remove();
            for (V adjV : graph.getNeighbours(v)) {
                if (!visited.contains(adjV)) {
                    visited.add(adjV);
                    distances.get(adjV).set(distances.get(v).get() + 1);
                    predecessors.put(adjV, v);
                    q.add(adjV);
                }
            }
        }
    }

    /**
     * Find the shortest path from a source vertex to another vertex.
     * This version doesn't traverse all the graph. It stops when the vertex to find is found.
     * @param graph graph
     * @param source vertex to start from
     * @param to_find vertex to find
     */
    public BFS(AbstractGraph<V,?> graph, V source, V to_find) {
        Queue<V> q = new LinkedList<>();
        initialise(graph, source, q);

        while (!q.isEmpty()) {
            V v = q.remove();
            for (V adjV : graph.getNeighbours(v)) {
                if (!visited.contains(adjV)) {
                    visited.add(adjV);
                    distances.get(adjV).set(distances.get(v).get() + 1);
                    predecessors.put(adjV, v);
                    if (adjV == to_find) return;
                    q.add(adjV);
                }
            }
        }
    }

    private void initialise(AbstractGraph<V,?> graph, V source, Queue<V> q) {
        visited = new HashSet<>();
        distances = new HashMap<>();
        predecessors = new HashMap<>();

        for (V vertex : graph.getVertices()) {
            distances.put(vertex, new AtomicInteger(INFINITY));
            predecessors.put(vertex,null);
        }

        distances.get(source).set(0);
        visited.add(source);
        q.add(source);
    }

    /**
     * Get map containing the shortest distance from the source vertex to each vertex.
     * @return HashMap of the vertices as keys and the distances as values
     */
    public HashMap<V, AtomicInteger> getDistances() {
        return distances;
    }

    /**
     * Get map containing the predecessor of each vertex in the shortest path from the source vertex.
     * If the predecessor is null, there is no path or the graph wasn't fully traversed.
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
