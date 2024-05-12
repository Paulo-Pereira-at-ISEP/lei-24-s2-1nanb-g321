package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.mdisc.Edge;
import pt.ipp.isep.dei.esoft.project.mdisc.UnionFind;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GraphAnalysisUI implements Runnable {

    //contantes para TEST
    private static final String TEST_FILE = "datasets/ex_18a_tp2.csv";
    private static final String TEST_FILE2 = "datasets/init_test.csv";
    private static final String TEST_FILE3 = "datasets/US13_JardimEspeciesNucleoRural.csv";
    private static final String TEST_FILE4 = "datasets/US13_JardimDosSentimentos.csv";


    /**
     * Constantes necessárias para a aplicação
     */
    private static final String OUTPUT_FOLDER = "output-files";    //pasta de ficheiros de output
    private static final String OUTPUT_TXT_EXTENSION = ".txt";     //extensão de ficheiros de texto
    private static final String MST_STRING = "MST";                //string utilizada para concatenar no nome dos ficheiros de saída
    private static final String GRAPH_STRING = "GRAPH";            //string utilizada para concatenar no nome dos ficheiros de saída
    private static final String IMAGE_EXTENSION_STRING = ".svg";   //extensão de ficheiros de imagem
    private static final String EFFICIENCY_PLOT_FILE_NAME = "eficiencyPlot.svg"; // nome do ficheiro de plot dos tempos de execução
    private static final String EFFICIENCY_CSV_DATA_FILE_NAME = "efficiencyData.csv"; // nome do ficheiro de dados dos tempos de execução
    private static final String UNIX_DIRECTORY_SEPARARTOR = "/"; // separador de diretorios UNIX
    private static final String WINDOWS_DIRECTORY_SEPARARTOR = "\\"; // separador de diretorios WINDOWS
    private static final String GNUPLOT_SCRIPT_FILE_NAME = "script.gp"; // nome do ficheiro de script do GNUPLOT



    /**
     * Run method
     */
    public void run() {
        List<Edge> edges = new ArrayList<>();


        String workFile = readString("Type the complete graph file name!");

        try {

            //US12
            edges = readGraphDataFromFile(workFile);

            //us13
            applyKruskalAlgorythmAndPrintData(edges, workFile);


        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

    }



    /**
     * Helper method for generating runtime data file
     *
     * @param execTimes
     * @param lines
     * @param filename
     */
    private static void printStatsToCSVFile(long[] execTimes, int[] lines, String filename) {
        try {

            String outputFolderAndFile = String.format("%s%s", OUTPUT_FOLDER + "/", filename);

            PrintWriter printToFile = new PrintWriter(new File(outputFolderAndFile));

            for (int i = 0; i < lines.length; i++) {

                printToFile.println(String.format("%d;%d", lines[i], execTimes[i]));

            }


            printToFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write file.");
        }
    }



    /**
     * Execution of US13
     *
     * @param edges
     * @param workFile
     */
    public static void applyKruskalAlgorythmAndPrintData(List<Edge> edges, String workFile) {
        //US13
        // Passo 1: ordenar os vértices por ordem de peso
        //graph.sort(null);
        bubbleSort(edges);

        List<Edge> mst = KruskalMST(edges);
        printMST(mst, edges);

        String outputGraph_TXTFile = outputTXTFileNameOf(workFile, GRAPH_STRING);
        String outputMST_TXTFile = outputTXTFileNameOf(workFile, MST_STRING);

        printGraphToTXTFile(edges, outputGraph_TXTFile);
        printGraphToTXTFile(mst, outputMST_TXTFile);

        String outputGraph_SVGFile = outputSVGFileNameOf(outputGraph_TXTFile, IMAGE_EXTENSION_STRING);
        String outputMST_SVGFile = outputSVGFileNameOf(outputMST_TXTFile, IMAGE_EXTENSION_STRING);

        plotGraph(outputGraph_TXTFile, outputGraph_SVGFile);
        plotGraph(outputMST_TXTFile, outputMST_SVGFile);
    }

    /**
     * Method for reading an integer
     *
     * @param prompt
     * @return
     */
    public static int readInteger(String prompt) {
        Scanner read = new Scanner(System.in);
        System.out.println(prompt);
        return Integer.parseInt(read.nextLine());
    }

    /**
     * Method for reading a string
     *
     * @param prompt
     * @return
     */
    public static String readString(String prompt) {
        Scanner read = new Scanner(System.in);
        System.out.println(prompt);
        return read.nextLine();
    }


    /**
     * Method for generating graph images
     *
     * @param inputFile
     * @param outputFile
     */
    public static void plotGraph(String inputFile, String outputFile) {

        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");

        try {

            if (isWindows) {

                ProcessBuilder pb1 = new ProcessBuilder("dot", "-Tsvg", OUTPUT_FOLDER + WINDOWS_DIRECTORY_SEPARARTOR + inputFile, "-o", OUTPUT_FOLDER + WINDOWS_DIRECTORY_SEPARARTOR + outputFile);
                pb1.inheritIO();
                Process process1 = pb1.start();
                process1.waitFor();

            } else {

                ProcessBuilder pb1 = new ProcessBuilder("dot", "-Tsvg", OUTPUT_FOLDER + UNIX_DIRECTORY_SEPARARTOR + inputFile, "-o", OUTPUT_FOLDER + UNIX_DIRECTORY_SEPARARTOR + outputFile);
                pb1.inheritIO();
                Process process1 = pb1.start();
                process1.waitFor();

            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * Image output file name generator method
     *
     * @param outputGraphTxtFile
     * @param svgExtensionString
     * @return
     */
    private static String outputSVGFileNameOf(String outputGraphTxtFile, String svgExtensionString) {
        return outputGraphTxtFile.split("\\.")[0] + svgExtensionString;
    }


    /**
     * Text output file name generator method
     *
     * @param workFile
     * @param type
     * @return
     */
    private static String outputTXTFileNameOf(String workFile, String type) {

        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");

        String inputFile;

        if (isWindows) {
            inputFile = workFile.split(WINDOWS_DIRECTORY_SEPARARTOR)[1];
        } else {
            inputFile = workFile.split(UNIX_DIRECTORY_SEPARARTOR)[1];
        }

        String outputFile = inputFile.split("\\.")[0] + type + OUTPUT_TXT_EXTENSION;
        return outputFile;
    }


    /**
     * Text output file generator method
     *
     * @param edges
     * @param filename
     */
    private static void printGraphToTXTFile(List<Edge> edges, String filename) {

        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");

        String outputFolderAndFile;

        try {

            if (isWindows) {

                outputFolderAndFile = String.format("%s%s", OUTPUT_FOLDER + WINDOWS_DIRECTORY_SEPARARTOR, filename);

            } else {

                outputFolderAndFile = String.format("%s%s", OUTPUT_FOLDER + UNIX_DIRECTORY_SEPARARTOR, filename);
            }


            PrintWriter printToFile = new PrintWriter(new File(outputFolderAndFile));

            printToFile.println(String.format("graph %s {\n", filename.split("\\.")[0]));

            for (Edge e : edges) {
                //printToFile.println(String.format("\t%s -- %s [w=%.1f]", e.src, e.dest, e.cost));
                printToFile.println(String.format("\t%s -- %s", e.src, e.dest));
            }
            printToFile.println("\n}");

            printToFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write file.");
        }
    }


    /**
     * Method of applying the Kruskal algorithm to the initial graph to generate a minimal spanning tree
     *
     * @param graph
     * @return
     */
    static List<Edge> KruskalMST(List<Edge> graph) {

        List<String> vertexList = getVertexList(graph);

        List<Edge> result = new ArrayList<>(); // Array para armazenar a árvore geradora minimal

        int e = 0;

        // vetor do tipo UnionFind para acompanhar os conjuntos aresta-pai
        UnionFind uf = new UnionFind(graph.size());


        while (e < graph.size() && result.size() < vertexList.size()) {
            // Passo 2: escolhe a aresta de menor custo
            Edge next_edge = graph.get(e);

            // obter os pais de cada vértice
            int x = uf.find(getVertexIndex(next_edge.src, vertexList));
            int y = uf.find(getVertexIndex(next_edge.dest, vertexList));

            // se os pais forem diferentes, então a aresta não fecha um ciclo
            // logo adiciona à arvore geradora minimal
            if (x != y) {
                result.add(next_edge);
                uf.union(x, y);
            }
            // senão, descarta-se a aresta


            e++;
        }

        return result;
    }

    /**
     * Helper method to sort a list of edgs by it's cost
     *
     * @param graph
     */
    private static void bubbleSort(List<Edge> graph) {

        int n = graph.size();

        Edge aux;

        for (int i = 0; i < n - 1; i++) {

            for (int j = 0; j < n - i - 1; j++) {

                if (graph.get(j).cost > graph.get(j + 1).cost) {

                    aux = graph.get(j);
                    graph.set(j, graph.get(j + 1));
                    graph.set(j + 1, aux);

                }
            }
        }
    }


    /**
     * Helper method to get the index of a vertex from its name
     *
     * @param vertex
     * @param vertexList
     * @return
     */
    private static int getVertexIndex(String vertex, List<String> vertexList) {
        return vertexList.indexOf(vertex);
    }


    /**
     * Auxiliary method to get graph data from file
     *
     * @param testFile
     * @return
     * @throws FileNotFoundException
     */
    public static List<Edge> readGraphDataFromFile(String testFile) throws FileNotFoundException {
        String[] aux;


        Scanner readFile = new Scanner(new File(testFile));

        System.out.println("File found!");

        List<Edge> data = new ArrayList<>();

        while (readFile.hasNext()) {
            aux = readFile.nextLine().split(";");
            data.add(new Edge(aux[0], aux[1], Double.parseDouble(aux[2])));
        }

        System.out.println("Readed " + data.size() + " lines from file!");
        return data;
    }


    /**
     * Auxiliary method to present the minimal spanning tree, graph dimension, graph order and cost
     *
     * @param mst
     * @param edges
     */
    private static void printMST(List<Edge> mst, List<Edge> edges) {
        double cost = 0;
        for (Edge e : mst) {
            System.out.println(String.format("%20s -- %-20s = %.2f", e.src, e.dest, e.cost));
            cost += e.cost;
        }
        System.out.printf("Graph Dimension = %d : Graph Order = %d : Minimum cost = %.2f\n", edges.size(), mst.size() + 1, cost);
    }

    /**
     * Auxiliary method to obtain the list of vertices, ordered and without repetitions
     *
     * @param edge
     * @return
     */
    private static List<String> getVertexList(List<Edge> edge) {
        List<String> auxVertexList = new ArrayList<>();
        for (Edge e : edge
        ) {
            auxVertexList.add(e.src);
            auxVertexList.add(e.dest);
        }
        List<String> vertexList = removeDuplicates(auxVertexList);
        vertexList.sort(null);
        return vertexList;
    }

    /**
     * Auxiliary method to remove duplicates from a list of Strings
     *
     * @param auxVertexList
     * @return
     */
    private static List<String> removeDuplicates(List<String> auxVertexList) {
        List<String> vertexList = new ArrayList<>();

        for (String s : auxVertexList) {
            if (!vertexList.contains(s))
                vertexList.add(s);
        }
        return vertexList;
    }
}
