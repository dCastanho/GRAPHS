package undirected_graphs.integer_undirected_graph;

import undirected_graphs.exceptions.OutOfBoundsVertexException;

public interface Search {
    /**
     * Checks whether a given vertex has been marked, meaning, has been visited by the search
     * @param v vertex to check
     * @return true if it has been marked, false if it hasn't
     */
    boolean marked(int v);

    /**
     * Returns the number of vertices that's possible to reach from a given source vertex
     * @param v source vertex
     * @return int with the number of vertices one can reach from the source
     */
    int count(int v) throws OutOfBoundsVertexException;
}
