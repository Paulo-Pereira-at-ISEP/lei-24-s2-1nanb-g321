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
            System.out.printf("Hour: %s\n", printHour(hour));
            System.out.printf("Status: %s\n", entry.getStatus());

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

    private String printHour(int hour) {

        if (1 == hour){
            return "08:00 am";
        } else if (2 == hour) {
            return "09:00 am";
        } else if (3 == hour) {
            return "10:00 am";
        } else if (4 == hour) {
            return  "11:00 am";
        } else if (5 == hour) {
            return "12:00 am";
        } else if (6 == hour) {
            return "14:00 pm";
        } else if (7 == hour) {
            return "15:00 pm;";
        } else {
            return "16:00 pm";
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
            do {
                System.out.println("Select the hour to start your task: ");
                System.out.println("[1] - 08:00");
                System.out.println("[2] - 09:00");
                System.out.println("[3] - 10:00");
                System.out.println("[4] - 11:00");
                System.out.println("[5] - 12:00");
                System.out.println("[6] - 14:00");
                System.out.println("[7] - 15:00");
                System.out.println("[8] - 16:00");
                System.out.print("Your choice: ");
                Scanner input = new Scanner(System.in);
                hour = input.nextInt();

                switch (hour) {
                    case 1:
                        return 1;
                    case 2:
                        return 2;
                    case 3:
                        return 3;
                    case 4:
                        return 4;
                    case 5:
                        return 5;
                    case 6:
                        return 6;
                    case 7:
                        return 7;
                    case 8:
                        return 8;
                        default:
                        System.out.println("Invalid choice. Please press a number between 1 and 8.");
                }
            } while (true);

        }
    }



