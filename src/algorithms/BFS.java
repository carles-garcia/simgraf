package algorithms;

import graphs.AbstractGraph;
import graphs.Edge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class BFS<V> {
    private HashSet<V> visited; //linkedHashSet would mantain order
    private HashMap<V, AtomicInteger> distances;  // atomic to update efficiently
    private HashMap<V, V> predecessors;

    private final int INFINITY = -1;
//todo: throw exceptions in algorithms?
    public BFS(AbstractGraph<V,?> graph, V search_key) {
        Queue<V> q = new LinkedList<>();
        initialise(graph, search_key, q);

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

    public BFS(AbstractGraph<V,?> graph, V search_key, V to_find) {
        Queue<V> q = new LinkedList<>();
        initialise(graph, search_key, q);

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

    private void initialise(AbstractGraph<V,?> graph, V search_key, Queue<V> q) {
        visited = new HashSet<>();
        distances = new HashMap<>();
        predecessors = new HashMap<>();

        for (V vertex : graph.getVertices()) {
            distances.put(vertex, new AtomicInteger(INFINITY));
            predecessors.put(vertex,null);
        }

        distances.get(search_key).set(0);
        visited.add(search_key);
        q.add(search_key);
    }

    public HashMap<V, AtomicInteger> getDistances() {
        return distances;
    }

    public HashMap<V, V> getPredecessors() {
        return predecessors;
    }

    public HashSet<V> getVisited() {
        return visited;
    }

}
