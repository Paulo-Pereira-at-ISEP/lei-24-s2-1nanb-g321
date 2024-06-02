package pt.ipp.isep.dei.esoft.project.ui.console.todolist;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateEntryController;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class CreateEntryUI implements Runnable {


    /**
     * Create Skill UI (console). This option is only available for administrators for demonstration purposes.
     */

    private final CreateEntryController controller;
    private String entryName;
    private String entryDescription;
    private String urgencyDegree;
    private int duration;
    private Task task;
    private GreenSpace greenSpace;
    private String status;

    public CreateEntryUI() {
        controller = new CreateEntryController();
    }

    private CreateEntryController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Create Entry ------------------------");

        requestData();

        submitData();
    }

    private void submitData() {
        Entry entry = controller.createEntry(task.getName(), task.getDescription(), urgencyDegree, duration, greenSpace);

        if (entry != null) {
            System.out.println("\nEntry successfully added to the ToDoList!");
        } else {
            System.out.println("\nEntry not created!");
        }
    }

    /**
     * Prompts the user to enter data for a new skill and allows for modification before confirmation.
     *
     * @implNote This method iteratively requests skill name and description from the user.
     * It then presents the entered data for confirmation.
     * - If confirmed ("y"), it exits the loop and indicates successful registration.
     * - If not confirmed ("n"), it allows the user to:
     * - Choose a data field (name or description) for modification using `requestDataModification`.
     * - If a valid field is chosen, it calls `modifySkillData` (assumed to be available)
     * to modify the specific data.
     * - It allows the user to modify another field or confirm again.
     * The loop continues until the user confirms ("y") or exits ("n").
     * After confirmation, the skill data can be used for further processing or storage.
     */
    private void requestData() {
        String input;
        greenSpace = displayAndSelectGreenSpaces();
        task = displayAndSelectTasks();
        urgencyDegree = requestUrgencyDegree();
        duration = requestDuration();
        status = requestStatus();

        System.out.println("\n\n\n---------- Submitted Data ----------");
        System.out.printf("GreenSpace: %s\n", greenSpace.getName());
        System.out.printf("Title: %s\n", task.getName());
        System.out.printf("Description: %s\n", task.getDescription());
        System.out.println("Urgency Degree: " + urgencyDegree);
        System.out.println("Duration: " + duration);


    }


    /**
     * Prompts the user to select a data field for modification.
     *
     * @return A String representing the chosen data field to modify ("Title" or "Description"),
     * or null if the user enters an invalid choice.
     */


    /**
     * Prompts the user to input a skill description and validates the input.
     * The input is considered valid if it contains only letters.
     *
     * @return The validated skill description.
     */
    private Task displayAndSelectTasks() {
        // Retrieve the list of available jobs
        List<Task> tasks = controller.getAllTasks();
        int listSize = tasks.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);
        while (answer < 1 || answer > listSize) {
            displayTasksOptions(tasks); // Display the list of available jobs
            System.out.print("Select a task: ");
            answer = input.nextInt(); // Prompt user to select a job
        }

        return tasks.get(answer - 1); // Return the selected job
    }
    private void displayTasksOptions(List<Task> tasks) {
        // Display the task categories as a menu with number options to select
        int i = 1;
        for (Task task : tasks) {
            System.out.println("  " + i + " - " + task.getName());
            i++;
        }
    }

    private String requestUrgencyDegree() {
        do {
            System.out.println("Choose one of the following urgency degrees: ");
            System.out.println("[1] - Low");
            System.out.println("[2] - Medium");
            System.out.println("[3] - High");
            System.out.print("Your choice: ");
            Scanner input = new Scanner(System.in);
            urgencyDegree = input.nextLine();

            switch (urgencyDegree) {
                case "1":
                    return "Low";
                case "2":
                    return "Medium";
                case "3":
                    return "High";
                default:
                    System.out.println("Invalid choice. Please enter '1', '2', or '3'.");
            }
        } while (true);

    }
    private String requestStatus() {
        do {
            System.out.println("Select the following status: ");
            System.out.println("[1] - Pending");
            System.out.print("Your choice: ");
            Scanner input = new Scanner(System.in);
            status = input.nextLine();

            switch (status) {
                case "1":
                    return "Pending";
                default:
                    System.out.println("Invalid choice. Please press '1'.");
            }
        } while (true);

    }

    /**
     * Prompts the user to input a skill name and validates the input.
     * The input is considered valid if it contains only letters.
     *
     * @return The validated skill name.
     */
    private int requestDuration() {

        System.out.print("What is the duration of the entry (hours)? ");
        Scanner input = new Scanner(System.in);
        duration = input.nextInt();
        return  duration;
    }

    private GreenSpace displayAndSelectGreenSpaces() {
        // Retrieve the list of available jobs
        List<GreenSpace> greenSpaces = controller.getAllGreenSpaces();
        int listSize = greenSpaces.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);
        while (answer < 1 || answer > listSize) {
            displayGreenSpacesOptions(greenSpaces); // Display the list of available jobs
            System.out.print("Select a GreenSpace: ");
            answer = input.nextInt(); // Prompt user to select a job
        }

        return greenSpaces.get(answer - 1); // Return the selected job
    }
    private void displayGreenSpacesOptions(List<GreenSpace> greenSpaces) {
        // Display the task categories as a menu with number options to select
        int i = 1;
        for (GreenSpace greenSpace : greenSpaces) {
            System.out.println("  " + i + " - " + greenSpace.getName());
            i++;
        }
    }


}
