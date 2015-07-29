import java.util.HashMap;
import java.util.HashSet;

// possible implementation
public class GraphT<V, E extends Edge<V>> implements InterfaceGraph<V,E> {
    private HashMap<V, HashMap<V,EdgeList>> list;
    private int size; // number of edges
    private EdgeType edgeType;
    private boolean loopsAllowed;
    private boolean multigraph;

    public GraphT(EdgeType edgeType, boolean loopsAllowed, boolean multigraph) {
        list = new HashMap<>();
    }

    public void add(V vertex) {
        list.put(vertex, new EdgeList("Graph"));
    }


     private class EdgeList {
         HashSet<E> edges;
         HashSet<E> loops;
         HashSet<E> incoming;
         HashSet<E> outgoing;

         EdgeList() {
             if (edgeType == null || edgeType == EdgeType.DIRECTED) {
                 incoming = new HashSet<>();
                 outgoing = new HashSet<>();
             }
             else edges = new HashSet<>();
             if (loopsAllowed) loops = new HashSet<>();
         }

        /* EdgeList(String type) {
            switch (type) {
                case "Simple": {
                    edges = new HashSet<>();
                    break;
                }
                case "Graph": {
                    edges = new HashSet<>();
                    loops = new HashSet<>();
                    break;
                }
                case "Directed": {
                    incoming = new HashSet<>();
                    outgoing = new HashSet<>();
                    break;
                }
            }
        }*/

      /*  void initialiseLoops() {
            loops = new HashMap<>();
        }*/


    }
}
