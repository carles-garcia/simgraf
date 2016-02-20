/*
Copyright (C) 2015,2016  Carles Garcia Cabot  (github.com/carles-garcia)
This file is part of simgraf, a simple graph library written in Java. 
Released under the Mozilla Public License v2 (see COPYING.txt)
*/
package algorithms;

import graphs.DirectedGraph;
import graphs.WeightedEdge;

import java.util.HashMap;

/**
 * Bellman-Ford algorithm
 * @param <V> type of the vertices of the graph
 * @param <E> type of the edges of the graph. Must extend {@link WeightedEdge}
 */
public class BellmanFord<V, E extends WeightedEdge<V>> {
    private HashMap<V, Double> distances;
    private HashMap<V, V> predecessors;
    private final Double INFINITY = Double.POSITIVE_INFINITY;
    private boolean negativeCycle;

    /**
     * Execute the Bellman-Ford algorithm starting from the source vertex.
     * @param graph to traverse
     * @param source vertex to start from
     */
    public BellmanFord(DirectedGraph<V,E> graph, V source) {
        distances = new HashMap<>();
        predecessors = new HashMap<>();
        negativeCycle = false;
        for (V vertex : graph.getVertices()) {
            distances.put(vertex, INFINITY);
            predecessors.put(vertex,null);
        }
        distances.put(source, 0.0);

        for (int i = 0; i < graph.order()-1; ++i) {
            boolean change = false;
            for (E edge : graph.getEdges()) {
                Double newD = distances.get(edge.getVertexA()) + edge.getWeight();
                if (newD < distances.get(edge.getVertexB())) {
                    distances.put(edge.getVertexB(), newD);
                    predecessors.put(edge.getVertexB(), edge.getVertexA());
                    change = true;
                }
            }
            if (!change) break; //optimisation
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

    /**
     * Get map containing the shortest distance from the source vertex to each vertex.
     * @return HashMap of the vertices as keys and the distances as values
     */
    public HashMap<V, Double> getDistances() {
        return distances;
    }

    /**
     * Get map containing the predecessor of each vertex in the shortest path from the source vertex.
     * If the predecessor is null, there is no path.
     * @return HashMap of the vertices as keys and their predecessors as values
     */
    public HashMap<V, V> getPredecessors() {
        return predecessors;
    }

    /**
     * Returns if the graph has a negative weight cycle
     * @return true if the graph has a negative weight cycle
     */
    public boolean hasNegativeCycle() {
        return negativeCycle;
    }

}
