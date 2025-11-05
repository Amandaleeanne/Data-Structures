import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

import weeek9.DirectedGraph;
import weeek9.Vertex;

public class DirectedGraphTest {

    @Test
    public void test01()
    {
        DirectedGraph g = new DirectedGraph();
        Vertex[] v = {
                new Vertex(0),  // placeholder so vertex labels == array indices
                new Vertex(1),
                new Vertex(2),
                new Vertex(3),
                new Vertex(4)
        };

        g.addVertex(v[1]);
        g.addVertex(v[2]);
        g.addVertex(v[3]);
        g.addVertex(v[4]);

        g.addEdge(v[1],v[2]);
        g.addEdge(v[2],v[3]);
        g.addEdge(v[2],v[4]);

        g.unweighted_figure9dot16(v[1]);

        System.out.println("graph adjacency list:");
        System.out.println(g);
        assertEquals("{1=[2], 2=[3, 4], 3=[], 4=[]}", g.toString());
    }

    @Test
    public void test02() {
        DirectedGraph g = new DirectedGraph();
        Vertex[] v = {
                new Vertex(0),  // placeholder so vertex labels == array indices
                new Vertex(1),
                new Vertex(2),
                new Vertex(3),
                new Vertex(4),
                new Vertex(5),
                new Vertex(6)
        };

        g.addVertex(v[1]);
        g.addVertex(v[2]);
        g.addVertex(v[3]);
        g.addVertex(v[4]);
        g.addVertex(v[5]);
        g.addVertex(v[6]);

        g.addEdge(v[1],v[2]);
        g.addEdge(v[2],v[3]);
        g.addEdge(v[3],v[4]);
        g.addEdge(v[4],v[5]);
        g.addEdge(v[5],v[6]);
        g.addEdge(v[1],v[5]);

        g.unweighted_figure9dot16(v[1]);
        System.out.println("graph adjacency list:");
        System.out.println(g);
        System.out.println("\nshortest paths from vertex " + v[1]);
        System.out.println(g.displayConfigTable());

        assertEquals("{1=[2, 5], 2=[3], 3=[4], 4=[5], 5=[6], 6=[]}", g.toString());
        //Shortest path correct?
        assertEquals(0, v[1].getDist());
        assertEquals(1, v[2].getDist());
        assertEquals(2, v[3].getDist());
        assertEquals(3, v[4].getDist());
        assertEquals(1, v[5].getDist()); // Reached directly from 1
        assertEquals(2, v[6].getDist()); // 1 -> 5 -> 6
    }

    // add test cases 
    @Test
    public void test03() {
                DirectedGraph g = new DirectedGraph();
        Vertex[] v = {
                new Vertex(0),  // placeholder so vertex labels == array indices
                new Vertex(1),
                new Vertex(2),
                new Vertex(3),
                new Vertex(4),
                new Vertex(5),
                new Vertex(6)
        };

        g.addVertex(v[1]);
        g.addVertex(v[2]);
        g.addVertex(v[3]);
        g.addVertex(v[4]);
        g.addVertex(v[5]);
        g.addVertex(v[6]);

        g.addEdge(v[1],v[2]);
        g.addEdge(v[2],v[3]);
        g.addEdge(v[3],v[4]);
        g.addEdge(v[4],v[5]);
        g.addEdge(v[5],v[6]);
        g.addEdge(v[1],v[5]);

        g.unweighted_figure9dot18(v[1]); // Start from vertex 1

        // Verify shortest path distances
        assertEquals(0, v[1].getDist());
        assertEquals(1, v[2].getDist());
        assertEquals(2, v[3].getDist());
        assertEquals(3, v[4].getDist());
        assertEquals(1, v[5].getDist()); // Reached directly from 1
        assertEquals(2, v[6].getDist()); // 1 -> 5 -> 6
    }

    @Test
    public void test04() {
                DirectedGraph g = new DirectedGraph();
        Vertex[] v = {
                new Vertex(0),  // placeholder so vertex labels == array indices
                new Vertex(1),
                new Vertex(2),
                new Vertex(3),
                new Vertex(4),
                new Vertex(5),
                new Vertex(6)
        };

        g.addVertex(v[1]);
        g.addVertex(v[2]);
        g.addVertex(v[3]);
        g.addVertex(v[4]);
        g.addVertex(v[5]);
        g.addVertex(v[6]);

        g.addEdge(v[1],v[2]);
        g.addEdge(v[2],v[3]);
        g.addEdge(v[3],v[4]);
        g.addEdge(v[4],v[5]);
        g.addEdge(v[5],v[6]);
        g.addEdge(v[1],v[5]);

        g.unweighted_figure9dot18(v[1]);

        // Reconstruct path
        Vertex current = v[6];
        StringBuilder path = new StringBuilder();
        while (current != null) {
            path.insert(0, current.getLabel());
            current = current.getPath();
        }

        assertEquals("156", path.toString());
    }

        @Test
    public void test05() {
                DirectedGraph g = new DirectedGraph();
        Vertex[] v = {
                new Vertex(0),  // placeholder so vertex labels == array indices
                new Vertex(1),
                new Vertex(2),
                new Vertex(3),
                new Vertex(4),
                new Vertex(5),
                new Vertex(6),
                new Vertex(7) // isolated vertex for testing
        };

        g.addVertex(v[1]);
        g.addVertex(v[2]);
        g.addVertex(v[3]);
        g.addVertex(v[4]);
        g.addVertex(v[5]);
        g.addVertex(v[6]);
        g.addVertex(v[7]);

        g.addEdge(v[1],v[2]);
        g.addEdge(v[2],v[3]);
        g.addEdge(v[3],v[4]);
        g.addEdge(v[4],v[5]);
        g.addEdge(v[5],v[6]);
        g.addEdge(v[1],v[5]);
        //end setup

        g.unweighted_figure9dot18(v[1]);
        assertEquals(Vertex.INFINITY, v[7].getDist());
        assertNull(v[7].getPath());
    }
  
        @Test
    public void test06() {
        DirectedGraph emptyGraph = new DirectedGraph();
        Vertex dummy = new Vertex(43068);

        assertDoesNotThrow(() -> emptyGraph.unweighted_figure9dot18(dummy));
    }

}