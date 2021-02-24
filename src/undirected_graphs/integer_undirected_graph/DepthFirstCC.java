package undirected_graphs.integer_undirected_graph;

import java.util.*;

import undirected_graphs.exceptions.OutOfBoundsVertexException;

public class DepthFirstCC implements ConnectedComponents {

    private Map<Integer, Integer> sizes;
    private Map<Integer, Integer> id;
    private Set<Integer> markSet;
    private int count;
    private UndirectedGraph graph;

    public DepthFirstCC(UndirectedGraph g){
        graph = g;
        id = new HashMap<>(graph.V());
        sizes = new HashMap<>();
        markSet = new HashSet<>();
        count = 0;
        for(int i = 0; i < graph.V(); i++){
                sizes.put(count, 0);
            if(!markSet.contains(i)){
                try {
                    depthFirst(i);
                } catch (OutOfBoundsVertexException e) {
                    e.printStackTrace();
                }
                count++;
            }
        }
    }


    @Override
    public boolean isConnected(int v, int w) throws OutOfBoundsVertexException {
        return id.get(v) == id.get(w);
    }


    private void depthFirst(int s) throws OutOfBoundsVertexException {
        id.put(s, count);
        markSet.add(s);
        int size = sizes.get(count);
        sizes.put(count, size + 1);
        for(int v : graph.adj(s))
            if(!markSet.contains(v))
                depthFirst(v);
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public int id(int v) {
        return id.get(v);
    }
    
}
