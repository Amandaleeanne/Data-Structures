package week10;

import java.io.InvalidClassException;
import java.util.*;
/**
 * A weighted, undirected graph API set up 
 * so that one can easily implement graph Algorithms.
 * Graph does NOT support duplicate values nor removals because I'm lazy.
 * Graph does NOT remove from the graph....because im lazy.
 * @author 
 */
//Note: I know I could have made this easier on myself by copying and pasting the Graph API from the book or tom, but i wanted practice
//implementing it myself
public class GraphForAlg {
    private Map<Vertex, List<Edge>> vertexMap;
    private List<Edge> allEdges;

    public GraphForAlg() {
        this.vertexMap = new HashMap<>();
        this.allEdges = new ArrayList<>();
    }

    /**
     * Constructs a graph from an adjacency matrix.
     * Assumes vertex labels are integers starting from 0.
     * If a weight is 0 or less, it's treated as no edge.
     * @param matrix Adjacency matrix
     */
    public GraphForAlg(Integer[][] matrix) throws InvalidClassException 
    {
        this(); // Initialize verticiesMap
        //Note to self on an adjacency matrix:
        //the indecies of the first array contain the Label
        //the indecies of the second array contain the mapped index and then the value (weight) that is assigned
        if (!(matrix[0].getClass().equals(Integer.class) || matrix[0][0].getClass().equals(Integer.class)))
        {
            throw new InvalidClassException("Array cannot contain anything other than numbers");
        }

        //pre-initalize vertcies
        for (Integer i = 0; i < matrix.length; i++) {
            addVertex(new Vertex(i+1)); //for 1-based graphs
        }

        for (int i = 0; i < matrix.length; i++) {
            for (Integer j = 0; j < matrix[i].length; j++) 
            {
                Integer weight = matrix[i][j];
                addEdge(this.getVertex(i+1),  this.getVertex(j+1), weight);
            }   
        }

    }

    /**
     * Constructs a graph from an adjacency list of vertex's and edges.
     * Each entry in the list maps a vertex to a list of its neighbors.
     * @param adjList Adjacency list with optional weights
     */
    public GraphForAlg(Map<Vertex, List<Edge>> adjList) {
        this(); // Initialize verticiesMap
        // Add all vertices first
        for (Vertex vertex : adjList.keySet()) {
            addVertex(vertex);
            for (Edge entry : adjList.get(vertex))
            {
                if (entry.getWeight() < 1)
                    addEdge(entry.getfromVertex(), entry.gettoVertex(), 1);
                else
                    addEdge(entry.getfromVertex(), entry.gettoVertex(), entry.getWeight());
            }
        }

    }
    /**
     * Adds a vertex to the graph
     * @param vertex
     */
    public void addVertex(Vertex vertex) 
    {
        vertexMap.putIfAbsent(vertex, new ArrayList<>());
    }
    /**
     * Adds an edge to the graph, undirected
     * @param fromVertex
     * @param toVertex
     * @param weight
     */
    public void addEdge(Vertex fromVertex, Vertex toVertex, int weight) 
    {
        vertexMap.get(fromVertex).add(new Edge(fromVertex, toVertex, weight));
        vertexMap.get(toVertex).add(new Edge(toVertex, fromVertex, weight)); // Undirected
        allEdges.add(new Edge(fromVertex, toVertex, weight));
    }
    /**
     * Adds an edge to the graph, directed suing the {@code Edge} class
     * @param fromVertex the vertex you are coming from
     * @param toVertex the vertex you are going to
     * @param weight assign a number for the weight, its an int.
     */
    public void addDirectedEdge(Vertex fromVertex, Vertex toVertex, int weight)
    {
        vertexMap.get(fromVertex).add(new Edge(fromVertex, toVertex, weight));
        allEdges.add(new Edge(fromVertex, toVertex, weight));
    }
    /**
     * Adds an edge from an already made {@code Edge} class.
     * @param vertex
     * @param edge
     */
    public void addExistingEdge(Vertex vertex, Edge edge){
        vertexMap.get(vertex).add(edge);
        vertexMap.get(edge.gettoVertex()).add(edge); //undirected
        allEdges.add(edge);
    }

    /**
     * Returns the list of all of the edges connected to a specific vertex in the graph
     * @param vertex
     * @return
     */
    public List<Edge> getEdges(Vertex vertex) 
    {
        return vertexMap.get(vertex);
    }

    /**
     * Returns all of the verticies in the graph as a Set
     * @return {@code Set}
     */
    public Set<Vertex> getAllVertices() 
    {
        return vertexMap.keySet();
    }
    /**
     * Returns the size of the graph, aka how many verticies.
     * @return
     */
    public int size()
    {
        return vertexMap.keySet().size();
    }

    /**
     * Returns the vertex in the graph that matches the given label.
     * @param label The label of the vertex to find.
     * @return The Vertex with the specified label, or null if not found.
     */
    public Vertex getVertex(Vertex vertex){
        for (Vertex v : vertexMap.keySet()) {
            if (v.compareTo(vertex) == 0) {
                return v;
            }
        }
        return null; // Not found
    }
    public Vertex getVertex(int label)
    {
        return getVertex(new Vertex(label));
    }
    /**
     * Returns every single edge in the entire graph.
     * @return
     */
    public List<Edge> getAllEdges() 
    {
        return allEdges;
    }

    public void resetVertices() 
    {
        for (Vertex v : vertexMap.keySet()) 
        {
            v.setDistance(Vertex.INFINITY);
            v.setVisited(false);
        }
    }

    @Override
    public String toString() 
    {
        StringBuilder sb = new StringBuilder();
        for (Vertex v : vertexMap.keySet()) 
        {
            sb.append(v).append(": ");
            for (Edge e : vertexMap.get(v)) 
            {
                sb.append(e.gettoVertex()).append("(").append(e.getWeight()).append(") ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
