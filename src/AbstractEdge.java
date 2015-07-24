import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractEdge<T> {
    private T vertex1;
    private T vertex2;


    public List<T> getEndpoints() {
        ArrayList<T> al = new ArrayList<>();
        al.add(vertex1);
        al.add(vertex2);
        return al;
    }

    protected T getVertex1() { return vertex1; }
    protected T getVertex2() { return vertex2; }
}
