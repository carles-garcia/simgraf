package algorithms;

import graphs.AbstractGraph;
import graphs.Edge;

import java.util.HashSet;

public class DepthFirstSearch<V, E extends Edge<V>, G extends AbstractGraph<V,E>> {
    private HashSet<V> visited;

    public DepthFirstSearch(G graph, V search_key) {
        visited = new HashSet<>();
        DFS(graph, search_key);

    }

    private void DFS(G graph, V vertex) {
        visited.add(vertex);
        for (V adjV : graph.getNeighbours(vertex)) {
            if (!visited.contains(adjV)) DFS(graph, adjV);
        }
    }
}
