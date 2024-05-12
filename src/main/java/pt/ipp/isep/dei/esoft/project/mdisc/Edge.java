package pt.ipp.isep.dei.esoft.project.mdisc;

/**
 * Data structure created to manage the operations with edges
 */
public class Edge implements Comparable<Edge> {
    public String src;
    public String dest;
    public double cost;

    public Edge(String src, String dest, double cost) {
        this.src = src;
        this.dest = dest;
        this.cost = cost;
    }

    /**
     * Compares the cost attribute of the current edge with another given by parameter
     * */
    public int compareTo(Edge compareEdge) {
        return Double.compare(this.cost, compareEdge.cost);
    }
}
