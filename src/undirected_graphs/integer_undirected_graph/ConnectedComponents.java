package undirected_graphs.integer_undirected_graph;

import undirected_graphs.exceptions.OutOfBoundsVertexException;

public interface ConnectedComponents{

    /**
     * Checks whether two given vertices are connected
     * @param v one of the given vertices
     * @param w one of the given vertices
     * @return true if they are, false if they aren't
     * @throws OutOfBoundsVertexException if at least one of the vertices isn't valid
     */
    boolean isConnected(int v, int w) throws OutOfBoundsVertexException;

    /**
     * Counts the total number of connected components in the graph
     * @return an int with the total number
     */
    int count();

    /**
     * Returns the id of the component containing the vertex v
     * @param v vertex
     * @return an int which represents the id of the component
     */
    int id(int v);
}