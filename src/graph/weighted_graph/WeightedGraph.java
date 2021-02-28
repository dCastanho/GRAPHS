package graph.weighted_graph;

import graph.exceptions.NonexistentVertexException;

public interface WeightedGraph<T> {

    /**
     * Adds a new vertex to the graph with no edges connected to it. Doesn't add if there is already an equal vertex, meaning, if there is
     * at least one vertex v in the graph where {@code v.equals(vertex)} is true, vertex isn't added.
     * @param vertex new vertex to add to the graph.
     * @return true if it successfully adds the vertex, false if it doesn't because there is already an equal vertex.
     */
    boolean addVertex(T vertex);

    /**
     * Adds a new edge to the graph between two given vertices with a given weight. The edges has no direction.
     * @param v one of the vertices 
     * @param w second vertex
     * @param weight weight of the edge
     * @throws NonexistentVertexException if either one of the given vertices doesn't exist in the graph
     */
    void addEdge(T v, T w, int weight) throws NonexistentVertexException;


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
     * Checks whether or not a given vertex is already on the graph or not
     * @param vertex we want to check
     * @return true if it already exists on the graph, false if it doesn't
     */
    boolean exists(T vertex);

    /**
     * Gets all the edges incident on the given vertex
     * @param vertex we want the edges of 
     * @return an iterable collection containing the edges
     * @throws NonexistentVertexException if the given vertex doesn't exist in the graph
     */
    Iterable<Edge<T>> adj(T vertex) throws NonexistentVertexException;



}
