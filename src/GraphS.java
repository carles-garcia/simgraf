import java.util.*;

public abstract class GraphS<V, E extends AbstractEdge<V>> implements InterfaceGraph<V,E> {
    /*
    Incidence list representation.
    Order of insertion of vertices is not saved

    Analysis of memory cost:
    (1 + 2*degree) references to each vertex
    A graph with 100 vertices would contain at least 100 references

    An implementation with adjacency list:
    (|V| + 2*degree) references to each vertex
    A graph with 100 vertices would contain at least 100.000 references

     */
    protected HashMap<V, HashSet<E>> edgeList;
    protected int size; // number of edges

    /*
    If the vertex isn't contained in the graph (and this is true for a null reference) throws
    an IllegalArgumenException
     */
    protected void checkContained(V vertex) {
        if (!contains(vertex)) throw new IllegalArgumentException(Errors.VERTEX_NOT_CONTAINED.toString());
    }

    /**
     * Default constructor
     */
    public GraphS() {
        edgeList = new HashMap<>();
        size = 0;
    }

    public void add(V vertex) {
        // Null vertices can't be added, therefore a graph will never contain null vertices
        Objects.requireNonNull(vertex, Errors.ADD_NULL_VERTEX.toString());
        if (!edgeList.containsKey(vertex)) edgeList.put(vertex, new HashSet<E>());
        else throw new IllegalArgumentException(Errors.VERTEX_CONTAINED.toString());
    }

    public void remove(V vertex) {
        for (E edge : Objects.requireNonNull(edgeList.get(vertex), Errors.VERTEX_NOT_CONTAINED.toString())) {
            edgeList.get
                    (edge.getVertexA().equals(vertex) ? edge.getVertexB() : edge.getVertexA()).remove(edge);
        }
        edgeList.remove(vertex);
    }

    public void add(E edge) {
        Objects.requireNonNull(edge, Errors.ADD_NULL_EDGE.toString());
        if (!areAdjacent(edge.getVertexA(), edge.getVertexB())) {
            edgeList.get(edge.getVertexA()).add(edge);
            edgeList.get(edge.getVertexB()).add(edge);
            ++size;
        }
        else throw new IllegalArgumentException(Errors.ADD_EXISTING_EDGE.toString());
    }

    public void remove(E edge) {
        Objects.requireNonNull(edge, Errors.REMOVE_NULL_EDGE.toString());
        if (!areAdjacent(edge.getVertexA(), edge.getVertexB())
                | !edgeList.get(edge.getVertexA()).remove(edge)
                | !edgeList.get(edge.getVertexB()).remove(edge))
            throw new IllegalArgumentException(Errors.EDGE_NOT_CONTAINED.toString());
        --size;
    }

    public E getEdge(V vertexA, V vertexB) {
        checkContained(vertexA);
        checkContained(vertexB);
        for (E edge : edgeList.get(vertexA)) {
            if (edge.getVertexA().equals(vertexA) ?
                    edge.getVertexB().equals(vertexB) : edge.getVertexA().equals(vertexB))
                return edge;
        }
        throw new IllegalArgumentException(Errors.NOT_ADJACENT.toString());
    }

    public boolean areAdjacent(V vertexA, V vertexB) {
        /*
        This method checks if the vertices are contained in the graph, therefore any method using this one
        doesn't need to check it again.
         */
        checkContained(vertexA);
        checkContained(vertexB);
        // Should be optimised considering which vertex has less edges
        for (E edge : edgeList.get(vertexA)) {
            if (edge.getVertexA().equals(vertexA) ?
                    edge.getVertexB().equals(vertexB) : edge.getVertexA().equals(vertexB))
                return true;
        }
        return false;
    }

    public boolean contains(V vertex) {
        return edgeList.containsKey(vertex);
    }

    public Set<V> getVertices() {
        // Should specify about order
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
        checkContained(vertex);
        return edgeList.get(vertex).size();
    }

    public Set<V> getNeighbours(V vertex) {
        checkContained(vertex);
        HashSet<V> hs = new HashSet<>();
        for (E edge: edgeList.get(vertex))
            hs.add(edge.getVertexA().equals(vertex) ? edge.getVertexB() : edge.getVertexA());
        return hs;
    }


    public Set<E> getEdges(V vertex) {
        checkContained(vertex);
        return edgeList.get(vertex);
    }


    public int order() {
        return edgeList.size();
    }


    public int size() {
        return size;
    }

}
