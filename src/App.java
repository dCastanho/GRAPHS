
import graph.Graph;
import graph.directed_graph.DirectedAdjacencyListGraph;
import graph.directed_graph.DirectedGraph;
import graph.exceptions.*;
import graph.undirected_graphs.UndirectedAdjacencyListGraph;


public class App {
    public static void main(String[] args) throws NonexistentVertexException {
        DirectedGraph<Integer> g = new DirectedAdjacencyListGraph<>();
        DirectedGraph<Integer> g2 = new DirectedAdjacencyListGraph<>();

        //Setting up "complex" graph
        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.addVertex(4);
        g.addVertex(5);
        g.addVertex(6);
        g.addVertex(7);
        g.addVertex(8);
        g.addVertex(9);
        g.addVertex(10);

        g.addEdge(1, 3);
        g.addEdge(1, 8);
        g.addEdge(1, 2);
        g.addEdge(3, 5);
        g.addEdge(5, 9);
        g.addEdge(9, 10);
        g.addEdge(10, 6);
        g.addEdge(6, 3);
        g.addEdge(5, 4);
        g.addEdge(2, 4);
        g.addEdge(2, 7);
        //-------------------------------

        // Setting up simple graph
        g2.addVertex(1);
        g2.addVertex(2);
        g2.addVertex(3);

        g2.addEdge(1, 2);
        g2.addEdge(1, 3);
        g2.addEdge(2, 3);
        //--------------------------------


        //System.out.println(g.getConnectedComponents());
        //System.out.println(g.getReversePostorder());
        //System.out.println(g.getPostorder());
        //System.out.println("--");
        System.out.println(g.transitiveClosure());

    

    }


    


}
