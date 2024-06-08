package pt.ipp.isep.dei.esoft.project.ui.console.todolist;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateEntryController;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import java.util.List;
import java.util.Scanner;

public class CreateEntryUI implements Runnable {


    /**
     * Create Entry UI (console). This option is only available for administrators for demonstration purposes.
     */

    private final CreateEntryController controller;
    private String urgencyDegree;
    private int duration;
    private Task task;
    private GreenSpace greenSpace;
    private Entry entry;

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
        Entry entry = controller.createEntry(task, urgencyDegree, duration, greenSpace);

        if (entry != null) {
            System.out.println("\n\n\n---------- Submitted Data ----------");
            System.out.printf("GreenSpace: %s\n", greenSpace.getName());
            System.out.printf("Title: %s\n", task.getName());
            System.out.printf("Description: %s\n", task.getDescription());
            System.out.println("Urgency Degree: " + urgencyDegree);
            System.out.println("Duration: " + duration + "h");
            System.out.println("Status: " + entry.getStatus());

            System.out.println("\nEntry successfully added to the ToDoList!");
        } else {
            System.out.println("\nEntry not created!");
        }
    }

    private void requestData() {
        greenSpace = displayAndSelectGreenSpaces();
        task = displayAndSelectTasks();
        urgencyDegree = requestUrgencyDegree();
        duration = requestDuration();


    }

    private Task displayAndSelectTasks() {
        List<Task> tasks = controller.getAllTasks();
        int listSize = tasks.size();

        // Check if there are any green spaces available
        if (listSize == 0) {
            System.out.println("No Tasks available.");
            return null; // or throw an exception, or return an optional, depending on your use case
        }

        Scanner input = new Scanner(System.in);
        int answer = -1;

        while (answer < 1 || answer > listSize) {
            displayTasksOptions(tasks);
            System.out.print("Select a Task (1 to " + listSize + "): ");
            if (input.hasNextInt()) {
                answer = input.nextInt();
                if (answer < 1 || answer > listSize) {
                    System.out.println("Invalid selection. Please select a number between 1 and " + listSize + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                input.next(); // Consume the invalid input
            }
        }

        return tasks.get(answer - 1);
    }

    private void displayTasksOptions(List<Task> tasks) {
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

    private int requestDuration() {
        Scanner input = new Scanner(System.in);
        int duration = -1;

        while (duration < 1) {
            System.out.print("What is the duration of the entry (hours)? ");
            if (input.hasNextInt()) {
                duration = input.nextInt();
                if (duration < 1) {
                    System.out.println("Invalid duration. Please enter a positive integer.");
                }
            } else {
                System.out.println("Invalid input. Please enter a positive integer.");
                input.next(); // Consume the invalid input
            }
        }

        return duration;
    }

    private GreenSpace displayAndSelectGreenSpaces() {
        List<GreenSpace> greenSpaces = controller.getAllGreenSpaces();
        int listSize = greenSpaces.size();

        // Check if there are any green spaces available
        if (listSize == 0) {
            System.out.println("No Green Spaces available.");
            return null; // or throw an exception, or return an optional, depending on your use case
        }

        Scanner input = new Scanner(System.in);
        int answer = -1;

        while (answer < 1 || answer > listSize) {
            displayGreenSpacesOptions(greenSpaces);
            System.out.print("Select a GreenSpace (1 to " + listSize + "): ");
            if (input.hasNextInt()) {
                answer = input.nextInt();
                if (answer < 1 || answer > listSize) {
                    System.out.println("Invalid selection. Please select a number between 1 and " + listSize + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                input.next(); // Consume the invalid input
            }
        }

        return greenSpaces.get(answer - 1);
    }

    private void displayGreenSpacesOptions(List<GreenSpace> greenSpaces) {
        int i = 1;
        for (GreenSpace greenSpace : greenSpaces) {
            System.out.println("  " + i + " - " + greenSpace.getName());
            i++;
        }
    }
}