package undirected_graphs.generic_graph;

import java.util.*;

import undirected_graphs.exceptions.NonexistentVertexException;

public class GenAdjacencyListGraph<T> implements GenUndirectedGraph<T> {

    private int nVertices;
    private int nEdges;
    private Map<T, List<T>> adjacencyList;
    

    public GenAdjacencyListGraph(){
        nVertices = 0;
        nEdges = 0;
        adjacencyList = new HashMap<>();
    }

    public GenAdjacencyListGraph(Map<T,List<T>> aMap){
        nVertices = 0;
        nEdges = 0;
        adjacencyList = aMap;
    }

    @Override
    public boolean addVertex(T vertex) {
        boolean added = true;
        if(exists(vertex))
            added = false;
        else{
            adjacencyList.put(vertex, new LinkedList<T>());
            nVertices++;
        }
        return added;
    }

    @Override
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
    public Iterable<T> adj(T vertex) throws NonexistentVertexException {
        if(!exists(vertex))
            throw new NonexistentVertexException();
        return adjacencyList.get(vertex);
    }

    @Override
    public boolean exists(T vertex) {
        return adjacencyList.containsKey(vertex);
    }

    //Uses depth first search to find the path, hence not the shortest one
    @Override
    public Iterable<T> getPath(T vertexOne, T vertexTwo) throws NonexistentVertexException {
        if(!exists(vertexOne) || !exists(vertexTwo))
            throw new NonexistentVertexException();

        Map<T,T> pathTo = new HashMap<>(nVertices);
        Set<T> marked = new HashSet<>(nVertices);
        return depthFirstSearch(pathTo, vertexOne, vertexTwo, marked);
    }

    //Pre-condition: Both vertices are in graph
    // Performs depth first search to find a path that ends in goal, starting the search at source. 
    private Stack<T> depthFirstSearch(Map<T,T> pathTo, T source, T goal, Set<T> marked){
        marked.add(source);        
        for(T curr : adjacencyList.get(source)){
            if(!marked.contains(curr)){
                pathTo.put(curr, source);
                if(curr == goal)
                    return buildPath(pathTo, source, goal);
                else{
                    Stack<T> path = depthFirstSearch(pathTo, curr, goal, marked);
                    if(path != null){
                        path.push(source);
                        return path;
                    }
                }
            }
        }
        return null;
    }

    //Pre-condition Both vertices are in graph and pathTo is correctly filled
    //Assuming a "complete" pathTo map where each key is vertex, and each value is another vertex which represents
    //the vertex "before" the key one in the path, builds a path between goal and source
    private Stack<T> buildPath(Map<T, T> pathTo, T source, T goal){
        Stack<T> path = new Stack<T>();
        T current = goal;
        while(current != source){
            path.push(current);
            current = pathTo.get(current);
        }
        path.push(current);
        return path;
    }

    //Uses breadth first search which is why it is guaranteed to be the shortest
    @Override
    public Iterable<T> getShortestPath(T vertexOne, T vertexTwo) throws NonexistentVertexException {
        if(!exists(vertexOne) || !exists(vertexTwo))
            throw new NonexistentVertexException(); 

        Map<T,T> pathTo = new HashMap<>(nVertices);
        Set<T> marked = new HashSet<>(nVertices);
        Queue<T> queue = new LinkedList<>();
        Iterable<T> path = null;

        marked.add(vertexOne);
        queue.add(vertexOne);

        while(!queue.isEmpty() && path == null){
            T curr = queue.remove();

            for(T n : adjacencyList.get(curr)){
                pathTo.put(n, curr);
                marked.add(n);
                if(n == vertexTwo)
                    path = buildPath(pathTo, vertexOne, vertexTwo);
                else if(!marked.contains(n))
                    queue.add(n);
            }
        }

        return path;
    }

    @Override
    public Iterable<GenUndirectedGraph<T>> getConnectedComponents() {
        List<GenUndirectedGraph<T>> connectedComponents = new LinkedList<>();
        Set<T> marked = new HashSet<>();
        for(T v : getVertices())
            if(!marked.contains(v)){
                List<T> component = new LinkedList<>();
                depthFirstComponent(v, component, marked);
                
                Map<T,List<T>> graphMap = new HashMap<>(component.size());
                for(T ele : component)
                    graphMap.put(ele, adjacencyList.get(ele));
                GenAdjacencyListGraph<T> compoGraph = new GenAdjacencyListGraph<>(graphMap);

                connectedComponents.add(compoGraph);
            }   
        
        return connectedComponents;
        
    }

    //Uses depth search to find the connected component which the given vertex is in
    //Pre-condition: vertex is in the graph
    private void depthFirstComponent(T vertex, List<T> component, Set<T> marked){
        marked.add(vertex);
        component.add(vertex);
        for(T curr : adjacencyList.get(vertex))
            if(!marked.contains(curr))
                depthFirstComponent(curr, component, marked);

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(T e : getVertices()){
            sb.append(e + " -> ");
            sb.append(adjacencyList.get(e));
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public T selfCycle() {
        for(T vertex : getVertices()){
            List<T> adjVertex = adjacencyList.get(vertex);
            if(adjVertex.contains(vertex))
                return vertex;
        }
        return null;
    }

    @Override
    public Iterable<T> parallelEdge() {
        for(T vertex : getVertices()){
            List<T> adjVertex = adjacencyList.get(vertex);
            T dup = hasDuplicate(adjVertex);
            if(dup != null){
                List<T> par = new ArrayList<>(2);
                par.add(vertex);
                par.add(dup);
                return par;
            }
        }
        return null;
    }

    //Checks if a list has duplicate entries of one of it's values, and returns one of them if it does, null if it doesn't
    private T hasDuplicate(List<T> list){
            int size = list.size();
            for(int x = 0; x < size; x++){
                T curr = list.get(x);
                List<T> subList = list.subList(x+1, size);
                if(subList.contains(curr))
                    return curr;
            }
            return null;
    }


    @Override
    public Iterable<T> getCycle() {
        List<T> cycle = new LinkedList<>();

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
        return cycle;

        
    }

    private void cycleDepthSearch(Set<T> marked, Map<T,T> pathTo, T source, List<T> cycle, T prev){
        if(!cycle.isEmpty()) return;
        marked.add(source);
        for(T  curr : adjacencyList.get(source)){
            if(!marked.contains(curr)){
                pathTo.put(curr, source);
                cycleDepthSearch(marked, pathTo, curr, cycle, source);
            } else if(prev != null && !prev.equals(curr)){
                T auxVertex = source;
                while(auxVertex != curr){
                    cycle.add(auxVertex);
                    auxVertex = pathTo.get(auxVertex);
                }
                cycle.add(auxVertex);
            }
        }
    }

    

    



    
}
