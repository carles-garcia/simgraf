package graphs;

import java.util.ArrayList;
import java.util.List;

public class Edge<V> {
    private V vertexA;
    private V vertexB;

    public Edge(V vertexA, V vertexB) {
        this.vertexA = vertexA;
        this.vertexB = vertexB;
        }

    public List<V> getEndpoints() {
        ArrayList<V> al = new ArrayList<>();
        al.add(vertexA);
        al.add(vertexB);
        return al;
    }

    public V getVertexA() {
        return vertexA;
    }

    public V getVertexB() {
        return vertexB;
    }

}
