package pt.ipp.isep.dei.esoft.project.ui.console.job;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateJobController;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.List;

/**
 * List Jobs UI (console). This option is only available for administrators for demonstration purposes.
 */
public class ListJobsUI implements Runnable {

    private final CreateJobController jobController;

    public ListJobsUI() {
        jobController = new CreateJobController();
    }

    public void run() {
        System.out.println("\n\n--- List Jobs ------------------------");
        listJobs();
    }

    /**
     * Lists all registered jobs along with their details.
     */
    private void listJobs() {
        List<Job> jobs = jobController.getAllJobs();

        System.out.println("Registered Jobs:");
        int counter = 1;
        for (Job job : jobs) {

            System.out.println("[" + counter + "]   Title: " + job.getName());
            System.out.println("      Description: " + job.getDescription());
            System.out.println("-------------------------");
            counter++;
        }
    }
}