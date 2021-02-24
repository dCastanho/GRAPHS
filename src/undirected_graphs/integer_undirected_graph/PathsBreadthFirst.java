package undirected_graphs.integer_undirected_graph;

import java.util.*;


import undirected_graphs.exceptions.OutOfBoundsVertexException;

public class PathsBreadthFirst implements GraphPaths {

    private UndirectedGraph graph;
    private Queue<Integer> queue;
    private Set<Integer> markSet;
    private Map<Integer, Integer> prev;

    public PathsBreadthFirst(UndirectedGraph g) {
        graph = g;
        queue = new LinkedList<>();
        markSet = new HashSet<>();
        prev = new HashMap<>(g.V());
    }

    @Override
    public boolean hasPath(int v, int w) throws OutOfBoundsVertexException {
        return getPath(v, w) != null;
        
    }

    @Override
    public Iterable<Integer> getPath(int v, int w) throws OutOfBoundsVertexException {
        if(graph.invalidVertex(v) || graph.invalidVertex(w))
            throw new OutOfBoundsVertexException();
        prev.clear();
        markSet.clear();
        queue.clear();

        queue.add(v);
        while(!queue.isEmpty()){
            int curr = queue.remove();
            for(int a : graph.adj(curr)){
                if(!markSet.contains(a)){
                    markSet.add(a);
                    prev.put(a, curr);
                    queue.add(a);
                }
            }
        }
        if(!markSet.contains(w))
            return null;
            
        List<Integer> path = new LinkedList<>();
        int curr = w;
        while(curr != v){
            System.out.println(curr);
            path.add(curr);
            curr = prev.get(curr);
        }
        path.add(curr);
        return path;

    }

}

