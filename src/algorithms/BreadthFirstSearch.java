package algorithms;

import graphs.AbstractGraph;
import graphs.Edge;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch<V, E extends Edge<V>, G extends AbstractGraph<V,E>> {
    private HashMap<V, Boolean> visited;
    private HashMap<V, Integer> distances;
    private HashMap<V, V> predecessors;

    private final int INFINITY = -1;

    public BreadthFirstSearch(G graph, V search_key) {
        Queue<V> q = new LinkedList<>();
        initialise(graph, search_key, q);

        while (!q.isEmpty()) {
            V v = q.remove();
            for (V adjV : graph.getNeighbours(v)) {
                if (!visited.get(adjV)) {
                    visited.put(v, true);
                    distances.put(adjV,distances.get(v) + 1);
                    predecessors.put(adjV, v);
                    q.add(adjV);
                }
            }
        }
    }

    public BreadthFirstSearch(G graph, V search_key, V to_find) {
        Queue<V> q = new LinkedList<>();
        initialise(graph, search_key, q);

        while (!q.isEmpty()) {
            V v = q.remove();
            for (V adjV : graph.getNeighbours(v)) {
                if (!visited.get(adjV)) {
                    visited.put(v, true);
                    distances.put(adjV, distances.get(v) + 1);
                    predecessors.put(adjV, v);
                    if (adjV == to_find) return;
                    q.add(adjV);
                }
            }
        }
    }

    private void initialise(G graph, V search_key, Queue<V> q) {
        visited = new HashMap<>();
        distances = new HashMap<>();
        predecessors = new HashMap<>();

        for (V vertex : graph.getVertices()) {
            visited.put(vertex,false);
            distances.put(vertex,INFINITY);
            predecessors.put(vertex,null);
        }

        distances.put(search_key, 0);
        visited.put(search_key, true);
        q.add(search_key);
    }

    private HashMap<V, Integer> getDistances() {
        return distances;
    }

    private HashMap<V, V> getPredecessors() {
        return predecessors;
    }



}
