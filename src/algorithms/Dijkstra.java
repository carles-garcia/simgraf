package algorithms;//Created on 04/08/15.

import graphs.DirectedGraph;
import graphs.WeightedEdge;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Dijkstra<V, E extends WeightedEdge<V>, G extends DirectedGraph<V,E>> {
    private HashMap<V, Double> distances;
    private HashMap<V, V> predecessors;
    private HashSet<V> visited;
    private final Double INFINITY = Double.POSITIVE_INFINITY;
    //todo: graph iterators?
    public Dijkstra(G graph, V source) {
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

    public HashMap<V, Double> getDistances() {
        return distances;
    }

    public HashMap<V, V> getPredecessors() {
        return predecessors;
    }

    public HashSet<V> getVisited() {
        return visited;
    }
}
