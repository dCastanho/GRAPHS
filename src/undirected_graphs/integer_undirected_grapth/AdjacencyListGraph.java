package undirected_integer_graph;

import java.util.*;

import undirected_graphs.exceptions.OutOfBoundsVertexException;

public class AdjacencyListGraph implements UndirectedGraph {

    private List<Set<Integer>> adjacencies;
    private int nVertices;
    private int nEdges;

    AdjacencyListGraph(int nVertices) {
        adjacencies = new ArrayList<>(nVertices);
        for (int i = 0; i < nVertices; i++)
            adjacencies.add(i, new HashSet<>());
        this.nVertices = nVertices;
        nEdges = 0;
    }

    @Override
    public int V() {
        return nVertices;
    }

    @Override
    public int E() {
        return nEdges;
    }

    @Override
    public void addEdge(int v, int w) throws OutOfBoundsVertexException{
        if(invalidVertex(v) || invalidVertex(w) )
            throw new OutOfBoundsVertexException();

        boolean added = adjacencies.get(v).add(w); //Edges are reflective, so we only need to check if it was in one    
        adjacencies.get(w).add(v);
        if(added) 
            nEdges++;    
    }

    @Override
    public Iterator<Integer> adj(int v) throws OutOfBoundsVertexException{
        if(invalidVertex(v))
            throw new OutOfBoundsVertexException();
        return adjacencies.get(v).iterator();
    }

    private boolean invalidVertex(int v){
        return v < 0 || v >= nVertices ;
    }
    
}
