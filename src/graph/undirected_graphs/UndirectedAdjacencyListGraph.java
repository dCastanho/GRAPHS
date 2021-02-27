package graph.undirected_graphs;

import java.util.*;

import graph.*;
import graph.exceptions.*;


public class UndirectedAdjacencyListGraph<T> extends AbsAdjacencyListGraph<T>{


    public UndirectedAdjacencyListGraph(){
        super();
    }

    public UndirectedAdjacencyListGraph(Map<T,List<T>> aMap){
        super(aMap);
    }


    public void addEdge(T vertexOne, T vertexTwo) throws NonexistentVertexException {
        if(!exists(vertexOne) || !exists(vertexTwo))
            throw new NonexistentVertexException();

        List<T> adjV1 = adjacencyList.get(vertexOne);
        List<T> adjV2 = adjacencyList.get(vertexTwo);

        adjV1.add(vertexTwo);
        adjV2.add(vertexOne);

        nEdges++;
    }

    @Override
    public Iterable<Graph<T>> getConnectedComponents() {
        List<Graph<T>> connectedComponents = new LinkedList<>();
        Set<T> marked = new HashSet<>();
        for(T v : getVertices())
            if(!marked.contains(v)){
                List<T> component = new LinkedList<>();
                depthFirstComponent(v, component, marked);
                
                Map<T,List<T>> graphMap = new HashMap<>(component.size());
                for(T ele : component)
                    graphMap.put(ele, adjacencyList.get(ele));
                Graph<T> compoGraph = new UndirectedAdjacencyListGraph<>(graphMap);

                connectedComponents.add(compoGraph);
            }   
        
        return connectedComponents;
        
    }
    

    



    
}
