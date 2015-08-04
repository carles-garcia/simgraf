package algorithms;//Created on 03/08/15.

import graphs.AbstractGraph;
import graphs.DirectedGraph;
import graphs.WeightedEdge;

import java.util.HashMap;

public class Bellman_Ford<V, E extends WeightedEdge<V>, G extends DirectedGraph<V,E>> {
    private HashMap<V, Double> distances;
    private HashMap<V, V> predecessors;
    private final Double INFINITY = Double.POSITIVE_INFINITY;
    private boolean negativeCycle;


    public Bellman_Ford(G graph, V source) {
        distances = new HashMap<>();
        predecessors = new HashMap<>();
        negativeCycle = false;
        for (V vertex : graph.getVertices()) {
            distances.put(vertex, INFINITY);
            predecessors.put(vertex,null);
        }
        distances.put(source, INFINITY);

        for (int i = 0; i < graph.order()-1; ++i) {
            for (E edge : graph.getEdges()) {
                Double newD = distances.get(edge.getVertexA()) + edge.getWeight();
                if (newD < distances.get(edge.getVertexB())) {
                    distances.put(edge.getVertexB(), newD);
                    predecessors.put(edge.getVertexB(), edge.getVertexA());
                }
            }
        }

        //check for negative cycles
        for (E edge : graph.getEdges()) {
            if (distances.get(edge.getVertexA()) + edge.getWeight()
                    < distances.get(edge.getVertexB())) {
                negativeCycle = true;
                break;
            }
        }

    }

    public HashMap<V, Double> getDistances() {
        return distances;
    }

    public HashMap<V, V> getPredecessors() {
        return predecessors;
    }

    public boolean hasNegativeCycle() {
        return negativeCycle;
    }

}
