package week9;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
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
        Assert.assertEquals("{1=[2], 2=[3, 4], 3=[], 4=[]}", g.toString());
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

        Assert.assertEquals("{1=[2, 5], 2=[3], 3=[4], 4=[5], 5=[6], 6=[]}", g.toString());
        // add an assertion to test the shortest path
    }

    // add test cases
  
}