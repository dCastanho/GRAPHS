
import graph.Graph;
import graph.directed_graph.DirectedAdjacencyListGraph;
import graph.exceptions.*;
import graph.undirected_graphs.UndirectedAdjacencyListGraph;


public class App {
    public static void main(String[] args) throws OutOfBoundsVertexException, NonexistentVertexException {
        Graph<Integer> g = new DirectedAdjacencyListGraph<>();
        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.addVertex(4);
        g.addVertex(5);
        g.addVertex(6);

        g.addEdge(1, 3);
        g.addEdge(3, 5);
        g.addEdge(5, 1);

        System.out.println(g.getCycle());
    

    }


    


}
