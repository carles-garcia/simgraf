package test;

import graphs.DirectedGraph;
import graphs.Edge;
import graphs.UndirectedGraph;
import graphs.WeightedEdge;

public class GraphTest {
    DirectedGraph<Integer, WeightedEdge<Integer>> digraph1 = new DirectedGraph<>(false,false);
    DirectedGraph<Integer, WeightedEdge<Integer>> digraph2 = new DirectedGraph<>(false,false);

    DirectedGraph<Integer, WeightedEdge<Integer>> digraph1L = new DirectedGraph<>(true,false);
    DirectedGraph<Integer, WeightedEdge<Integer>> digraph2L = new DirectedGraph<>(true,false);

    DirectedGraph<Integer, WeightedEdge<Integer>> digraph1M = new DirectedGraph<>(false,true);
    DirectedGraph<Integer, WeightedEdge<Integer>> digraph2M = new DirectedGraph<>(false,true);

    DirectedGraph<Integer, WeightedEdge<Integer>> digraph1LM = new DirectedGraph<>(true,true);
    DirectedGraph<Integer, WeightedEdge<Integer>> digraph2LM = new DirectedGraph<>(true,true);

    UndirectedGraph<Integer, Edge<Integer>> graph1 = new UndirectedGraph<>(false,false);
    UndirectedGraph<Integer, Edge<Integer>> graph2 = new UndirectedGraph<>(false,false);

    UndirectedGraph<Integer, Edge<Integer>> graph1L = new UndirectedGraph<>(true,false);
    UndirectedGraph<Integer, Edge<Integer>> graph2L = new UndirectedGraph<>(true,false);

    UndirectedGraph<Integer, Edge<Integer>> graph1M = new UndirectedGraph<>(false,true);
    UndirectedGraph<Integer, Edge<Integer>> graph2M = new UndirectedGraph<>(false,true);

    UndirectedGraph<Integer, Edge<Integer>> graph1LM = new UndirectedGraph<>(true,true);
    UndirectedGraph<Integer, Edge<Integer>> graph2LM = new UndirectedGraph<>(true,true);

    public GraphTest() {
        for (int i = 1; i <= 5; ++i) {
            digraph1.add(i);
            digraph1L.add(i);
            digraph1M.add(i);
            digraph1LM.add(i);
            graph1.add(i);
            graph1L.add(i);
            graph1M.add(i);
            graph1LM.add(i);
        }
        for (int i = 1; i <= 10; ++i) {
            digraph2.add(i);
            digraph2L.add(i);
            digraph2M.add(i);
            digraph2LM.add(i);
            graph2.add(i);
            graph2L.add(i);
            graph2M.add(i);
            graph2LM.add(i);
        }
        initialise1();
        initialise2();

    }

