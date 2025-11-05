
package week10;

/**
 *
 * @author Thomas.Abbott and edits by Amandaleeanne Schock
 */
public class Vertex implements Comparable<Vertex>{
    public final static int INFINITY = Integer.MAX_VALUE;
    
    private Integer label;
    private int distance;
    private boolean visited;

    public Vertex(int label) {
        this.label = label;
        this.distance = INFINITY;
        this.visited = false;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setInfinity()
    {
        this.distance = INFINITY;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getLabel() {
        return label;
    }

    public int getDistance() {
        return distance;
    }

    public boolean getVisited() {
        return visited;
    }

    @Override
    public int compareTo(Vertex o) {
        return Integer.compare(this.label, o.label);
    }
    
    @Override
    public int hashCode() {
        return label.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        Vertex v = (Vertex) o;
        return this.label.equals(v.label);
    }

    @Override
    public String toString() {
        return label.toString();
    }
}
