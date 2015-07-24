import java.util.ArrayList;
import java.util.HashMap;

public abstract class MatrixGraph<V,E extends AbstractEdge> implements Graph{
    HashMap<V,Integer> vertices;
    ArrayList<ArrayList<Integer>> adjacencyMatrix;
    ArrayList<E> edges;
}