    private void initialise1() {
        graph1.add(new Edge<Integer>(1,2));
        graph1L.add(new Edge<Integer>(1,2));
        graph1M.add(new Edge<Integer>(1,2));
        graph1LM.add(new Edge<Integer>(1,2));
        digraph1.add(new WeightedEdge<Integer>(1,2,-1.0));
        digraph1L.add(new WeightedEdge<Integer>(1,2,-1.0));
        digraph1M.add(new WeightedEdge<Integer>(1,2,-1.0));
        digraph1LM.add(new WeightedEdge<Integer>(1,2,-1.0));

        graph1.add(new Edge<Integer>(3,2));
        graph1L.add(new Edge<Integer>(3,2));
        graph1M.add(new Edge<Integer>(3,2));
        graph1LM.add(new Edge<Integer>(3,2));
        digraph1.add(new WeightedEdge<Integer>(3,2,1.0));
        digraph1L.add(new WeightedEdge<Integer>(3,2,1.0));
        digraph1M.add(new WeightedEdge<Integer>(3,2,1.0));
        digraph1LM.add(new WeightedEdge<Integer>(3,2,1.0));

        graph1.add(new Edge<Integer>(3,1));
        graph1L.add(new Edge<Integer>(3,1));
        graph1M.add(new Edge<Integer>(3,1));
        graph1LM.add(new Edge<Integer>(3,1));
        digraph1.add(new WeightedEdge<Integer>(3,1,1.0));
        digraph1L.add(new WeightedEdge<Integer>(3,1,1.0));
        digraph1M.add(new WeightedEdge<Integer>(3,1,1.0));
        digraph1LM.add(new WeightedEdge<Integer>(3,1,1.0));

        graph1.add(new Edge<Integer>(3,4));
        graph1L.add(new Edge<Integer>(3,4));
        graph1M.add(new Edge<Integer>(3,4));
        graph1LM.add(new Edge<Integer>(3,4));
        digraph1.add(new WeightedEdge<Integer>(3,4,-5.0));
        digraph1L.add(new WeightedEdge<Integer>(3,4,-5.0));
        digraph1M.add(new WeightedEdge<Integer>(3,4,-5.0));
        digraph1LM.add(new WeightedEdge<Integer>(3,4,-5.0));

        graph1.add(new Edge<Integer>(4,5));
        graph1L.add(new Edge<Integer>(4,5));
        graph1M.add(new Edge<Integer>(4,5));
        graph1LM.add(new Edge<Integer>(4,5));
        digraph1.add(new WeightedEdge<Integer>(4,5,6.0));
        digraph1L.add(new WeightedEdge<Integer>(4,5,6.0));
        digraph1M.add(new WeightedEdge<Integer>(4,5,6.0));
        digraph1LM.add(new WeightedEdge<Integer>(4,5,6.0));

        graph1.add(new Edge<Integer>(1,5));
        graph1L.add(new Edge<Integer>(1,5));
        graph1M.add(new Edge<Integer>(1,5));
        graph1LM.add(new Edge<Integer>(1,5));
        digraph1.add(new WeightedEdge<Integer>(1,5,3.0));
        digraph1L.add(new WeightedEdge<Integer>(1,5,3.0));
        digraph1M.add(new WeightedEdge<Integer>(1,5,3.0));
        digraph1LM.add(new WeightedEdge<Integer>(1,5,3.0));

        //loops
        graph1L.add(new Edge<Integer>(1,1));
        graph1LM.add(new Edge<Integer>(1,1));
        digraph1L.add(new WeightedEdge<Integer>(1,1,-4.0));
        digraph1LM.add(new WeightedEdge<Integer>(1,1,-4.0));
        //multis
        graph1M.add(new Edge<Integer>(4,3));
        graph1LM.add(new Edge<Integer>(4,3));
        digraph1M.add(new WeightedEdge<Integer>(4,3,1.0));
        digraph1LM.add(new WeightedEdge<Integer>(4,3,1.0));
    }

