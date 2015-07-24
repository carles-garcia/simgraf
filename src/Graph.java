public interface Graph<V, E extends AbstractEdge> {
    void add(V vertex);
    void remove(V Vertex);

    void add(V vertex1, V vertex2);
    void remove(V vertex1, V vertex2);
    void add(E edge);
    void remove(E edge);
    void getEdge(V vertex1, V vertex2);

    boolean areAdjacent(V vertex1, V vertex2);
    boolean contains(V vertex);

}
