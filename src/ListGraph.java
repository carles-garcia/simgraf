import java.util.*;

public abstract class ListGraph<V, E extends Edge<V>> implements Graph<V,E> {
    private HashMap<V, HashSet<E>> edgeList;

    public ListGraph() {
        edgeList = new HashMap<>();
    }

    /**
     * Add vertex to the graph
     * @param vertex if it's already in the graph, nothing happens
     */
    public void add(V vertex) {
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

    public void add(V vertex1, V vertex2) {
        if (!areAdjacent(vertex1,vertex2)) {
       // Instantiate new E     edgeList.get(vertex1).add(new E(vertex1,vertex2));
        }
    }

    /**
     * Remove the edge between two vertices, if it exists
     * @param vertex1 must be contained in the graph
     * @param vertex2 must be contained in the graph
     */
    public void remove(V vertex1, V vertex2) {
        Objects.requireNonNull(vertex1, "vertex1 is null");
        Objects.requireNonNull(vertex2, "vertex2 is null");
        for (E edge : Objects.requireNonNull(edgeList.get(vertex1), "vertex1 is not contained in the graph")) {
            if (edge.getVertex1().equals(vertex1) ?
                    edge.getVertex2().equals(vertex2) : edge.getVertex1().equals(vertex2)) {
                edgeList.get(vertex1).remove(edge);
                break;
            }
        }
        for (E edge : Objects.requireNonNull(edgeList.get(vertex2), "vertex2 is not contained in the graph")) {
            if (edge.getVertex1().equals(vertex2) ?
                    edge.getVertex2().equals(vertex1) : edge.getVertex1().equals(vertex1)) {
                edgeList.get(vertex2).remove(edge);
                break;
            }
        }
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
