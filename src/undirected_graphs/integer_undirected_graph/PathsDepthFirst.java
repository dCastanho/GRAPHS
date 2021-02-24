package undirected_graphs.integer_undirected_graph;

import java.util.*;

import undirected_graphs.exceptions.OutOfBoundsVertexException;

public class PathsDepthFirst implements GraphPaths{

    private UndirectedGraph graph;
    private Set<Integer> markSet;
    private Map<Integer, Integer> prev;

    public PathsDepthFirst(UndirectedGraph g) {
        graph = g;
        markSet = new HashSet<>();
        prev = new HashMap<>(g.V());
    }


    @Override
    public boolean hasPath(int v, int w) throws OutOfBoundsVertexException {
        return getPath(v, w) != null; 
    }

    @Override
    public Iterable<Integer> getPath(int v, int w) throws OutOfBoundsVertexException{
        if(graph.invalidVertex(v) || graph.invalidVertex(w))
            throw new OutOfBoundsVertexException();
        prev.clear();
        markSet.clear();
        pathBuilder(v);
        int curr = w;
        if(!markSet.contains(curr))
            return null;
        Stack<Integer> path = new Stack<Integer>();
        while(curr != v){
            path.push(curr);
            curr = prev.get(curr);
        }
        path.push(curr);
        return path;
        
    }

    private void pathBuilder(int source) throws OutOfBoundsVertexException {
        markSet.add(source);
        for(int v : graph.adj(source))
            if(!markSet.contains(v)){
                prev.put(v, source);
                pathBuilder(v);
            }
    }
    
}
