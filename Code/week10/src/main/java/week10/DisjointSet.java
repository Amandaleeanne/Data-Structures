package week10;

/**
 * Implements a disjoint-set (union-find) data structure with path compression and union by rank.
 * 
 * JavaDoc auto-creation by GPT
 */
class DisjointSet {
    private final int[] parent;
    private final int[] rank;

    /**
     * Initializes the disjoint set with {@code size} elements.
     *
     * @param size number of elements
     */
    public DisjointSet(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) parent[i] = i;
    }

    /**
     * Finds the representative of the set containing {@code x}, with path compression.
     *
     * @param x element to find
     * @return root of the set containing {@code x}
     */
    public int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    /**
     * Unites the sets containing {@code x} and {@code y} using union by rank.
     *
     * @param x first element
     * @param y second element
     */
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) return;

        if (rank[rootX] < rank[rootY])
            parent[rootX] = rootY;
        else if (rank[rootX] > rank[rootY])
            parent[rootY] = rootX;
        else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }
}
