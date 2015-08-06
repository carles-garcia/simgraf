package algorithms;

import graphs.AbstractGraph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;

public class DFS<V> {
    private HashSet<V> visited;
    private HashMap<V, AtomicInteger> distances;  // atomic to update efficiently
    private HashMap<V, V> predecessors;
    private final int INFINITY = -1;

    public DFS(AbstractGraph<V,?> graph, V search_key) {
        visited = new HashSet<>();
        distances = new HashMap<>();
        predecessors = new HashMap<>();
        for (V vertex : graph.getVertices()) {
            distances.put(vertex, new AtomicInteger(INFINITY));
            predecessors.put(vertex,null);
        }
        distances.get(search_key).set(0);

        DFS(graph, search_key);

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

    public HashSet<V> getVisited() {
        return visited;
    }

    public HashMap<V, AtomicInteger> getDistances() {
        return distances;
    }

    public HashMap<V, V> getPredecessors() {
        return predecessors;
    }
}
