package week10;

import java.io.InvalidClassException;
import java.util.*;
/**
 * A class that implements most of the algorithms tasked for in the assignment. 
 * Only accepts numbers as input for the graph for simplicity.
 * PLEASE NOTE: This code is *extremely messy* because I realized halfway through and 4days in 
 * I should have just made each algorithm a sperate java file. However, sunk cost falacy exists.
 */
public class Algorithms {
    private GraphForAlg graph;
    private double time;
    private final Map<Vertex, Edge> path;

    public Algorithms(GraphForAlg graph)
    {
        this.graph = graph;
        this.time = 0;
        this.path = new LinkedHashMap<>();
    }
    //these constructors below "smell funny" but....oh well ive spent 2days on just the graph and edges.
    public Algorithms(Integer[][] matrix) {
        try {
            graph = new GraphForAlg(matrix);
        } catch (InvalidClassException e) {
        }
        this.time = 0;
        this.path = new LinkedHashMap<>();
    }

    public Algorithms(Map<Vertex, List<Edge>> adjList)
    {
        this.graph = new GraphForAlg(adjList);
        this.time = 0;
        this.path = new LinkedHashMap<>();
    }

    /**
     * Starts the timer
     */
    public void startTimer() {
        time = System.nanoTime();
    }
    /**
     * Stops the timer
     */
    public void stopTimer() {
        time = (System.nanoTime() - time) / 1000000.0; // in milliseconds
        System.out.println("Execution Time: " + time + " ms");
    }

    /**
     * Resets all of the data contained within the Vertcies in the graph and clears the path map.
     */
    private void resetData(){
        for (Vertex vertex : graph.getAllVertices()) {
            vertex.setInfinity();
            vertex.setVisited(false);
        }
        path.clear();
    }
    /**
     * Initializes prims and Disjkstras
     */
    private Vertex initalizeMethod(int startingNode) throws Exception
    {
        if (!graph.getAllVertices().contains(new Vertex(startingNode)))
        {
            throw new Exception("Graph does not contain vertex");
        }
        if(graph.size() == 0)
        {
            throw new Exception("Graph is empty");
        }
        startTimer();
        resetData(); //reset and allocate vertecies
        
        Vertex starNode = graph.getVertex(new Vertex(startingNode));
        starNode.setDistance(0);
        return starNode;
    }

    //Dijkstra
    /**
     * Returns null if the startingNode does not exist within the graph or graph is empty. Otherwise calculates the shortest path
     * and returns a Tuple object containing a string of the path and the running stime it took for the algorithm.
     * @param startingNode
     * @return
     */
        public Tuple Dijkstra(int startingNode) throws Exception
        {
            Vertex startVertex = initalizeMethod(startingNode);
            IndexMinPQ<Integer> minHeap = new IndexMinPQ<>(graph.size());
            minHeap.insert(startVertex.getLabel(), startVertex.getDistance()); // All distances ecept start should be infinity
            
            //Start Algorithm
            Vertex currVertex;

            while (!minHeap.isEmpty())
            {
                currVertex = graph.getVertex(minHeap.delMin());
                currVertex.setVisited(true);
                
                //for each vertex w (edge toVertex) adjacent to current vertex...
                for (Edge edge : graph.getEdges(currVertex)) 
                {
                    //if that vertex is not known...
                    Vertex w = edge.gettoVertex();
                    if (!w.getVisited())
                    {
                        //Calculate its cost...
                        Integer currTotalCost = currVertex.getDistance() + edge.getWeight();
                        //And check to see if the vertex we are looking at has a greater distance than what we have
                        if (currTotalCost < graph.getVertex(w).getDistance())
                        {
                            //if so, update...
                            w.setDistance(currTotalCost);
                            path.put(w, edge);

                            //and re-heap if nessesary:
                            int currVertexLabel = currVertex.getLabel();
                            if (minHeap.contains(currVertexLabel)) {
                                if (currTotalCost < minHeap.keyOf(currVertexLabel)) 
                                {
                                    minHeap.decreaseKey(currVertexLabel, currTotalCost);
                                }
                            } else {
                                minHeap.insert(currVertexLabel, currTotalCost);
                            }
                        }
                    }
                }
            }
            //end
            stopTimer();
            double timeSpent = this.time;
            return new Tuple(reconstructPath(startVertex), timeSpent); //for testing
            
        }

