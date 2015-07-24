import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

abstract public class AbstractEdge<T> {
    private T vertex1;
    private T vertex2;
    private EdgeType edgeType;
    private Double weight;
    static private Double defaultWeight;

    public List<T> getEndpoints() {
        ArrayList<T> al = new ArrayList<>();
        al.add(vertex1);
        al.add(vertex2);
        return al;
    }

    public T getVertex1() { return vertex1; }
    public T getVertex2() { return vertex2; }
}
