import undirected_graphs.exceptions.OutOfBoundsVertexException;
import undirected_graphs.integer_undirected_graph.AdjacencyListGraph;
import undirected_graphs.integer_undirected_graph.Search;
import undirected_graphs.integer_undirected_graph.SearchBreadthFirst;
import undirected_graphs.integer_undirected_graph.UndirectedGraph;

public class App {
    public static void main(String[] args) throws OutOfBoundsVertexException {
        UndirectedGraph g = new AdjacencyListGraph(13);
        g.addEdge(0, 2);
        g.addEdge(0, 1);
        g.addEdge(0, 5);
        g.addEdge(0, 6);
        g.addEdge(5, 3);
        g.addEdge(5, 4);
        g.addEdge(3, 4);
        g.addEdge(6, 4);
        Search s = new SearchBreadthFirst(g);
        System.out.println(s.count(5));
    }


    


}
