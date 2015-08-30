package graphs;

/**
 * Errors class.
 * <p> For use in exceptions in the graphs' code.
 */
public enum Errors {
    VERTEX_NOT_CONTAINED("The vertex is not contained in the graph"),
    VERTEX_CONTAINED("The vertex is already contained in the graph"),
    EDGE_NOT_CONTAINED("The edge is not contained in the graph"),
    EDGE_CONTAINED("The edge is already contained in the graph"),
    ADD_NULL_VERTEX("Tried to add a null vertex"),
    ADD_NULL_EDGE("Tried to add a null edge"),
    ADD_EXISTING_EDGE("There is already an edge between the parameter's endpoints"),
    REMOVE_NULL_VERTEX("Tried to remove a null vertex"),
    REMOVE_NULL_EDGE("Tried to remove a null edge"),
    NOT_ADJACENT("The vertices are not adjacent"),
    LOOPS_NOT_ALLOWED("This graph doesn't allow loops"),
    WRONG_EDGE_TYPE("This graph doesn't allow this type of edge")
    ;

    private String message;

    Errors(String message) {
        this.message = message;
    }

    public String toString() {
        return message;
    }
}
