package undirected_graphs.integer_undirected_graph;

import java.util.*;

public class SearchBreadthFirst implements Search{
    
    private UndirectedGraph graph;
    private Set<Integer> markSet;

    public SearchBreadthFirst(UndirectedGraph g){
        graph = g;
        markSet = new HashSet<>();
    }

    public boolean marked(int v){
        return markSet.contains(v);
    }

    public int count(int source){
        markSet.add(source);
        int total = 1;
        try {
            for( int v : graph.adj(source))
                if(!markSet.contains(v))
                    total += count(v);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }



}
