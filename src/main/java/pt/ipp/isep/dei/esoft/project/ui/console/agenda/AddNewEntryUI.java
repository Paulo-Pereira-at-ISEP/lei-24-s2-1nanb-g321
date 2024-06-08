package pt.ipp.isep.dei.esoft.project.ui.console.agenda;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateEntryToAgendaController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AddNewEntryUI implements Runnable {

    private final CreateEntryToAgendaController controller;
    private Entry entrys;
    private int hour;
    private LocalDate date;
    private String status;
    private Scanner input; // Declare a single Scanner instance

    public AddNewEntryUI() {
        controller = new CreateEntryToAgendaController();
        input = new Scanner(System.in); // Initialize the Scanner instance
    }

    private CreateEntryToAgendaController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Add Entry to Agenda ------------------------");

        requestData();

        submitData();
    }

    private void submitData() {
        if (controller.getAllToDoListEntrys().isEmpty()) {
            System.out.println("You need to create an entry first");
        } else {
            Entry entry = controller.createEntry(entrys.getName(), entrys.getDescription(), entrys.getUrgencyDegree(), entrys.getDuration(), entrys.getGreenSpace(), entrys.getEntryDate(), hour);

            if (entry != null) {
                System.out.println("\n\n\n---------- Submitted Data ----------");
                System.out.printf("Entry: %s\n", entry.getName());
                System.out.printf("Date: %s\n", entry.getEntryDate());
                System.out.printf("Duration: %sh\n", entry.getDuration());
                System.out.printf("Status: %s\n", entry.getStatus());
                System.out.printf("Starting Hour: %sh\n", entry.getHour());
                System.out.printf("Ending Hour: %sh\n", (entry.getHour() + entry.getDuration()));

                System.out.println("\nEntry successfully added to the Agenda!");
            } else {
                System.out.println("\nEntry not created!");
            }
        }
    }

    private void requestData() {
        entrys = displayAndSelectEntry();
        if (entrys != null) {
            date = LocalDate.parse(requestDate());
            entrys.setEntryDate(date);
            hour = requestHour();
        }
    }

    private Entry displayAndSelectEntry() {
        // Retrieve the list of available entries
        List<Entry> entry = controller.getAllToDoListEntrys();
        int listSize = entry.size();

        if (listSize == 0) {
            System.out.println("No entries available.");
            return null; // or handle appropriately
        }

        int answer = -1;
        while (answer < 1 || answer > listSize) {
            displayEntryOptions(entry); // Display the list of available entries
            System.out.print("Select an entry: ");

            try {
                answer = input.nextInt(); // Prompt user to select an entry
                if (answer < 1 || answer > listSize) {
                    System.out.println("Invalid selection. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                input.next(); // Clear the invalid input
            }
        }

        return entry.get(answer - 1); // Return the selected entry
    }

    private void displayEntryOptions(List<Entry> ent) {
        // Display the task categories as a menu with number options to select
        int i = 1;
        for (Entry entry : ent) {
            System.out.println("  " + i + " - " + entry.getName());
            i++;
        }
    }

    private String requestDate() {
        boolean validDate = false;
        String inputDate = "";
        while (!validDate) {
            try {
                inputDate = (Utils.readLineFromConsole("Date (YYYY-MM-DD): ")); // Prompt user for admission date
                validDate = Utils.parseDate(inputDate); // Validate the input date format
                if (!validDate) {
                    System.out.println("Invalid date format (YYYY-MM-DD)."); // Print error if input date format is invalid
                }
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid date format."); // Print error if there's an exception parsing the date
            }
        }
        return inputDate; // Return the validated employee admission date
    }

    private int requestHour() {
        int hour;
        do {
            System.out.println("Select the hour to start your task (8-16): ");
            System.out.print("Your choice: ");
            try {
                hour = input.nextInt();
                if (hour < 8 || hour > 16) {
                    System.out.println("Invalid choice. Please enter a number between 8 and 16.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                input.next(); // Clear the invalid input
                hour = -1; // Reset hour to ensure the loop continues
            }
        } while (hour < 8 || hour > 16);

        return hour;
    }
}
