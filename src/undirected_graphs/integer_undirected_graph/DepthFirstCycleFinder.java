package undirected_graphs.integer_undirected_graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import undirected_graphs.exceptions.OutOfBoundsVertexException;

public class DepthFirstCycleFinder implements CycleFinder {

    private UndirectedGraph g;
    private Set<Integer> markSet;
    private Map<Integer, Integer> prev;
    private Stack<Integer> cycle;


    public DepthFirstCycleFinder(UndirectedGraph graph){
        g = graph;
        markSet = new HashSet<>();
        prev = new HashMap<>(g.V());
        cycle = new Stack<Integer>();
    }

    @Override
    public Iterable<Integer> findCycle() {
        cycle.clear();
        markSet.clear();
        prev.clear();
        for(int i = 0; i < g.V(); i++)
            if(!markSet.contains(i)){
                try {
                    depthFirst(i, -1);
                } catch (OutOfBoundsVertexException e) {
                    e.printStackTrace();
                }
                
        }
        return cycle;
    }

    private void depthFirst(int curr, int p) throws OutOfBoundsVertexException {
    
        markSet.add(curr);
        for(int v : g.adj(curr)){

            if(!cycle.empty()) return; 

            if(!markSet.contains(v)) {
                prev.put(v, curr);
                depthFirst(v, curr);
            }

            else if(p != v){
                for (int x = curr; x != v; x = prev.get(x)) {
                    cycle.push(x);
                }
                cycle.push(v);
                cycle.push(curr);
        }}
    }
    
}
