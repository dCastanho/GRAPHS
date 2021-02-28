package graph.weighted_graph;

import graph.exceptions.NonexistentVertexException;
import java.util.*;

public class WeightedAdjacencyListGraph<T> implements WeightedGraph<T> {


    private Map<T, List<Edge<T>>> adjacencyList;
    private int nVertices;
    private int nEdges;

    public WeightedAdjacencyListGraph(){
        adjacencyList = new HashMap<>();
        nVertices = 0;
        nEdges = 0;
    }

    public WeightedAdjacencyListGraph(Map<T,List<Edge<T>>> aMap){
        Set<T> keys = aMap.keySet();
        nVertices = keys.size();
        nEdges = 0;
        for(T key : keys)
            nEdges += aMap.get(key).size();
        adjacencyList = aMap;
    }

    

    @Override
    public boolean addVertex(T vertex) {
        boolean inGraph = exists(vertex);
        if(!inGraph){
            List<Edge<T>> adjs = new LinkedList<>();
            adjacencyList.put(vertex, adjs);
        }
        return !inGraph;
    }

    @Override
    public void addEdge(T v, T w, int weight) throws NonexistentVertexException {
        if(!exists(v) || !exists(w))
            throw new NonexistentVertexException();
            
        Edge<T> e = new Edge<T>(v, w, weight);
        
        List<Edge<T>> vList = adjacencyList.get(v);
        List<Edge<T>> wList = adjacencyList.get(w);

        vList.add(e);
        wList.add(e);
    }

    @Override
    public int nVertices() {
        return nVertices;
    }

    @Override
    public int nEdges() {
        return nEdges;
    }

    @Override
    public Iterable<T> getVertices() {
        return adjacencyList.keySet();
    }

    @Override
    public boolean exists(T vertex) {
        return adjacencyList.containsKey(vertex);
    }

    @Override
    public Iterable<Edge<T>> adj(T vertex) throws NonexistentVertexException {
        if(!exists(vertex))
            throw new NonexistentVertexException();

        return adjacencyList.get(vertex);
    }
    
}
