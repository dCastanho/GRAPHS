package undirected_graphs.generic_graph;

import undirected_graphs.exceptions.NonexistentVertexException;

public interface GenUndirectedGraph<T> {
    
    /**
     * Adds a new vertex to the graph with no edges connected to it. Doesn't add if there is already an equal vertex, meaning, if there is
     * at least one vertex v in the graph where {@code v.equals(vertex)} is true, vertex isn't added.
     * @param vertex new vertex to add to the graph.
     * @return true if it successfully adds the vertex, false if it doesn't because there is already an equal vertex.
     */
    boolean addVertex(T vertex);

    /**
     * Adds an edge between two vertices, even if there is already one or if they are the same, meaning parallel edges and self-loops are possible.
     * @param vertexOne one end of the edge.
     * @param vertexTwo the other end of the edge.
     * @throws NonexistentVertexException if either {@code vertexOne} or {@code vertexTwo} don't exist in the graph.
     */
    void addEdge(T vertexOne, T vertexTwo) throws NonexistentVertexException;

    /**
     * Shows how many vertices are in the graph.
     * @return an int with the number of vertices in the graph.
     */
    int nVertices();

    /**
     * Shows how many edges are in the graph.
     * @return an int with the number of edges in the graph.
     */
    int nEdges();

    /**
     * Gets all the vertices of the graph.
     * @return an iterable collection with all the vertices, in no particular order.
     */
    Iterable<T> getVertices();
    
    /**
     * Gets all the vertices adjacent to a given vertex, meaning, all vertices that share an edge with the given vertex.
     * @param vertex the given vertex
     * @return an iterable collection with the adjacent vertices 
     * @throws NonexistentVertexException if the give vertex is not in the graph
     */
    Iterable<T> adj(T vertex) throws NonexistentVertexException;

    /**
     * Checks whether or not a given vertex is already on the graph or not
     * @param vertex we want to check
     * @return true if it already exists on the graph, false if it doesn't
     */
    boolean exists(T vertex);

    /**
     * Finds a path between two given vertices if there is one, no guarantee it is the shortest path.
     * @param vertexOne one end of the path
     * @param vertexTwo the other end of the path
     * @return an iterable collection which contains the path, it is ordered from {@code vertexTwo} to {@code vertexOne} or is {@code null} if there is no path
     * @throws NonexistentVertexException if either one of the vertices doesn't exist in the graph.
     */
    Iterable<T> getPath(T vertexOne, T vertexTwo) throws NonexistentVertexException;

    /**
     * Finds the shortest path between two given vertices, if there is one. 
     * @param vertexOne one end of the path
     * @param vertexTwo the other end of the path
     * @return an iterable collection which contains the path, it is ordered from {@code vertexTwo} to {@code vertexOne} or is {@code null} if there is no path
     * @throws NonexistentVertexException
     */
    Iterable<T> getShortestPath(T vertexOne, T vertexTwo) throws NonexistentVertexException;

    /**
     * Gets the connected components of the graph.
     * @return an Iterable collection of iterable collections, where each "sub-collection" represents a connected component.
     */
    Iterable<Iterable<T>> getConnectedComponents();

}
