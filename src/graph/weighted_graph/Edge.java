package graph.weighted_graph;

import java.util.ArrayList;
import java.util.List;

public class Edge<T> implements Comparable<Edge<T>>{

    private T endOne;
    private T endTwo;
    private int weight;

    public Edge(T v, T w, int weight) {
        this.weight = weight;
        endOne = v;
        endTwo = w;
    }

    T getEndOne() {
        return endOne;
    }

    T getEndTwo() {
        return endTwo;
    }

    int getWeight(){
        return weight;
    }

    @Override
    public int compareTo(Edge<T> o) {
        if(o.weight > weight)
            return -1;
        else if(o.weight < weight)
            return 1;
        else 
            return 0;
    }

    Iterable<T> extremes(){
        List<T> ex = new ArrayList<>(2);
        ex.add(endOne);
        ex.add(endTwo);
        return ex;
    }
}
