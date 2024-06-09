package pt.ipp.isep.dei.esoft.project.ui.console;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class US18 {
    static final String MATRIX_STR = "datasets/us18_matrix.csv";
    static final String POINTS_STR = "datasets/us18_points_names.csv";
    private static final String DIJKSTRA_US18_FILENAME = "dijkstra_us18";
    static final String US18_DOT_FILENAME = "us18_dot";


    public void run() {

        try {
            presentOptions();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private static void presentOptions() throws FileNotFoundException {
        String[] verticesNames = US17.readPointNamesData(POINTS_STR);
        double[][] matrix = US17.readEdgesData(MATRIX_STR);
        US17.printPointAndMatrixData(verticesNames, matrix);

        US17.printInitalGraphToTXTFile(matrix, verticesNames, US18_DOT_FILENAME + US17.OUTPUT_TXT_EXTENSION);
        US17.plotGraph(US18_DOT_FILENAME + US17.OUTPUT_TXT_EXTENSION, "us18_initial_graph" + US17.IMAGE_EXTENSION_STRING);


        String opt = "";

        System.out.println("Choose one of the following options:");
        System.out.println("1 - Routes for all signs");
        System.out.println("2 - Choose a sign");
        System.out.println("0 - Exit");

        Scanner read = new Scanner(System.in);

        do {
            opt = read.nextLine();
            if (!opt.equals("1") && !opt.equals("2") && !opt.equals("0"))
                System.out.println("Insert the right option!");
        } while (!opt.equals("1") && !opt.equals("2") && !opt.equals("0"));

        switch (opt) {
            case "1":
                proceedToAllVertices(verticesNames, matrix);
                break;
            case "2":
                proceedToOneVertex(verticesNames, matrix);
                break;
        }


    }

    private static void proceedToOneVertex(String[] verticesNames, double[][] matrix) {
        Scanner read = new Scanner(System.in);
        List<String> vertices= List.of(verticesNames);
        System.out.println("\nChoose the initial sign?");
        String vertex = read.nextLine();
        if (vertices.contains(vertex)) {
            us18Routine(verticesNames, matrix, vertex);
        }else
            System.out.println("Vertex not found!");
    }

    private static void proceedToAllVertices(String[] verticesNames, double[][] matrix) {
        Scanner read = new Scanner(System.in);

        String opt = "";
        System.out.println("Proceed to all vertices? (y/n)");
        do {
            opt = read.nextLine();
            if (!opt.equalsIgnoreCase("y") && !opt.equalsIgnoreCase("n"))
                System.out.println("Insert the correct option!");
        } while (!opt.equalsIgnoreCase("y") && !opt.equalsIgnoreCase("n"));

        if (opt.equalsIgnoreCase("y")) {
            List<Integer> apIndexes = identifyAP_indexes(verticesNames);

            List<String> verticesNamesCopy = new ArrayList<>();

            for (int i = 0; i < verticesNames.length; i++) {
                if (!apIndexes.contains(i))
                    verticesNamesCopy.add(verticesNames[i]);
            }

            System.out.println("Smallest routes");
            for (String v : verticesNamesCopy) {
                us18Routine(verticesNames, matrix, v);
            }
        }
    }

    private static void us18Routine(String[] verticesNames, double[][] matrix, String vertice) {

        String pathAndCost;
        List<String> smalestPaths = new ArrayList<>();
        List<Double> smalestCosts = new ArrayList<>();
        List<double[][]> workedMatrixes = new ArrayList<>();
        List<String[]> workedVnames = new ArrayList<>();
        List<String[]> workedPrevVertixes = new ArrayList<>();


        List<Integer> apIndexes = identifyAP_indexes(verticesNames);


        //itera por cada vertice de AP removendo os restantes
        for (Integer i : apIndexes) {
            List<Integer> apIndexesAux = new ArrayList<>(List.copyOf(apIndexes));
            apIndexesAux.remove(i);
            double[][] workingMatrix = removeUnwantedMatrixIndexes(apIndexesAux, matrix);
            String[] workingVNames = removeUnwantedVerticesIndexes(apIndexesAux, verticesNames);

            double[][] copy = US17.deepCopyMatrixDouble(workingMatrix);
            double[] weights = new double[workingVNames.length];
            String[] prevVertices = new String[workingVNames.length];


            //aplica o dijkstra à matrix e vertices depois de removidos os valores dos restantes APs
            US17.applyDijkstraAlg(copy, workingVNames, verticesNames[i], weights, prevVertices);

            pathAndCost = US17.printShortestRoutesToCSV(workingVNames, prevVertices, weights, vertice, DIJKSTRA_US18_FILENAME + "_" + verticesNames[i]);

            Double cost = Double.parseDouble(pathAndCost.split(US17.CSV_SEPARATOR)[1].trim().replace(",", "."));


            smalestPaths.add(pathAndCost);
            smalestCosts.add(cost);
            workedMatrixes.add(workingMatrix);
            workedVnames.add(workingVNames);
            workedPrevVertixes.add(prevVertices);
        }

        int smalestIndex = getSmallestIndex(smalestCosts);
        System.out.println(smalestPaths.get(smalestIndex));
    }

    private static int getSmallestIndex(List<Double> smallestCosts) {

        Double smallest = smallestCosts.get(0);
        int index = 0;
        for (int i = 1; i < smallestCosts.size(); i++) {
            if (smallestCosts.get(i) < smallest) {
                smallest = smallestCosts.get(i);
                index = i;
            }
        }
        return index;
    }

    private static String[] removeUnwantedVerticesIndexes(List<Integer> apIndexes, String[] verticesNames) {
        String[] aux = new String[verticesNames.length - apIndexes.size()];
        int deleted = 0;

        for (int i = 0; i < verticesNames.length; i++) {
            if (!apIndexes.contains(i))
                aux[i - deleted] = verticesNames[i];
            else
                deleted++;
        }
        return aux;
    }

    private static double[][] removeUnwantedMatrixIndexes(List<Integer> apIndexes, double[][] matrix) {
        int newSize = matrix.length - apIndexes.size();
        double[][] aux = new double[newSize][newSize];
        int newRow = 0, newCol;

        for (int i = 0; i < matrix.length; i++) {
            if (apIndexes.contains(i)) continue;
            newCol = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                if (apIndexes.contains(j)) continue;
                aux[newRow][newCol] = matrix[i][j];
                newCol++;
            }
            newRow++;
        }
        return aux;
    }

    private static List<Integer> identifyAP_indexes(String[] verticesNames) {
        List<Integer> aux = new ArrayList<>();
        for (int i = 0; i < verticesNames.length; i++) {
            if (verticesNames[i].startsWith(US17.ASSEMBLY_POINT_STR))
                aux.add(i);
        }
        return aux;
    }

}