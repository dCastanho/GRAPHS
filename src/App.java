import java.nio.file.Paths;

import graph.Graph;
import graph.exceptions.*;
import graph.undirected_graphs.GenUndiAdjacencyListGraph;




public class App {
    public static void main(String[] args) throws OutOfBoundsVertexException, NonexistentVertexException {
        Graph<Integer> g = new GenUndiAdjacencyListGraph<>();
        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.addVertex(4);
        g.addVertex(5);
        g.addVertex(6);

        g.addEdge(1, 3);
        g.addEdge(3, 5);
        g.addEdge(1, 5);


    
        System.out.println(g.getCycle());
    

    }


    


}