    private void initialise2() {
        graph2.add(new Edge<Integer>(1,2));
        graph2L.add(new Edge<Integer>(1,2));
        graph2M.add(new Edge<Integer>(1,2));
        graph2LM.add(new Edge<Integer>(1,2));
        digraph2.add(new WeightedEdge<Integer>(1,2,-1.0));
        digraph2L.add(new WeightedEdge<Integer>(1,2,-1.0));
        digraph2M.add(new WeightedEdge<Integer>(1,2,-1.0));
        digraph2LM.add(new WeightedEdge<Integer>(1,2,-1.0));

        graph2.add(new Edge<Integer>(3,2));
        graph2L.add(new Edge<Integer>(3,2));
        graph2M.add(new Edge<Integer>(3,2));
        graph2LM.add(new Edge<Integer>(3,2));
        digraph2.add(new WeightedEdge<Integer>(3,2,1.0));
        digraph2L.add(new WeightedEdge<Integer>(3,2,1.0));
        digraph2M.add(new WeightedEdge<Integer>(3,2,1.0));
        digraph2LM.add(new WeightedEdge<Integer>(3,2,1.0));

        graph2.add(new Edge<Integer>(3,1));
        graph2L.add(new Edge<Integer>(3,1));
        graph2M.add(new Edge<Integer>(3,1));
        graph2LM.add(new Edge<Integer>(3,1));
        digraph2.add(new WeightedEdge<Integer>(3,1,1.0));
        digraph2L.add(new WeightedEdge<Integer>(3,1,1.0));
        digraph2M.add(new WeightedEdge<Integer>(3,1,1.0));
        digraph2LM.add(new WeightedEdge<Integer>(3,1,1.0));

        graph2.add(new Edge<Integer>(3,4));
        graph2L.add(new Edge<Integer>(3,4));
        graph2M.add(new Edge<Integer>(3,4));
        graph2LM.add(new Edge<Integer>(3,4));
        digraph2.add(new WeightedEdge<Integer>(3,4,-5.0));
        digraph2L.add(new WeightedEdge<Integer>(3,4,-5.0));
        digraph2M.add(new WeightedEdge<Integer>(3,4,-5.0));
        digraph2LM.add(new WeightedEdge<Integer>(3,4,-5.0));

        graph2.add(new Edge<Integer>(4,5));
        graph2L.add(new Edge<Integer>(4,5));
        graph2M.add(new Edge<Integer>(4,5));
        graph2LM.add(new Edge<Integer>(4,5));
        digraph2.add(new WeightedEdge<Integer>(4,5,6.0));
        digraph2L.add(new WeightedEdge<Integer>(4,5,6.0));
        digraph2M.add(new WeightedEdge<Integer>(4,5,6.0));
        digraph2LM.add(new WeightedEdge<Integer>(4,5,6.0));

        graph2.add(new Edge<Integer>(1,5));
        graph2L.add(new Edge<Integer>(1,5));
        graph2M.add(new Edge<Integer>(1,5));
        graph2LM.add(new Edge<Integer>(1,5));
        digraph2.add(new WeightedEdge<Integer>(1,5,3.0));
        digraph2L.add(new WeightedEdge<Integer>(1,5,3.0));
        digraph2M.add(new WeightedEdge<Integer>(1,5,3.0));
        digraph2LM.add(new WeightedEdge<Integer>(1,5,3.0));
        
        
        // for graph 2
        graph2.add(new Edge<Integer>(6,3));
        graph2L.add(new Edge<Integer>(6,3));
        graph2M.add(new Edge<Integer>(6,3));
        graph2LM.add(new Edge<Integer>(6,3));
        digraph2.add(new WeightedEdge<Integer>(6,3,2.0));
        digraph2L.add(new WeightedEdge<Integer>(6,3,2.0));
        digraph2M.add(new WeightedEdge<Integer>(6,3,2.0));
        digraph2LM.add(new WeightedEdge<Integer>(6,3,2.0));

        graph2.add(new Edge<Integer>(6,7));
        graph2L.add(new Edge<Integer>(6,7));
        graph2M.add(new Edge<Integer>(6,7));
        graph2LM.add(new Edge<Integer>(6,7));
        digraph2.add(new WeightedEdge<Integer>(6,7,3.0));
        digraph2L.add(new WeightedEdge<Integer>(6,7,3.0));
        digraph2M.add(new WeightedEdge<Integer>(6,7,3.0));
        digraph2LM.add(new WeightedEdge<Integer>(6,7,3.0));

        graph2.add(new Edge<Integer>(6,8));
        graph2L.add(new Edge<Integer>(6,8));
        graph2M.add(new Edge<Integer>(6,8));
        graph2LM.add(new Edge<Integer>(6,8));
        digraph2.add(new WeightedEdge<Integer>(6,8,3.0));
        digraph2L.add(new WeightedEdge<Integer>(6,8,3.0));
        digraph2M.add(new WeightedEdge<Integer>(6,8,3.0));
        digraph2LM.add(new WeightedEdge<Integer>(6,8,3.0));

        graph2.add(new Edge<Integer>(10,9));
        graph2L.add(new Edge<Integer>(10,9));
        graph2M.add(new Edge<Integer>(10,9));
        graph2LM.add(new Edge<Integer>(10,9));
        digraph2.add(new WeightedEdge<Integer>(10,9,1.0));
        digraph2L.add(new WeightedEdge<Integer>(10,9,1.0));
        digraph2M.add(new WeightedEdge<Integer>(10,9,1.0));
        digraph2LM.add(new WeightedEdge<Integer>(10,9,1.0));
        
        //loops
        graph2L.add(new Edge<Integer>(1,1));
        graph2LM.add(new Edge<Integer>(1,1));
        digraph2L.add(new WeightedEdge<Integer>(1,1,-4.0));
        digraph2LM.add(new WeightedEdge<Integer>(1,1,-4.0));

        
        //multis
        graph2M.add(new Edge<Integer>(4,3));
        graph2LM.add(new Edge<Integer>(4,3));
        digraph2M.add(new WeightedEdge<Integer>(4,3,10.0));
        digraph2LM.add(new WeightedEdge<Integer>(4,3,10.0));

        graph2LM.add(new Edge<Integer>(1,1));
        digraph2LM.add(new WeightedEdge<Integer>(1,1,1.0));

        graph2M.add(new Edge<Integer>(6,7));
        graph2LM.add(new Edge<Integer>(6,7));
        digraph2M.add(new WeightedEdge<Integer>(6,7,2.0));
        digraph2LM.add(new WeightedEdge<Integer>(6,7,2.0));
        graph2M.add(new Edge<Integer>(6,7));
        graph2LM.add(new Edge<Integer>(6,7));
        digraph2M.add(new WeightedEdge<Integer>(6,7,2.0));
        digraph2LM.add(new WeightedEdge<Integer>(6,7,2.0));

        graph2M.add(new Edge<Integer>(6,8));
        graph2LM.add(new Edge<Integer>(6,8));
        digraph2M.add(new WeightedEdge<Integer>(6,8,2.0));
        digraph2LM.add(new WeightedEdge<Integer>(6,8,2.0));
        graph2M.add(new Edge<Integer>(6,8));
        graph2LM.add(new Edge<Integer>(6,8));
        digraph2M.add(new WeightedEdge<Integer>(6,8,2.0));
        digraph2LM.add(new WeightedEdge<Integer>(6,8,2.0));
    }


