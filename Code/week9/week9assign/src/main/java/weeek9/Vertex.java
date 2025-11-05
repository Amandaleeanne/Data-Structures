
package weeek9;

/**
 *
 * @author Thomas.Abbott and edits by Amandaleeanne.Schock
 */
public class Vertex {
    public final static int INFINITY = Integer.MAX_VALUE;
    
    private Integer label;
    
    // three pieces of information
    private int dist;
    private Vertex path;
    private boolean visited;

    public Vertex(Integer label) {
        this.label = label;
        this.dist = INFINITY;
        this.visited = false;
    }

    public void setLabel(Integer label) {
        this.label = label;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public void setvisited(boolean visited) {
        this.visited = visited;
    }

    public void setPath(Vertex path) {
        this.path = path;
    }

    public Integer getLabel() {
        return label;
    }

    public Vertex getPath() {
        return path;
    }

    public int getDist() {
        return dist;
    }

    public boolean getvisited() {
        return visited;
    }

    @Override
    public boolean equals(Object o) {
        Vertex v = (Vertex) o;
        return this.label.equals(v.label);
    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }

    @Override
    public String toString() {
        return label.toString();
    }
}
