package graph.directed_graph;

import java.util.*;
import graph.AbsAdjacencyListGraph;
import graph.Graph;
import graph.exceptions.NonexistentVertexException;

public class DirectedAdjacencyListGraph<T> extends AbsAdjacencyListGraph<T> {

    public DirectedAdjacencyListGraph(){
        super();
    }

    public DirectedAdjacencyListGraph(Map<T,List<T>> aMap){
        super(aMap);
    }

    @Override
    public void addEdge(T vertexOne, T vertexTwo) throws NonexistentVertexException {
        if(!exists(vertexOne) || !exists(vertexTwo))
        throw new NonexistentVertexException();

        adjacencyList.get(vertexOne).add(vertexTwo);

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
                Graph<T> compoGraph = new DirectedAdjacencyListGraph<>(graphMap);

                connectedComponents.add(compoGraph);
            }   
        
        return connectedComponents;
    }
    
}
