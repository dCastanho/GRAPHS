package graph.directed_graph;

import java.util.*;
import graph.AbsAdjacencyListGraph;
import graph.Graph;
import graph.exceptions.NonexistentVertexException;

public class DirectedAdjacencyListGraph<T> extends AbsAdjacencyListGraph<T> implements DirectedGraph<T> {

    //NOTE: No copies of vertexes are made, which means that even methods like stronglyConnectedComponents return graphs whose vertices point to the actual objects, also stored in this graph and not copies of this.

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

    public Iterable<T> getCycle() {
        Stack<T> cycle = new Stack<>();

        T selfLoop = selfCycle();
        if(selfLoop != null){
            cycle.add(selfLoop);
            cycle.add(selfLoop);
            return cycle;
        } 

        Iterable<T> parallel = parallelEdge();
        if(parallel != null)
            return parallel;

        Set<T> marked = new HashSet<>();
        Map<T,T> pathTo = new HashMap<>();
        for(T v : getVertices()){
            if(!marked.contains(v))
                cycleDepthSearch(marked, pathTo, v, cycle, null);
        }
        return reversePath(cycle);
    }
    
    private void cycleDepthSearch(Set<T> marked, Map<T,T> pathTo, T source, Stack<T> cycle, T prev){
        if(!cycle.isEmpty()) return;
        marked.add(source);
        for(T  curr : adjacencyList.get(source)){
            if(!marked.contains(curr)){
                pathTo.put(curr, source);
                cycleDepthSearch(marked, pathTo, curr, cycle, source);
            } else{
                T auxVertex = source;
                while(auxVertex != curr){
                    cycle.push(auxVertex);
                    auxVertex = pathTo.get(auxVertex);
                }
                cycle.push(auxVertex);
            }
        }
    }

    @Override
    public Iterable<T> topologicalSort() {
        return getReversePostorder();
        
    }



    private void postorder(Queue<T> sorted, Set<T> marked, T source){
        marked.add(source);
        for(T v : adjacencyList.get(source))
            if(!marked.contains(v))
                postorder(sorted, marked, v);
        sorted.add(source);
    }

    private void preorder(Queue<T> sorted, Set<T> marked, T source){
        marked.add(source);
        sorted.add(source);
        for(T v : adjacencyList.get(source))
            if(!marked.contains(v))
                postorder(sorted, marked, v);
    }

    @Override
    public boolean stronglyConnected(T vertexOne, T vertexTwo) throws NonexistentVertexException{
        if(!exists(vertexOne) || !exists(vertexTwo))
            throw new NonexistentVertexException();

        Iterable<T> pathOneTwo = getPath(vertexOne, vertexTwo);
        Iterable<T> pathTwoOne = getPath(vertexTwo, vertexOne);

        return pathOneTwo != null && pathTwoOne != null;
    }

    @Override
    public DirectedGraph<T> reverse() {
        Map<T, List<T>> aMap = new HashMap<>(nVertices);
        for(T curr : getVertices()){
            if(!aMap.containsKey(curr))
                aMap.put(curr, new LinkedList<>());
            for(T v : adjacencyList.get(curr)){
                List<T> adjList = aMap.get(v);
                if(adjList == null)
                    adjList = new LinkedList<>();
                
                adjList.add(curr);
                aMap.put(v, adjList);
            }}
        return new DirectedAdjacencyListGraph<T>(aMap);
    }

    public Iterable<DirectedGraph<T>> stronglyConnectedComponents(){
        DirectedGraph<T> reverse = reverse();
        Iterable<T> revPostorder = reverse.getReversePostorder();
        List<DirectedGraph<T>> connectedComponents = new LinkedList<>();
        Set<T> marked = new HashSet<>();
        for(T v : revPostorder)
            if(!marked.contains(v)){
                List<T> component = new LinkedList<>();
                depthFirstComponent(v, component, marked);
                
                Map<T,List<T>> graphMap = new HashMap<>(component.size());
                for(T ele : component)
                    graphMap.put(ele, adjacencyList.get(ele));
                DirectedGraph<T> compoGraph = new DirectedAdjacencyListGraph<>(graphMap);

                connectedComponents.add(compoGraph);
            }   
        
        return connectedComponents;
    }

    @Override
    public Iterable<T> getPostorder() {
        Queue<T> sorted = new LinkedList<T>();
        Set<T> marked = new HashSet<T>();
        for(T source : getVertices())
            if(!marked.contains(source))
                postorder(sorted, marked, source);
        return sorted;
    }

    @Override
    public Iterable<T> getReversePostorder() {
        return reversePath(getPostorder());
    }

    @Override
    public Iterable<T> getPreorder() {
        Queue<T> sorted = new LinkedList<T>();
        Set<T> marked = new HashSet<T>();
        for(T source : getVertices())
            if(!marked.contains(source))
                preorder(sorted, marked, source);
        return sorted;
    }

    @Override
    public Iterable<T> getReachable(T vertex) throws NonexistentVertexException {
        if(!exists(vertex))
            throw new NonexistentVertexException();

        return reachable(vertex);
    }

    private Set<T> reachable(T vertex){
        Set<T> marked = new HashSet<>();
        depthReachable(marked, vertex);
        return marked;
    }


    private void depthReachable(Set<T> marked, T source){
        marked.add(source);
        for(T v : adjacencyList.get(source))
            if(!marked.contains(v))
                depthReachable(marked, v);
    }

	@Override
	public DirectedGraph<T> transitiveClosure() {
        Map<T, List<T>> aMap = new HashMap<>();
        for(T curr : getVertices()){
            List<T> adjList = new LinkedList<>();
            adjList.addAll(reachable(curr));
            aMap.put(curr, adjList);
        }
		return new DirectedAdjacencyListGraph<T>(aMap);
	}
    
}
