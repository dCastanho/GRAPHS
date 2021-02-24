package undirected_graphs.integer_undirected_graph;

import undirected_graphs.exceptions.OutOfBoundsVertexException;

public interface GraphPaths {
    
    /**
     * Checks whether there is a path between vertices v and w
     * @param v one of the ends of the path
     * @param w one of the ends of the path
     * @return true if there is, false if there isn't
     * @throws OutOfBoundsVertexException if one of the vertexes isn't valid
     */
    boolean hasPath(int v, int w) throws OutOfBoundsVertexException;

    /**
     * Returns a path between v and w, or null if there isn't one. It's guaranteed to be the shortest path.
     * @param v one of the ends of the path
     * @param w one of the ends of the path
     * @return an Iterable with the path or null if there isn't one
     * @throws OutOfBoundsVertexException if one of the vertexes isn't valid
     */
    Iterable<Integer> getPath(int v, int w) throws OutOfBoundsVertexException;

}
