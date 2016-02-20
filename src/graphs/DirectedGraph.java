/*
Copyright (C) 2015,2016  Carles Garcia Cabot  (github.com/carles-garcia)
This file is part of simgraf, a simple graph library written in Java. 
Released under the Mozilla Public License v2 (see COPYING.txt)
*/
package graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class DirectedGraph<V, E extends Edge<V>> extends AbstractGraph<V,E> {
    /*
    list contains the vertices as keys. The values are HashMaps that have the adjacent vertices as keys
    and EdgeLists associated. Each EdgeList contains the edges between two vertices.
     */
    private HashMap<V, HashMap<V,EdgeList>> list;
    private HashMap<V, AtomicInteger> indegrees; //atomic to update efficiently
    private HashMap<V, AtomicInteger> outdegrees;
    private int size; // number of edges

    private class EdgeList {
        HashSet<E> incoming;
        HashSet<E> outgoing;

        EdgeList() {
            incoming = new HashSet<>();
            outgoing = new HashSet<>();
        }
    }

    /* If the vertex isn't contained in the graph (and this is true for a null reference) throws
    an IllegalArgumentException
     */
    private void checkContained(V vertex) {
        if (!contains(vertex)) throw new IllegalArgumentException(Errors.VERTEX_NOT_CONTAINED.toString());
    }

    /* PUBLIC METHODS */

    /**
     * Constructor for the directed graph.
     * @param loopsAllowed True if loops are allowed
     * @param multigraph True if multiple edges between two vertices are allowed
     */
    public DirectedGraph(boolean loopsAllowed, boolean multigraph) {
        super(loopsAllowed,multigraph);
        list = new HashMap<>();
        indegrees = new HashMap<>();
        outdegrees = new HashMap<>();
        size = 0;
    }

    /* Overrides */

    public void add(V vertex) {
        Objects.requireNonNull(vertex, Errors.ADD_NULL_VERTEX.toString());
        if (!list.containsKey(vertex)) {
            list.put(vertex, new HashMap<V,EdgeList>());
            indegrees.put(vertex, new AtomicInteger());
            outdegrees.put(vertex, new AtomicInteger());
        }
        else throw new IllegalArgumentException(Errors.VERTEX_CONTAINED.toString());
    }

    public void remove(V vertex) {
        Objects.requireNonNull(vertex, Errors.VERTEX_NOT_CONTAINED.toString());
        for (V adjV : list.get(vertex).keySet()) {
            indegrees.get(adjV).getAndAdd(-list.get(adjV).get(vertex).incoming.size());
            outdegrees.get(adjV).getAndAdd(-list.get(adjV).get(vertex).outgoing.size());
            list.get(adjV).remove(vertex);
        }
        list.remove(vertex);
    }

    public void add(E edge) {
        Objects.requireNonNull(edge, Errors.ADD_NULL_EDGE.toString());
        if (!loopsAllowed() && edge.getVertexA().equals(edge.getVertexB()))
            throw new IllegalArgumentException(Errors.LOOPS_NOT_ALLOWED.toString());

        boolean adjacent = areAdjacent(edge.getVertexA(), edge.getVertexB());
        if (isMultigraph() || !adjacent) {
            if (!adjacent) {
                list.get(edge.getVertexA()).put(edge.getVertexB(),new EdgeList());
                list.get(edge.getVertexB()).put(edge.getVertexA(),new EdgeList());
            }
            if (!list.get(edge.getVertexA()).get(edge.getVertexB()).outgoing.add(edge))
                throw new IllegalArgumentException(Errors.EDGE_CONTAINED.toString());
            list.get(edge.getVertexB()).get(edge.getVertexA()).incoming.add(edge);
            ++size;

            outdegrees.get(edge.getVertexA()).getAndIncrement();
            indegrees.get(edge.getVertexB()).getAndIncrement();
        }
        else throw new IllegalArgumentException(Errors.ADD_EXISTING_EDGE.toString());
    }

    public void remove(E edge) {

        Objects.requireNonNull(edge, Errors.REMOVE_NULL_EDGE.toString());
        if (!areAdjacent(edge.getVertexA(), edge.getVertexB()))
            throw new IllegalArgumentException(Errors.EDGE_NOT_CONTAINED.toString());

        if (!list.get(edge.getVertexA()).get(edge.getVertexB()).outgoing.remove(edge))
            throw new IllegalArgumentException(Errors.EDGE_NOT_CONTAINED.toString());
        if (list.get(edge.getVertexA()).get(edge.getVertexB()).outgoing.isEmpty()
                && list.get(edge.getVertexA()).get(edge.getVertexB()).incoming.isEmpty()) {
            list.get(edge.getVertexA()).remove(edge.getVertexB());
            list.get(edge.getVertexB()).remove(edge.getVertexA());
        }
        else list.get(edge.getVertexB()).get(edge.getVertexA()).incoming.remove(edge);

        --size;

        outdegrees.get(edge.getVertexA()).getAndDecrement();
        indegrees.get(edge.getVertexB()).getAndDecrement();
    }


    public E getEdge(V vertexA, V vertexB) {
        if (!areAdjacent(vertexA,vertexB))
            throw new IllegalArgumentException(Errors.NOT_ADJACENT.toString());
        E edge = list.get(vertexA).get(vertexB).incoming.iterator().next();
        if (edge == null) edge = list.get(vertexA).get(vertexB).outgoing.iterator().next();
        return edge;
    }

    public boolean areAdjacent(V vertexA, V vertexB) {
        checkContained(vertexA);
        checkContained(vertexB);
        return list.get(vertexA).containsKey(vertexB);
    }

    public boolean contains(V vertex) {
        return list.containsKey(vertex);
    }

    public Set<V> getVertices() {
        return list.keySet();
    }

    public Set<E> getEdges() {
        HashSet<E> hs = new HashSet<>();
        for (HashMap<V,EdgeList> hm : list.values()) {
            for (EdgeList el : hm.values()) {
                hs.addAll(el.incoming);
                hs.addAll(el.outgoing);
            }
        }
        return hs;
    }

    public Set<V> getNeighbours(V vertex) {
        checkContained(vertex);
        HashSet<V> hs = new HashSet<>();
        for (V adjV : list.get(vertex).keySet()) {
            if (!list.get(vertex).get(adjV).outgoing.isEmpty())
                hs.add(adjV);
        }
        return hs;
    }

    public int order() {
        return list.size();
    }

    public int size() {
        return size;
    }

    /* Original methods */

    /**
     * Returns the indegree of a vertex (number of incoming edges)
     * @param vertex to get indegree of
     * @return integer indicating indegree of the vertex
     */
    public int indegree(V vertex) {
        checkContained(vertex);
        return indegrees.get(vertex).get();
    }

    /**
     * Returns the outdegree of a vertex (number of outgoing edges)
     * @param vertex to get outdegree of
     * @return integer indicating outdegree of the vertex
     */
    public int outdegree(V vertex) {
        checkContained(vertex);
        return outdegrees.get(vertex).get();
    }

    /**
     * Get incoming edges of the vertex
     * @param vertex to get incoming edges
     * @return set containing incoming edges to the vertex
     */
    public Set<E> getIncomingEdges(V vertex) {
        checkContained(vertex);
        HashSet<E> hs = new HashSet<>();
        for (EdgeList el : list.get(vertex).values()) {
            hs.addAll(el.incoming);
        }
        return hs;
    }

    /**
     * Get outgoing edges of the vertex
     * @param vertex to get outgoing edges
     * @return set containing outgoing edges from the vertex
     */
    public Set<E> getOutgoingEdges(V vertex) {
        checkContained(vertex);
        HashSet<E> hs = new HashSet<>();
        for (EdgeList el : list.get(vertex).values()) {
            hs.addAll(el.outgoing);
        }
        return hs;
    }

}
