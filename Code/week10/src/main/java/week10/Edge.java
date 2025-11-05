package week10;

/**
 * Edge class that assists in implementing le graphe
 */
public class Edge implements Comparable<Edge> {
    private final Vertex fromVertex;
    private final Vertex toVertex;
    private final int weight;

    public Edge(Vertex fromVertex, Vertex toVertex, int weight) {
        this.fromVertex = fromVertex;
        this.toVertex = toVertex;
        this.weight = weight;
    }

    public Vertex getfromVertex() {
        return fromVertex;
    }

    public Vertex gettoVertex() {
        return toVertex;
    }

    public int getWeight() {
        return weight;
    }
    
    //for undirected
    /**
     * Compares vertexEquality for undirected graphs such that knowing AC == CA with the same weight MUST mean its the same edge
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o){
        Edge edge = (Edge) o;
        if (this.weight == edge.weight)
        {
            //Undirected graphs
            if (fromVertex.compareTo(edge.toVertex) == 0 && toVertex.compareTo(edge.fromVertex) == 0)
            {
                return true;
            }
            //basic equality check
            if (fromVertex.compareTo(edge.fromVertex) == 0 && fromVertex.compareTo(edge.fromVertex) == 0)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }

    @Override
    public String toString() {
        return fromVertex + " --(" + weight + ")--> " + toVertex;
    }
}
