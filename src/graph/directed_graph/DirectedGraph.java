package graph.directed_graph;

import graph.Graph;
import graph.exceptions.NonexistentVertexException;

public interface DirectedGraph<T> extends Graph<T> {
    
    /**
     * Returns a topological sort of the digraph, meaning a collection, where one element's edges all point to elements on it's right
     * The topological sort is only correct if there are no cycles in the graph.
     * @return an iterable collection with the topological sort
     */
    Iterable<T> topologicalSort();

    /**
     * Checks whether vertexOne and vertexTwo are strongly connected, which means, there is a path from vertexOne to vertexTwo and a path from vertexTwo to vertexOne
     * @param vertexOne one of the vertices to check
     * @param vertexTwo the other vertex to check
     * @return true if they are strongly connected, false if they aren't
     * @throws NonexistentVertexException if at least one of the given vertices doesn't exist in the graph
     */
    boolean stronglyConnected(T vertexOne, T vertexTwo) throws NonexistentVertexException; 
    
    /**
     * Returns the graph's postorder
     * @return an iterable with the graph's postorder
     */
    Iterable<T> getPostorder();

    /**
     * Returns the graph's reverse postorder
     * @return an iterable with the graph's reverse postorder
     */
    Iterable<T> getReversePostorder();

    /**
     * Returns the graph's preorder
     * @return an iterable with the graph's preorder
     */
    Iterable<T> getPreorder();


    /**
     * Returns the reverse of the directed graph, which inverts de order of the edges, meaning that if there was an edge from v to w, the reverse graph has an edge from w to v
     * @return the reversed directed graph
     */
    DirectedGraph<T> reverse();

    /**
     * Gets all the strongly connected components in the graph, returning a collection of graphs, in which each graph is a strongly connected component
     * A strongly connected component is a graph in which all it's vertices are strongly connected
     * @return
     */
    Iterable<DirectedGraph<T>> stronglyConnectedComponents();

    /**
     * Gets all the vertices that are reachable from the given vertex, meaning that a vertex v will be in the returned collection if there is a path from {@code vertex} to v    * @param vertex
     * @return an iterable collection with all the reachable vertices
     * @throws NonexistentVertexException is the given vertex doesn't exist in the graph
     */
    Iterable<T> getReachable(T vertex) throws NonexistentVertexException;

    /**
     * Gets the graphs transitive closure, which is a directed graph in which 2 vertices v and w have an edge from v to w if there is a path from v to w in the original graph 
     * @return a directed graph with the transitive closure
     */
    DirectedGraph<T> transitiveClosure();
}
