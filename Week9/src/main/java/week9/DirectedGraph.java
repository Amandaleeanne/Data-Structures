package week9;

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

    // **************
    // Insert new code here based on the pseudocode included in the assignment
    // **************

    public String displayConfigTable() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < configTbl.length; i++) {
            sb.append(configTbl[i]);
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return adjVertices.toString();
    }
}