    /**
     * A helper method that reconstructs the path in Dijkstras.
     * @param startingVertex (Leftover from inital assignment instructions) specifies the starting node
     * @return String of all of the paths from the vertex
     */
    private Tuple reconstructPath(Vertex startingVertex){
        startTimer();
        StringBuilder sb = new StringBuilder();
        sb.append("Shortest paths from Vertex ").append(startingVertex.getLabel()).append(":\n");

        for (Vertex target : graph.getAllVertices()) {

            List<Vertex> pathList = new ArrayList<>();
            Vertex current = target;

            while (path.containsKey(current)) {
                pathList.add(current);
                current = path.get(current).getfromVertex();
            }

            if (current.equals(startingVertex)) {
                pathList.add(startingVertex);
                Collections.reverse(pathList);

                sb.append("To ").append(target.getLabel()).append(" (")
                  .append(target.getDistance()).append("): ");
                for (Vertex v : pathList) {
                    sb.append(v.getLabel()).append(" ");
                }
                sb.append("\n");
            } else {
                sb.append("To ").append(target.getLabel()).append(": No path\n");
            }
        }

    stopTimer();
    double timeSpent = this.time;
    return new Tuple(sb.toString(), timeSpent);
    }

    //Prim's Algorithm
        /**
         * Calculates the Minimum spanning tree and its cost for a given algorithm using Prim's algorithm
         * from the given starting index. Note: Weight may/will differ from kruskal due to the different possible start points.
         * @param startingNode (Leftover from inital assignment instructions) specifies the starting node
         * @return
         * @throws Exception
         */
        public Tuple prim(int startingNode) throws Exception {
            startTimer();
            Vertex startVertex = initalizeMethod(startingNode);
            IndexMinPQ<Vertex> minHeap = new IndexMinPQ<>(graph.size()+1);
            LinkedList<Edge> mst = new LinkedList<>();
            Set<Vertex> inMST = new HashSet<>();
            int runningCost = 0;

            // Initialize distances and add start vertex <- note this is from when the assignment said to start from any vertex
            startVertex.setDistance(0);
            minHeap.insert(startVertex.getLabel(), startVertex);

            while (!minHeap.isEmpty()) {
                Vertex currVertex = minHeap.minKey();
                minHeap.delMin();
                inMST.add(currVertex); //add to tree


                // Process adjacent vertices
                for (Edge edge : graph.getEdges(currVertex)) {
                    Vertex adjacent = edge.gettoVertex();
                    int weight = edge.getWeight();

                    if (!inMST.contains(adjacent) && weight < adjacent.getDistance()) {
                        adjacent.setDistance(weight);
                        mst.add(edge);
                        int id = adjacent.getLabel();
                        if (minHeap.contains(id)) 
                        {
                            //reheap
                            if (adjacent.getDistance() < minHeap.keyOf(id).getDistance()) //for distance comparison instread of label
                            {
                                minHeap.decreaseKey(id, adjacent);
                            }
                            } else {
                                minHeap.insert(id, adjacent);
                            }

                    }
                }
            }
            stopTimer();
            runningCost = mst.stream().mapToInt(Edge::getWeight).sum(); //GPT (i thought it was cool though)
            return new Tuple(new Tuple(mst, runningCost), this.time);
        }

        /**
         * Runs kruskals algorithm and returns the minimum spanning tree, its total cost, and how much time was spent (in ms)
         * @return Tuple
         */
        public Tuple kruskals()
        {
            startTimer();
            List<Edge> edgesList = graph.getAllEdges();
            DisjointSet dsjSet = new DisjointSet(graph.size()+1);
            edgesList.sort(null); //should sort by weight due to edges class
            int MST_MAX_SIZE = graph.size()-1;
            List<Edge> mst = new ArrayList<>(MST_MAX_SIZE);
            int weightCounter = 0;

            //create the disjoint set
            for (Edge edge : edgesList) {
                if(mst.size() == MST_MAX_SIZE) break;
                int fromVertex = edge.getfromVertex().getLabel();
                int toVertex = edge.gettoVertex().getLabel();
                
                //Ignore cycles by making sure that the parents of the two edges are not the same:
                if(dsjSet.find(fromVertex) != dsjSet.find(toVertex))
                {
                    //if they arent, unionnn
                    mst.add(edge);
                    weightCounter += edge.getWeight();
                    dsjSet.union(fromVertex, toVertex);
                }

            }
            return new Tuple(new Tuple(mst, weightCounter), this.time);
        }
}
