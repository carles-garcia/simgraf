/*
Copyright (C) 2015,2016  Carles Garcia Cabot  (github.com/carles-garcia)
This file is part of simgraf, a simple graph library written in Java. 
Released under the Mozilla Public License v2 (see COPYING.txt)
*/
package graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * Edge
 * @param <V> type of the vertices that an edge connects
 */
public class Edge<V> {
    private V vertexA;
    private V vertexB;

    /**
     * Basic constructor
     * <p> In directed graphs vertexA represents the origin while vertexB represents
     * the destination. In undirected graphs it doesn't matter.
     * @param vertexA (vertex origin for directed graphs)
     * @param vertexB (vertex destination for directed graphs)
     */
    public Edge(V vertexA, V vertexB) {
        this.vertexA = vertexA;
        this.vertexB = vertexB;
    }

    /**
     * Copy constructor
     * @param original edge to copy
     */
    public Edge(Edge<V> original) {
        vertexA = original.getVertexA();
        vertexB = original.getVertexB();
    }

    /**
     * Get endpoints of an edge
     * @return list containing vertexA in position 0 and vertexB in position 1.
     */
    public List<V> getEndpoints() {
        ArrayList<V> al = new ArrayList<>();
        al.add(vertexA);
        al.add(vertexB);
        return al;
    }

    public V getVertexA() {
        return vertexA;
    }

    public V getVertexB() {
        return vertexB;
    }

}
