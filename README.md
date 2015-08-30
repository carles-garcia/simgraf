# simgraf
A simple graph library written in Java.
Easily extensible.

Unlike other popular Java graph libraries, <b>simgraf</b> is not aimed to graph visualization. It is aimed to provide a versatile set of graph data structures and algorithms focused on time efficiency.  

This library contains 2 main packages:
* graphs: Contains 2 graph classes and 2 edge classes
    * DirectedGraph
    * UndirectedGraph
    * Edge
    * WeightedEdge


* algorithms: Contains 4 algorithms for the graphs
    * BFS (Breadth-First Search)
    * DFS (Depth-First Search)
    * Bellman-Ford
    * Dijkstra

For each graph the user can choose if loops are allowed and if multiple edges between two vertices are allowed.

The vertices can be of any type and the edges must be of class Edge/WeightedEdge or a subclass of them.

###### Development & contributing

The library is ready for production. It has been thoroughly tested and documented.

If you have coded an algorithm for this library I encourage you to do a pull request.

###### License

BSD 3-Clause License
