package pt.ipp.isep.dei.esoft.project.ui.console.agenda;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateFilteredEntriesController;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;

public class FilterEntriesUI implements Runnable {
    private final CreateFilteredEntriesController controller;
    private LocalDate date;
    private LocalDate date2;

    public FilterEntriesUI() {
        controller = new CreateFilteredEntriesController();
    }

    private CreateFilteredEntriesController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Filter Entries in Agenda ------------------------");

        requestData();

        submitData();
    }

    private void submitData() {
        List<Entry> filteredEntries = controller.getFilteredEntries(date, date2);
        if (filteredEntries != null && !filteredEntries.isEmpty()) {
            for (Entry entry : filteredEntries) {
                System.out.printf("Entry: %s\n", entry.getName());
                System.out.printf("Date: %s\n", entry.getEntryDate());
                System.out.printf("Hour: %s\n", entry.getHour());
                System.out.printf("Status: %s\n", entry.getStatus());
                System.out.println();
            }
        } else {
            System.out.println("\nNo entries found for the given date range!");
        }
    }

    private void requestData() {
        date = LocalDate.parse(requestDate("Start Date (YYYY-MM-DD): "));
        date2 = LocalDate.parse(requestDate("End Date (YYYY-MM-DD): "));
        if (date.isAfter(date2)) {
            System.out.println("For the end date, you selected an earlier date (" + date2 + ") compared to the start date (" + date + ").");

            // Prompt user to verify between the two dates
            String input = Utils.readLineFromConsole("Do you want to verify between these two dates? " + date + " - " + date2 + " (Y/N): ");
            switch (input.toLowerCase()) {
                case "y":
                    // Swap the dates
                    LocalDate otherDate = date;
                    date = date2;
                    date2 = otherDate;
                    break;
                case "n":
                    handleExitOrSelectAnotherDate();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void handleExitOrSelectAnotherDate() {
        // Prompt user for action
        System.out.println("Do you want to exit or select another date?");
        System.out.println("[1] - Select another date");
        System.out.println("[2] - Go to previous menu");

        String input = Utils.readLineFromConsole("Your choice: ");
        switch (input) {
            case "1":
                // Request new dates
                requestData();
                break;
            case "2":
                // Exit the program
                System.out.println("Exiting program.");
                AgendaUI agendaUI = new AgendaUI(); // Create an instance of AgendaUI
                agendaUI.run(); // Run the AgendaUI menu
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                handleExitOrSelectAnotherDate(); // Retry input
                break;
        }
    }

    private String requestDate(String prompt) {
        boolean validDate = false;
        String input = "";
        while (!validDate) {
            try {
                input = Utils.readLineFromConsole(prompt); // Prompt user for date
                validDate = Utils.parseDate(input); // Validate the input date format
                if (!validDate) {
                    System.out.println("Invalid date format (YYYY-MM-DD)."); // Print error if input date format is invalid
                }
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid date format."); // Print error if there's an exception parsing the date
            }
        }
        return input;
    }
}
