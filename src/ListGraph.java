import java.util.*;

public abstract class ListGraph<V, E extends Edge<V>> implements Graph<V,E> {
    private HashMap<V, HashSet<E>> edgeList;

    public ListGraph() {
        edgeList = new HashMap<>();
    }

    /**
     * Add vertex to the graph
     * @param vertex Must not be null. If it's already in the graph, nothing happens
     */
    public void add(V vertex) {
        // Null vertices can't be added, therefore there will never be null vertices
        Objects.requireNonNull(vertex, "vertex is null");
        if (!edgeList.containsKey(vertex)) edgeList.put(vertex, new HashSet<E>());
    }

    /**
     * Remove vertex and its incident edges from the graph
     * @param vertex must be contained in the graph
     */
    public void remove(V vertex) {
        for (E edge : Objects.requireNonNull(edgeList.get(vertex), "vertex is not contained in the graph")) {
            edgeList.get
                    (edge.getVertex1().equals(vertex) ? edge.getVertex2() : edge.getVertex1()).remove(edge);
        }
        edgeList.remove(vertex);
    }


    public void add(E edge) {
        if (!areAdjacent(edge.getVertex1(),edge.getVertex2())) {
            edgeList.get(edge.getVertex1()).add(edge);
            edgeList.get(edge.getVertex2()).add(edge);
        }
    }

    public void remove(E edge) {
        edgeList.get(edge.getVertex1()).remove(edge);
        edgeList.get(edge.getVertex2()).remove(edge);
    }

    public E getEdge(V vertex1, V vertex2) {
        for (E edge : edgeList.get(vertex1)) {
            if (edge.getVertex1().equals(vertex1) ?
                    edge.getVertex2().equals(vertex2) : edge.getVertex1().equals(vertex2))
                return edge;
        }
        return null;
    }

    public boolean areAdjacent(V vertex1, V vertex2) {
        if (!contains(vertex1)) throw new IllegalArgumentException("vertex1 is not contained in the graph");
        else if (!contains(vertex2)) throw new IllegalArgumentException("vertex2 is not contained in the graph");

        for (E edge : edgeList.get(vertex1)) {
            if (edge.getVertex1().equals(vertex1) ?
                    edge.getVertex2().equals(vertex2) : edge.getVertex1().equals(vertex2))
                return true;
        }
        return false;
    }

    public boolean contains(V vertex) {
        return edgeList.containsKey(vertex);
    }

    public Set<V> getVertices() {
        return edgeList.keySet();
    }

    public Set<E> getEdges() {
        HashSet<E> edges = new HashSet<>();
        for (HashSet<E> s: edgeList.values()) {
            for (E edge : s) {
                edges.add(edge);
            }
        }
        return edges;
    }

    public int degree(V vertex) {
        return edgeList.get(vertex).size();
    }



}
