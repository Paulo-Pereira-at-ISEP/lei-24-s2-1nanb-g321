package pt.ipp.isep.dei.esoft.project.ui.console.agenda;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateEntryToAgendaController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class AddNewEntryUI implements Runnable{

    /**
     * Create Skill UI (console). This option is only available for administrators for demonstration purposes.
     */

    private final CreateEntryToAgendaController controller;

    private Entry entrys;
    private int hour;
    private LocalDate date;
    private String status;
    public AddNewEntryUI() {
        controller = new CreateEntryToAgendaController();
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
        Entry entry = controller.createEntry(entrys.getName(), entrys.getDescription(), entrys.getUrgencyDegree(), entrys.getDuration(), entrys.getGreenSpace(), entrys.getEntryDate(), hour);

        if (entry != null) {
            System.out.println("\n\n\n---------- Submitted Data ----------");
            System.out.printf("Entry: %s\n", entry.getName());
            System.out.printf("Date: %s\n", entry.getEntryDate());
            System.out.printf("Duration: %s\n", entry.getDuration());
            System.out.printf("Status: %s\n", entry.getStatus());
            System.out.printf("Stating Hour: %dh\n", entry.getHour());


            System.out.println("\nEntry successfully added to the Agenda!");
        } else {
            System.out.println("\nEntry not created!");
        }
    }

    private void requestData() {
        entrys = displayAndSelectEntry();
        date = LocalDate.parse(requestDate());
        entrys.setEntryDate(date);
        hour = requestHour();
    }


    private Entry displayAndSelectEntry() {
        // Retrieve the list of available jobs
        List<Entry> entry = controller.getAllEntrysFromToDoList();
        int listSize = entry.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);
        while (answer < 1 || answer > listSize) {
            displayEntryOptions(entry); // Display the list of available jobs
            System.out.print("Select an entry: ");
            answer = input.nextInt(); // Prompt user to select a job
        }

        return entry.get(answer - 1); // Return the selected job
    }

    private void displayEntryOptions(List<Entry> ent) {
        // Display the task categories as a menu with number options to select
        int i = 1;
        for (Entry entry : ent) {
            System.out.println("  " + i + " - " + entry.getName());
            i++;
        }
    }


    private CharSequence requestDate() {
        boolean validDate = false;
        String input = "";
        while (!validDate) {
            try {
                input = (Utils.readLineFromConsole("Entry Date (YYYY-MM-DD): ")); // Prompt user for birth date
                validDate = Utils.parseDate(input); // Validate the input date format
                if (validDate) {
                    assert input != null;
                } else {
                    System.out.println("Invalid date format (YYYY-MM-DD)."); // Print error if input date format is invalid
                }
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid date format."); // Print error if there's an exception parsing the date
            }
        }
        return input;
    }
    private int requestHour() {
        Scanner input = new Scanner(System.in);
        int hour;
        do {
            System.out.println("Select the hour to start your task (8-16): ");
            System.out.print("Your choice: ");
            hour = input.nextInt();

            if (hour < 8 || hour > 16) {
                System.out.println("Invalid choice. Please enter a number between 8 and 16.");
            }
        } while (hour < 8 || hour > 16);

        return hour;
    }
    }



