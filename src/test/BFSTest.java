package test;

import algorithms.BFS;
import graphs.DirectedGraph;
import graphs.Edge;
import graphs.UndirectedGraph;

public class BFSTest {
    public static void main(String[] args) {
        UndirectedGraph<Integer, Edge<Integer>> g = new UndirectedGraph<>(false, false);
        for (int i = 0; i < 5; ++i) {
            g.add(i);
        }
        g.add(new Edge<Integer>(0,1));
        g.add(new Edge<Integer>(1,2));
        g.add(new Edge<Integer>(2,3));
        g.add(new Edge<Integer>(3,4));
        g.add(new Edge<Integer>(4,0));
        g.add(new Edge<Integer>(1,3));

        BFS<Integer> bfs = new BFS<Integer>(g, 0);
        for (int i = 0; i < 5; ++i) {
            System.out.print("Distance to " + i + ": ");
            System.out.println(bfs.getDistances().get(i));
        }


            DirectedGraph<Double, Edge<Double>> dg = new DirectedGraph<>(false, false);
            BFS<Double> bfs2 = new BFS<Double>(dg, .0);




    }
}