    public DirectedGraph<Integer, WeightedEdge<Integer>> getDigraph1() {
        return digraph1;
    }

    public DirectedGraph<Integer, WeightedEdge<Integer>> getDigraph2() {
        return digraph2;
    }

    public DirectedGraph<Integer, WeightedEdge<Integer>> getDigraph1L() {
        return digraph1L;
    }

    public DirectedGraph<Integer, WeightedEdge<Integer>> getDigraph2L() {
        return digraph2L;
    }

    public DirectedGraph<Integer, WeightedEdge<Integer>> getDigraph1M() {
        return digraph1M;
    }

    public DirectedGraph<Integer, WeightedEdge<Integer>> getDigraph2M() {
        return digraph2M;
    }

    public DirectedGraph<Integer, WeightedEdge<Integer>> getDigraph1LM() {
        return digraph1LM;
    }

    public DirectedGraph<Integer, WeightedEdge<Integer>> getDigraph2LM() {
        return digraph2LM;
    }

    public UndirectedGraph<Integer, Edge<Integer>> getGraph1() {
        return graph1;
    }

    public UndirectedGraph<Integer, Edge<Integer>> getGraph2() {
        return graph2;
    }

    public UndirectedGraph<Integer, Edge<Integer>> getGraph1L() {
        return graph1L;
    }

    public UndirectedGraph<Integer, Edge<Integer>> getGraph2L() {
        return graph2L;
    }

    public UndirectedGraph<Integer, Edge<Integer>> getGraph1M() {
        return graph1M;
    }

    public UndirectedGraph<Integer, Edge<Integer>> getGraph2M() {
        return graph2M;
    }

    public UndirectedGraph<Integer, Edge<Integer>> getGraph1LM() {
        return graph1LM;
    }

    public UndirectedGraph<Integer, Edge<Integer>> getGraph2LM() {
        return graph2LM;
    }
}
