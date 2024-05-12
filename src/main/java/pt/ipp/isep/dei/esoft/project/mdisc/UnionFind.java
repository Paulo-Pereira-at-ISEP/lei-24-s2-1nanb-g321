package pt.ipp.isep.dei.esoft.project.mdisc;


/**
 * Data structure created to manage the operations with parenthood of vertices
 */
public class UnionFind {
    int[] parent;

    /**
     * Constructor method
     * @param n the size of the array in the current structure
     */
    public UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;
    }

    /**
     * Find the parent of a given vertex
     * @param x the vertex
     * @return the parent of the vertex
     */
    public int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    /**
     * Unite two vertices in a parenthood relationship
     * @param x one vertex
     * @param y another vertex
     */
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        parent[rootX] = rootY;
    }
}
