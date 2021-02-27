package graph.exceptions;

public class NonexistentVertexException extends Exception{

    
    private static final long serialVersionUID = 1L;

    public NonexistentVertexException() {
        super();
    }

    public NonexistentVertexException(String msg) {
        super(msg);
    }
    
}