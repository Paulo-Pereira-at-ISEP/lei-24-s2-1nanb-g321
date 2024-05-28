package pt.ipp.isep.dei.esoft.project.ui.console.tasks;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateTaskController;
import pt.ipp.isep.dei.esoft.project.domain.Task;

import java.util.List;

/**
 * List Skill UI (console). This option is only available for administrators for demonstration purposes.
 */
public class ListTasksUI implements Runnable {

    private final CreateTaskController taskController;

    public ListTasksUI() {
        taskController = new CreateTaskController();
    }

    public void run() {
        System.out.println("\n\n--- List Tasks ------------------------");
        listTasks();
    }

    /**
     * Lists all registered skills along with their details.
     */
    private void listTasks() {
        List<Task> tasks = taskController.getAllTasks();

        System.out.println("Registered Tasks:");
        int counter = 1;
        for (Task task : tasks) {

            System.out.println("[" + counter + "]   Title: " + task.getName());
            System.out.println("      Description: " + task.getDescription());
            System.out.println("-------------------------");
            counter++;
        }
    }

}