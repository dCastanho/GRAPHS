package undirected_graphs.integer_undirected_graph;
import undirected_graphs.exceptions.OutOfBoundsVertexException;

/**
 * UndirectedGraph
 */
public interface UndirectedGraph {

    /**
     * Returns the number of vertices in the graph
     * @return int with the number of vertices
     */
    int V();
    
    /**
     * Returns the number of edges in the graph
     * @return int with the number of edges
     */
    int E();

    /**
     * Adds an edge between vertices v and w
     * @param v vertex to which the edge will be connected
     * @param w vertex to which the edge will be connected
     */
    void addEdge(int v, int w) throws OutOfBoundsVertexException;

    /**
     * Returns an Iterable containing all the vertices adjacent to v, in other words, all vertices that share an edge with v
     * @param v vertex to get all the adjacent vertices from
     * @return Iterable with the vertices
     */
    Iterable<Integer> adj(int v) throws OutOfBoundsVertexException;

    /**
     * Checks whether or not the given vertex is invalid
     * @param v vertex to check
     * @return true if it's invalid, false if it's valid
     */
    boolean invalidVertex(int v);
}