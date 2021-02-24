package undirected_graphs.integer_undirected_graph;

public interface CycleFinder {
    
    /**
     * Finds a cycle in the graph, if there is one
     * @return an Iterable with the cycle, or null if there is none
     */
    Iterable<Integer> findCycle();
}
