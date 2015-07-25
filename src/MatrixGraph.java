import java.util.ArrayList;
import java.util.HashMap;

public abstract class MatrixGraph<V, E extends Edge> implements Graph<V, E> {
    private HashMap<V,Integer> vertices;
    private ArrayList<ArrayList<Integer>> adjacencyMatrix;
    private ArrayList<E> edges;
    private int nextId;

    public MatrixGraph() {
        vertices = new HashMap<>();
        adjacencyMatrix = new ArrayList<>();
        edges = new ArrayList<>();
        nextId = 0;
    }

    public void add(V vertex) {
        // if not contained put
        vertices.put(vertex, ++nextId);
      //  adjacencyMatrix.add(nextId);

    }
}
