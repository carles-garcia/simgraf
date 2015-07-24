import java.util.ArrayList;
import java.util.HashMap;

public abstract class ListGraph<V, E extends AbstractEdge> implements Graph {
    HashMap<V, E> edgeList;

}
