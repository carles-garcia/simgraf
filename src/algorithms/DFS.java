package algorithms;

import graphs.AbstractGraph;
import graphs.Edge;

import java.util.HashSet;

public class DFS<V> {
    private HashSet<V> visited;

    public DFS(AbstractGraph<V,?> graph, V search_key) {
        visited = new HashSet<>();
        DFS(graph, search_key);

    }

    private void DFS(AbstractGraph<V,?> graph, V vertex) {
        visited.add(vertex);
        for (V adjV : graph.getNeighbours(vertex)) {
            if (!visited.contains(adjV)) DFS(graph, adjV);
        }
    }
}
