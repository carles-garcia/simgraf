import java.util.ArrayList;
import java.util.List;

public class Edge<V> {
    private V vertex1;
    private V vertex2;

    public Edge(V vertex1, V vertex2) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }

    public List<V> getEndpoints() {
        ArrayList<V> al = new ArrayList<>();
        al.add(vertex1);
        al.add(vertex2);
        return al;
    }

    public V getVertex1() { return vertex1; }
    public V getVertex2() { return vertex2; }
}
