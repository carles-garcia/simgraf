import java.util.ArrayList;
import java.util.HashMap;

abstract public class MatrixGraph<V,E extends AbstractEdge> {
    HashMap<V,Integer> vertices;
    ArrayList<ArrayList<Integer>> adjacencyMatrix;
    ArrayList<E> edges;
}
