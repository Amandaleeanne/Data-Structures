package weeek9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectedGraph {

    //private Map<Vertex, List<AdjacencyList<Vertex, >> adjVertices = new HashMap<>();
    private Map<Vertex, ArrayList<Vertex>> adjVertices = new HashMap<>();
    private ConfigEntry[] configTbl = new ConfigEntry[0];

    // add an element to the vertices set
    public void addVertex(Vertex vertex) {
        adjVertices.putIfAbsent(vertex, new ArrayList<>());
    }

    // add to the adjacency list of the graph
    public void addEdge(Vertex vertex, Vertex w) {
        List<Vertex> edges = adjVertices.get(vertex);
        edges.add(w);
    }

    /**
     * create the configuration table containing the vertex, if it is known, and the distance
     *
     * @param s distinguished vertex
     * @param tblSize number of entries in the adjVertices map
     * @return an array of entries described in the configuration table
     */
    private ConfigEntry[] initConfigTable(Vertex s, int tblSize) {
        configTbl = new ConfigEntry[tblSize];

        int i = 0;
        for (Map.Entry<Vertex, ArrayList<Vertex>> mapEntry : adjVertices.entrySet()) {
            Vertex v = mapEntry.getKey();
            ConfigEntry config = new ConfigEntry(v);

            if (s.equals(v)) {
                config.setDist(0);
            } else {
                config.setDist(Vertex.INFINITY);
            }

            configTbl[i++] = config;
        }
        return configTbl;
    }

    /**
     * Inefficient algorithm to find the shortest path.
     * @param s
     */
    public void unweighted_figure9dot16(Vertex s) {
        int numVertices = adjVertices.size();
        ConfigEntry[] configTbl = initConfigTable(s, numVertices);

        for (int currDist = 0; currDist < numVertices; currDist++) {
            int vIndex = 0;

            // for each Vertex v
            for (Map.Entry<Vertex, ArrayList<Vertex>> mapEntry : adjVertices.entrySet()) {
                Vertex v = mapEntry.getKey();

                if (!configTbl[vIndex].getKnown() && configTbl[vIndex].getDist() == currDist) {
                    configTbl[vIndex].setKnown(true);
                    // for each Vertex w adjacent to v
                    for (Vertex w : mapEntry.getValue()) {
                        if (w.getDist() == Vertex.INFINITY) {
                            w.setDist(currDist + 1);
                            w.setPath(v);
                        }
                    }
                }
                vIndex++;
            }
        }
    }

    /**
     * Efficient algorithm to find the shortest path.
     * @param startingVertex
     */
    public void unweighted_figure9dot18(Vertex startingVertex){

    if (startingVertex == null || !adjVertices.containsKey(startingVertex)) {
        return; // not throwing an error for sake of easy testing
    }

        Queue<Vertex> queue = new Queue<>();
        //for each vertex:
        for (Map.Entry<Vertex, ArrayList<Vertex>> entry : adjVertices.entrySet()) {
            Vertex vertex = entry.getKey();
            vertex.setDist(Vertex.INFINITY);
            vertex.setPath(null); // Clear any other runs of an algorithm
        }

        startingVertex.setDist(0);
        queue.enqueue(startingVertex);

        while(!queue.isEmpty())
        {
            Vertex vertex = queue.dequeue();
            // For each adjacent nextVertex of vertex set its distance/path then enqueue
            for (Vertex nextVertex : adjVertices.get(vertex)) {
                if (nextVertex.getDist() == Vertex.INFINITY) {
                    nextVertex.setDist(vertex.getDist() + 1);
                    nextVertex.setPath(vertex);
                    queue.enqueue(nextVertex);
                }
            }
        }
        

    }

    public String displayConfigTable() {
        StringBuilder sb = new StringBuilder();

        for (ConfigEntry configTbl1 : configTbl) {
            sb.append(configTbl1);
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return adjVertices.toString();
    }
}
