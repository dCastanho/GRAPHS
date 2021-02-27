package graph;

import java.util.*;

import graph.exceptions.NonexistentVertexException;


public abstract class AbsAdjacencyListGraph<T> implements Graph<T>{
    
    protected int nVertices;
    protected int nEdges;
    protected Map<T, List<T>> adjacencyList;
    

    public AbsAdjacencyListGraph(){
        nVertices = 0;
        nEdges = 0;
        adjacencyList = new HashMap<>();
    }


    public AbsAdjacencyListGraph(Map<T,List<T>> aMap){
        nVertices = 0;
        nEdges = 0;
        adjacencyList = aMap;
    }

    public int nVertices(){
        return nVertices;
    }

    public int nEdges(){
        return nEdges;
    }

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

    public Iterable<T> getVertices() {
        return adjacencyList.keySet();
    }

    public boolean exists(T vertex) {
        return adjacencyList.containsKey(vertex);
    }

    public Iterable<T> adj(T vertex) throws NonexistentVertexException {
        if(!exists(vertex))
            throw new NonexistentVertexException();
        return adjacencyList.get(vertex);
    }

    //Uses depth first search to find the path, hence not the shortest one
    public Iterable<T> getPath(T vertexOne, T vertexTwo) throws NonexistentVertexException {
        if(!exists(vertexOne) || !exists(vertexTwo))
            throw new NonexistentVertexException();

        Map<T,T> pathTo = new HashMap<>(nVertices);
        Set<T> marked = new HashSet<>(nVertices);
        return depthFirstSearch(pathTo, vertexOne, vertexTwo, marked);
    }

    //Pre-condition: Both vertices are in graph
    //Performs depth first search to find a path that ends in goal, starting the search at source. 
    protected Stack<T> depthFirstSearch(Map<T,T> pathTo, T source, T goal, Set<T> marked){
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
                        reversePath(path);
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
    protected Stack<T> buildPath(Map<T, T> pathTo, T source, T goal){
        Stack<T> path = new Stack<T>();
        T current = goal;
        while(current != source){
            path.push(current);
            current = pathTo.get(current);
        }
        path.push(current);
        return path;
    }


    protected void reversePath(Stack<T> path) {
        Stack<T> newPath = new Stack<T>();
        while(!path.empty())
            newPath.push(path.pop());
            
    }

    //Uses breadth first search which is why it is guaranteed to be the shortest
    
    public Iterable<T> getShortestPath(T vertexOne, T vertexTwo) throws NonexistentVertexException {
        if(!exists(vertexOne) || !exists(vertexTwo))
            throw new NonexistentVertexException(); 

        Map<T,T> pathTo = new HashMap<>(nVertices);
        Set<T> marked = new HashSet<>(nVertices);
        Queue<T> queue = new LinkedList<>();
        Stack<T> path = null;

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
        reversePath(path);
        return path;
    }

    //Uses depth search to find the connected component which the given vertex is in
    //Pre-condition: vertex is in the graph
    protected void depthFirstComponent(T vertex, List<T> component, Set<T> marked){
        marked.add(vertex);
        component.add(vertex);
        for(T curr : adjacencyList.get(vertex))
            if(!marked.contains(curr))
                depthFirstComponent(curr, component, marked);

    }

    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(T e : getVertices()){
            sb.append(e + " -> ");
            sb.append(adjacencyList.get(e));
            sb.append("\n");
        }
        return sb.toString();
    }

    
    public T selfCycle() {
        for(T vertex : getVertices()){
            List<T> adjVertex = adjacencyList.get(vertex);
            if(adjVertex.contains(vertex))
                return vertex;
        }
        return null;
    }

    
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
    protected T hasDuplicate(List<T> list){
            int size = list.size();
            for(int x = 0; x < size; x++){
                T curr = list.get(x);
                List<T> subList = list.subList(x+1, size);
                if(subList.contains(curr))
                    return curr;
            }
            return null;
    }


    
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

    protected void cycleDepthSearch(Set<T> marked, Map<T,T> pathTo, T source, List<T> cycle, T prev){
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