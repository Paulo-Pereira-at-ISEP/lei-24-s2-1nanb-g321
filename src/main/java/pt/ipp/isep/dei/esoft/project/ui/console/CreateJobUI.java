package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateJobController;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.Scanner;

/**
 * Create Skill UI (console). This option is only available for administrators for demonstration purposes.
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
        jobName = requestJobTitle();

        //Request the Task Description from the console
        jobDescription = requestJobDescription();
    }

    private String requestJobDescription() {
        Scanner input = new Scanner(System.in);
        System.out.print("Job Description: ");
        return input.nextLine();
    }

    private String requestJobTitle() {
        Scanner input = new Scanner(System.in);
        System.out.print("Job Title: ");
        return input.nextLine();
    }

}