# simgraf (CURRENTLY IN ALPHA)*
A simple graph library written in Java.
Easily extensible.

\* Graphs and algorithms are finished but haven't been tested thoroughly

This library contains 2 main packages:
* graphs: Contains 2 graph classes and two edge classes
    * DirectedGraph
    * UndirectedGraph
    * Edge
    * WeightedEdge


* algorithms: contains 4 algorithms for the graphs
    * BFS (Breadth-First Search)
    * DFS (Depth-First Search)
    * Bellman-Ford
    * Dijkstra

For each graph the user can choose if loops are allowed and if multiple edges between two vertices are allowed.

The vertices can be of any type and the edges must be of class Edge/WeightedEdge or a subclass of them.

###### History
v0.1.0 Start of development - 24/7/15

v0.2.0 Alpha release - 2/8/15
