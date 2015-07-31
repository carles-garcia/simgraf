import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// possible implementation
public class GraphT<V, E extends Edge<V>> implements InterfaceGraph<V,E> {
    private HashMap<V, HashMap<V,EdgeList>> list;
    private int size; // number of edges
    private EdgeType graphType;
    private boolean loopsAllowed;
    private boolean multigraph;

    private class EdgeList {
        HashSet<E> edges;
        HashSet<E> incoming;
        HashSet<E> outgoing;
        int edgeLoops; //instead of another hashset
        int arcLoops;

        EdgeList() {
            if (graphType == null || graphType == EdgeType.DIRECTED) {
                incoming = new HashSet<>();
                outgoing = new HashSet<>();
            }
            else edges = new HashSet<>();
            //if (loopsAllowed) loops = new HashSet<>();
            edgeLoops = arcLoops = 0;
        }

    }

    /*
        If the vertex isn't contained in the graph (and this is true for a null reference) throws
        an IllegalArgumenException
         */
    private void checkContained(V vertex) {
        if (!contains(vertex)) throw new IllegalArgumentException(Errors.VERTEX_NOT_CONTAINED.toString());
    }

    public GraphT(EdgeType graphType, boolean loopsAllowed, boolean multigraph)
    {
        list = new HashMap<>();
        size = 0;
        this.graphType = graphType;
        this.loopsAllowed = loopsAllowed;
        this.multigraph = multigraph;
    }

    public void add(V vertex) {
        Objects.requireNonNull(vertex, Errors.ADD_NULL_VERTEX.toString());
        if (!list.containsKey(vertex)) list.put(vertex, new HashMap<V, EdgeList>());
        else throw new IllegalArgumentException(Errors.VERTEX_CONTAINED.toString());
    }

    public void remove(V vertex) {
        Objects.requireNonNull(vertex, Errors.VERTEX_NOT_CONTAINED.toString());
        for (V adjV : list.get(vertex).keySet()) {
            list.get(adjV).remove(vertex);
        }
        list.remove(vertex);
    }

    public void add(E edge) {
        Objects.requireNonNull(edge, Errors.ADD_NULL_EDGE.toString());
        if (graphType != EdgeType.MIXED && edge.getType() != graphType)
            throw new IllegalArgumentException(Errors.WRONG_EDGE_TYPE.toString());

        if (!loopsAllowed && edge.getVertexA().equals(edge.getVertexB()))
            throw new IllegalArgumentException(Errors.LOOPS_NOT_ALLOWED.toString());

        boolean adjacent = areAdjacent(edge.getVertexA(), edge.getVertexB());
        if (multigraph || !adjacent) {
            if (!adjacent) {
                list.get(edge.getVertexA()).put(edge.getVertexB(),new EdgeList());
                list.get(edge.getVertexB()).put(edge.getVertexA(),new EdgeList());
            }
            if (edge.getType() == EdgeType.UNDIRECTED) {
                if (!list.get(edge.getVertexA()).get(edge.getVertexB()).edges.add(edge))
                    throw new IllegalArgumentException(Errors.EDGE_CONTAINED.toString());
                list.get(edge.getVertexB()).get(edge.getVertexA()).edges.add(edge);
            }
            else {
                if (!list.get(edge.getVertexA()).get(edge.getVertexB()).outgoing.add(edge))
                    throw new IllegalArgumentException(Errors.EDGE_CONTAINED.toString());
                list.get(edge.getVertexB()).get(edge.getVertexA()).incoming.add(edge);
            }
            ++size;
        }
        else throw new IllegalArgumentException(Errors.ADD_EXISTING_EDGE.toString());
    }

    public void remove(E edge) {
        // must update adjacencies
        Objects.requireNonNull(edge, Errors.REMOVE_NULL_EDGE.toString());
        if (!areAdjacent(edge.getVertexA(), edge.getVertexB()))
            throw new IllegalArgumentException(Errors.EDGE_NOT_CONTAINED.toString());
        if (graphType != EdgeType.MIXED && edge.getType() != graphType)
            throw new IllegalArgumentException(Errors.WRONG_EDGE_TYPE.toString());

        if (edge.getType() == EdgeType.UNDIRECTED) {
            if (!list.get(edge.getVertexA()).get(edge.getVertexB()).edges.remove(edge))
                throw new IllegalArgumentException(Errors.EDGE_NOT_CONTAINED.toString());
        }
        else {
            if (!list.get(edge.getVertexA()).get(edge.getVertexB()).outgoing.remove(edge))
                throw new IllegalArgumentException(Errors.EDGE_NOT_CONTAINED.toString());
            list.get(edge.getVertexB()).get(edge.getVertexA()).incoming.remove(edge);
        }

    }

    public E getEdge(V vertexA, V vertexB) {
        if (!areAdjacent(vertexA,vertexB))
            throw new IllegalArgumentException(Errors.NOT_ADJACENT.toString());
        if (graphType == EdgeType.UNDIRECTED) {
            return list.get(vertexA).get(vertexB).edges.iterator().next();
        }
        else {
            if (list.get(vertexA).get(vertexB).incoming.isEmpty())
                return list.get(vertexA).get(vertexB).outgoing.iterator().next();
            else return list.get(vertexA).get(vertexB).incoming.iterator().next();
        }
    }

    public boolean areAdjacent(V vertexA, V vertexB) {
        checkContained(vertexA);
        checkContained(vertexB);
        return list.get(vertexA).containsKey(vertexB);
    }

    public boolean contains(V vertex) {
        return list.containsKey(vertex);
    }

    public Set<E> getEdges() {
        HashSet<E> hs = new HashSet<>();
        for (HashMap<V,EdgeList> hm : list.values()) {
            for (EdgeList el : hm.values()) {
                if (graphType == EdgeType.MIXED || graphType == EdgeType.UNDIRECTED)
                    hs.addAll(el.edges);
                if (graphType == EdgeType.MIXED || graphType == EdgeType.DIRECTED) {
                    hs.addAll(el.incoming);
                    hs.addAll(el.outgoing);
                }
            }
        }
        return hs;
    }

    public int degree(V vertex) {

    }

    public Set<V> getNeighbours(V vertex) {

    }

    public Set<E> getEdges(V vertex) {

    }

    public int order() {
        return list.size();
    }

    public int size() {
        return size;
    }

    public int indegree(V vertex) {

    }

    public int outdegree(V vertex) {

    }

    public Set<E> getIncomingArcs(V vertex) {
        HashSet<E> hs = new HashSet<>();
        if (graphType == EdgeType.MIXED || graphType = EdgeType.DIRECTED) {
            for
        }

    }

    public Set<E> getOutgoingArcs(V vertex) {

    }

    public Set<E> getEdges(V vertexA, V vertexB) {

    }

    public int numberOfEdges(V vertexA, V vertexB) {

    }
}
