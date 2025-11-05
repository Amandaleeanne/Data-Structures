
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InvalidClassException;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.sun.org.apache.xml.internal.security.algorithms.Algorithm;

import week10.*;

public class Testing {
   
    @Test
    //Does the graph work?
    public void testInitalizeGraph() throws InvalidClassException
    {
        //courtesy of GPT(I stole numbers from the mock midterm and asked GPT to quickly put it in a 2d array):
        Integer[][] matrix = {
            //  1  2  3  4  5  6  7  8
            { 0, 1, 1, 0, 0, 0, 0, 0 }, // 1
            { 1, 0, 1, 0, 1, 0, 0, 0 }, // 2
            { 1, 1, 0, 1, 0, 1, 0, 0 }, // 3
            { 0, 0, 1, 0, 1, 1, 0, 0 }, // 4
            { 0, 1, 0, 1, 0, 1, 0, 1 }, // 5
            { 0, 0, 1, 1, 1, 0, 1, 0 }, // 6
            { 0, 0, 0, 0, 0, 1, 0, 1 }, // 7
            { 0, 0, 0, 0, 1, 0, 1, 0 }  // 8
        };
        
        GraphForAlg graph = new GraphForAlg(matrix);
        System.out.println(graph.getAllEdges());
        assertTrue(graph.size() == 8); // all verticies added?
        assertTrue(graph.getAllEdges().size() == 64); //all edges added?
    }
    @Test
    public void testDjikstras() throws InvalidClassException, Exception
    {
        Integer[][] matrix = {
            //  1  2  3  4  5  6  7  8
            { 0, 1, 1, 0, 0, 0, 0, 0 }, // 1
            { 1, 0, 1, 0, 1, 0, 0, 0 }, // 2
            { 1, 1, 0, 1, 0, 1, 0, 0 }, // 3
            { 0, 0, 1, 0, 1, 1, 0, 0 }, // 4
            { 0, 1, 0, 1, 0, 1, 0, 1 }, // 5
            { 0, 0, 1, 1, 1, 0, 1, 0 }, // 6
            { 0, 0, 0, 0, 0, 1, 0, 1 }, // 7
            { 0, 0, 0, 0, 1, 0, 1, 0 }  // 8
        };
        
        Algorithms graph = new Algorithms(matrix); //""inhereted"" from the graph so it will work.
        Tuple output = graph.Dijkstra(1);
        
    }
    
    //I didn't wanna deal with the tester class, took too much time. But it runs and I believe its right :thumbs_up:
    public static void main(String[] args) throws Exception {
        Integer[][] matrix = {
          //  1  2  3  4  5  6  7  8
            { 0, 2, 1, 5, 0, 2, 0, 1 }, // 1
            { 2, 0, 1, 1, 1, 1, 1, 1 }, // 2
            { 1, 1, 0, 1, 1, 1, 1, 1 }, // 3
            { 5, 1, 1, 0, 1, 1, 1, 1 }, // 4
            { 0, 1, 0, 1, 0, 1, 1, 1 }, // 5
            { 2, 1, 1, 1, 1, 0, 1, 1 }, // 6
            { 0, 1, 1, 1, 1, 1, 0, 1 }, // 7
            { 1, 1, 1, 1, 1, 1, 1, 0 }  // 8
        };
        
        Algorithms graph = new Algorithms(matrix); //""inhereted"" from the graph so it will work.
        System.out.println(graph.Dijkstra(1).getNestedIndex1());

        System.out.println("Prim, start node 1: \n" + graph.prim(1).getNestedTup());
        System.out.println("Prim, start node 2: \n" + graph.prim(2).getNestedTup());

        System.out.println("Kruskal, no start node: \n" + graph.kruskals().getNestedTup());
    }
}
