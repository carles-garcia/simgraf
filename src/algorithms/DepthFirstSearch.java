package algorithms;//Created on 03/08/15.

import graphs.AbstractGraph;
import graphs.Edge;

import java.util.ArrayList;
import java.util.HashMap;

public class DepthFirstSearch<V, E extends Edge<V>, G extends AbstractGraph<V,E>> {
    ArrayList<V> reachable;
    HashMap<V, Boolean> visited;

    public DepthFirstSearch(G graph, V search_key) {
        reachable = new ArrayList<>();
        reachable.add(search_key);
        visited = new HashMap<>();
        for (V vertex : graph.getVertices()) {
            visited.put(vertex,false); //instead of map, use set with only those that have been visited
        }

        DFS(graph, search_key);

    }

    private void DFS(G graph, V vertex) {
        visited.put(vertex, true);
        for (V adjV : graph.getNeighbours(vertex)) {
            if (!visited.get(adjV)) DFS(graph, adjV);
        }
    }
}
