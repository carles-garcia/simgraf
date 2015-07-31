import java.util.ArrayList;
import java.util.List;

public class Edge<V> {
    private V vertexA;
    private V vertexB;
    private EdgeType edgeType;

    public Edge(V vertexA, V vertexB, EdgeType edgeType) {
        if (edgeType == EdgeType.MIXED) throw new IllegalArgumentException("An edge can't be mixed");
        this.vertexA = vertexA;
        this.vertexB = vertexB;
        this.edgeType = edgeType;
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

    public EdgeType getType() {
        return edgeType;
    }
}
