import java.util.HashMap;
import java.util.HashSet;

// possible implementation
public abstract class AbstractGraphT<V, E extends Edge<V>> implements InterfaceGraph<V,E> {
    private HashMap<V, EdgeList> list;

    AbstractGraphT() {
        list = new HashMap<>();
    }

    public void add(V vertex) {
        list.put(vertex, new EdgeList("Graph"));
    }


     private class EdgeList {
        EdgeList(String type) {
            switch (type) {
                case "Simple": {
                    edges = new HashMap<>();
                    break;
                }
                case "Graph": {
                    edges = new HashMap<>();
                    loops = new HashMap<>();
                    break;
                }
            }
        }

      /*  void initialiseLoops() {
            loops = new HashMap<>();
        }*/

        HashMap<V, HashSet<E>> edges;
        HashMap<V, HashSet<E>> loops;
    }
}
