package pt.ipp.isep.dei.esoft.project.ui.console.job;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateJobController;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

/**
 * Create Job UI (console). This option is only available for administrators for demonstration purposes.
 */
public class CreateJobUI implements Runnable {

    private final CreateJobController controller;
    private String jobName;
    private String jobDescription;

    public CreateJobUI() {
        controller = new CreateJobController();
    }

    private CreateJobController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Create Job ------------------------");

        requestData();

        submitData();
    }

    private void submitData() {
        Job job = getController().createJob(jobName, jobDescription);

        if (job != null) {
            System.out.println("\nJob successfully created!");
        } else {
            System.out.println("\nJob not created!");
        }
    }

    private void requestData() {

        //Request the Skill Name from the console
        jobName = requestJobName();

        //Request the Task Description from the console
        jobDescription = requestJobDescription();
    }

    /**
     * Prompts the user to input a job description and validates the input.
     * The input is considered valid if it contains only letters.
     *
     * @return The validated job description.
     */
    private String requestJobDescription() {
        return Utils.requestOnlyLetters("Job Description: ", "Job Description must only contain letters.\n");
    }

    /**
     * Prompts the user to input a job name and validates the input.
     * The input is considered valid if it contains only letters.
     *
     * @return The validated job name.
     */
    private String requestJobName() {
        return Utils.requestOnlyLetters("Job Name: ", "Job Name must only contain letters.\n");
    }
}