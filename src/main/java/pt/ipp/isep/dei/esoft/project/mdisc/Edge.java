package pt.ipp.isep.dei.esoft.project.mdisc;

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
     * Compara as arestas com base no custo
     * */
    public int compareTo(Edge compareEdge) {
        return Double.compare(this.cost, compareEdge.cost);
    }
}
