import java.util.Collection;

public class BaseGraphIm<V, E extends AbstractEdge<V>> {
    private BaseGraphS<V,E> graph;

    public void add(V vertex) {
        graph.add(vertex);
    }

    public void remove(V vertex) {
        graph.remove(vertex);
    }

    public void add(E edge) {
        graph.add(edge);
    }

    public void remove(E edge) {
        graph.remove(edge);
    }

    public E getEdge(V vertexA, V vertexB) {
        return graph.getEdge(vertexA,vertexB);
    }

    public boolean areAdjacent(V vertexA, V vertexB) {
        return graph.areAdjacent(vertexA,vertexB);
    }

    public boolean contains(V vertex) {
        return graph.contains(vertex);
    }

    public Collection<V> getVertices() {
        return graph.getVertices();
    }

    public Collection<E> getEdges() {
        return graph.getEdges();
    }

    public int degree(V vertex) {
        return graph.degree(vertex);
    }

    public Collection<V> getNeighbours(V vertex) {
        return graph.getNeighbours(vertex);
    }

    public Collection<E> getEdges(V vertex) {
        return graph.getEdges(vertex);
    }

    public int order() {
        return graph.order();
    }

    public int size() {
        return graph.size();
    }

}
