package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.mdisc.Edge;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static pt.ipp.isep.dei.esoft.project.ui.console.GraphAnalysisUI.*;

public class ExecutionTimeAnalysis implements Runnable{

    /**
     * Constantes necessárias para a aplicação
     */
    private static final String OUTPUT_FOLDER = "output-files";    //pasta de ficheiros de output
    private static final String EFFICIENCY_PLOT_FILE_NAME = "eficiencyPlot.svg"; // nome do ficheiro de plot dos tempos de execução
    private static final String EFFICIENCY_CSV_DATA_FILE_NAME = "efficiencyData.csv"; // nome do ficheiro de dados dos tempos de execução
    private static final String UNIX_DIRECTORY_SEPARARTOR = "/"; // separador de diretorios UNIX
    private static final String WINDOWS_DIRECTORY_SEPARARTOR = "\\"; // separador de diretorios WINDOWS
    private static final String GNUPLOT_SCRIPT_FILE_NAME = "script.gp"; // nome do ficheiro de script do GNUPLOT

    @Override
    public void run() {

        String workFile;
        List<Edge> edges;

        try {
            //US14


            String workFilePrefix = readString("Type the complete graph files name prefix!");
            int numOfFilesToAnalize = readInteger("Type the number of file to analyze");

            long[] executionTimes = new long[numOfFilesToAnalize];
            int[] numOfLinesReaded = new int[numOfFilesToAnalize];

            for (int i = 0; i < numOfFilesToAnalize; i++) {

                workFile = workFilePrefix + (i + 1) + ".csv";

                edges = readGraphDataFromFile(workFile);

                //saves the number of lines read
                numOfLinesReaded[i] = edges.size();

                // saves the starting time of the process
                long startTime = System.nanoTime();

                //US 13
                applyKruskalAlgorythmAndPrintData(edges, workFile);

                // saves the ending time of the process
                long endTime = System.nanoTime();

                // calculates the execution time in milliseconds
                long executionTimeMs = (endTime - startTime) / 1_000_000; // Convert nanoseconds to milliseconds

                executionTimes[i] = executionTimeMs;

            }


            //makes csv file in the output folder
            printStatsToCSVFile(executionTimes, numOfLinesReaded, EFFICIENCY_CSV_DATA_FILE_NAME);

            //makes the gnuplot scipt
            makeGnuplotScript(GNUPLOT_SCRIPT_FILE_NAME);

            executeGnuPlot(GNUPLOT_SCRIPT_FILE_NAME);


        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }


    /**
     * Run gnuplot with script
     *
     * @param scriptFileName
     */
    public static void executeGnuPlot(String scriptFileName) {

        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");

        try {

            File plotFile;

            if (isWindows) {

                plotFile = new File(OUTPUT_FOLDER + WINDOWS_DIRECTORY_SEPARARTOR + EFFICIENCY_PLOT_FILE_NAME);

                //ProcessBuilder builder = new ProcessBuilder("gnuplot", OUTPUT_FOLDER + WINDOWS_DIRECTORY_SEPARARTOR + scriptFileName);
                ProcessBuilder builder = new ProcessBuilder("pwd");
                builder.inheritIO();
                Process process = builder.start();
                process.waitFor(3000, TimeUnit.MILLISECONDS);

            } else {

                plotFile = new File(OUTPUT_FOLDER + UNIX_DIRECTORY_SEPARARTOR + EFFICIENCY_PLOT_FILE_NAME);

                ProcessBuilder builder = new ProcessBuilder("gnuplot", OUTPUT_FOLDER + UNIX_DIRECTORY_SEPARARTOR + scriptFileName);
                //ProcessBuilder builder = new ProcessBuilder("ls", "-l", OUTPUT_FOLDER);
                builder.inheritIO();
                Process process = builder.start();
                process.waitFor(3000, TimeUnit.MILLISECONDS);

            }


            if (plotFile.exists()) {
                System.out.println("\nGráfico gerado com sucesso!\n");
            } else {
                System.out.println("\nErro! Gráfico não gerado.\n");
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("Erro a executar o GNUPLOT: " + e);
        }
    }

    /**
     * Helper method for generating the GNUPLOT script
     *
     * @param filename
     */
    private static void makeGnuplotScript(String filename) {

        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");

        String outputFolderAndFile;

        PrintWriter printToFile = null;


        try {

            if (isWindows) {

                //Generates script file for Windows systems (directories separated with backslash)

                outputFolderAndFile = String.format("%s%s", OUTPUT_FOLDER + WINDOWS_DIRECTORY_SEPARARTOR, filename);
                printToFile = new PrintWriter(new File(outputFolderAndFile));

                printToFile.println(String.format(
                        "set terminal svg size 1920,1080 enhanced font \"Arial,20\"\n" +
                                "set datafile separator \";\"\n" +
                                "\n" +
                                "set title \"Análise de efficiência do algoritmo da US13\"\n" +
                                "set xlabel \"Nº linhas do ficheiro\"\n" +
                                "set ylabel \"Tempo de execussão (em milisegundos)\"\n" +
                                "set grid\n" +
                                "\n" +
                                "set style data linespoints\n" +
                                "\n" +
                                "set object 1 rectangle from screen 0,0 to screen 1,1 behind fillcolor rgb \"white\" fillstyle solid 1.0 noborder\n" +
                                "\n" +
                                "plot \"%s\" u 1:2 smooth unique with lines title 'Eficiência em função do tamanho do ficheiro de input'\n" +
                                "\n" +
                                "set output \"%s\"\n" +
                                "replot", OUTPUT_FOLDER + WINDOWS_DIRECTORY_SEPARARTOR + EFFICIENCY_CSV_DATA_FILE_NAME, OUTPUT_FOLDER + WINDOWS_DIRECTORY_SEPARARTOR + EFFICIENCY_PLOT_FILE_NAME));

                printToFile.close();
            } else {

                //Generates script file for UNIX-based systems (directories separated with slash)

                outputFolderAndFile = String.format("%s%s", OUTPUT_FOLDER + UNIX_DIRECTORY_SEPARARTOR, filename);

                printToFile = new PrintWriter(new File(outputFolderAndFile));

                printToFile.println(String.format(
                        "set terminal svg size 1920,1080 enhanced font \"Arial,20\"\n" +
                                "set datafile separator \";\"\n" +
                                "\n" +
                                "set title \"Análise de efficiência do algoritmo da US13\"\n" +
                                "set xlabel \"Nº linhas do ficheiro\"\n" +
                                "set ylabel \"Tempo de execussão (em milisegundos)\"\n" +
                                "set grid\n" +
                                "\n" +
                                "set style data linespoints\n" +
                                "\n" +
                                "set object 1 rectangle from screen 0,0 to screen 1,1 behind fillcolor rgb \"white\" fillstyle solid 1.0 noborder\n" +
                                "\n" +
                                "plot \"%s\" u 1:2 smooth unique with lines title 'Eficiência em função do tamanho do ficheiro de input'\n" +
                                "\n" +
                                "set output \"%s\"\n" +
                                "replot", OUTPUT_FOLDER + UNIX_DIRECTORY_SEPARARTOR + EFFICIENCY_CSV_DATA_FILE_NAME, OUTPUT_FOLDER + UNIX_DIRECTORY_SEPARARTOR + EFFICIENCY_PLOT_FILE_NAME));

                printToFile.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write file.");
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

}
