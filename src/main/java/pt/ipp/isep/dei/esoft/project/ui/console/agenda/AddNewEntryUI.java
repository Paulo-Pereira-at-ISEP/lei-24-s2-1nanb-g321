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
    private Team team;
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
        Entry entry = controller.createEntry(entrys.getName(), entrys.getDescription(), entrys.getUrgencyDegree(), entrys.getDuration(), entrys.getGreenSpace(), entrys.getEntryDate(), status, team);

        if (entry != null) {
            System.out.println("\nEntry successfully added to the Agenda!");
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
        entrys = displayAndSelectEntry();
        team = displayAndSelectTeams();
        date = LocalDate.parse(requestDate());
        entrys.setEntryDate(date);
        status = requestStatus();



        System.out.println("\n\n\n---------- Submitted Data ----------");
        System.out.printf("Entry: %s\n", entrys.getName());
        System.out.println("Team:\n" + team);
        System.out.printf("Date: %s\n", entrys.getEntryDate());
        System.out.println("Status: %s\n" + status);


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
    private Entry displayAndSelectEntry() {
        // Retrieve the list of available jobs
        List<Entry> entry = controller.getAllEntrys();
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

    private Team displayAndSelectTeams() {
        // Retrieve the list of available jobs
        List<Team> teams = controller.getAllTeams();
        int listSize = teams.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);
        while (answer < 1 || answer > listSize) {
            displayTeamsOptions(teams); // Display the list of available jobs
            System.out.print("Select a team: ");
            answer = input.nextInt(); // Prompt user to select a job
        }

        return teams.get(answer - 1); // Return the selected job
    }

    private void displayTeamsOptions(List<Team> tms) {
        // Display the task categories as a menu with number options to select
        int i = 1;
        for (Team teams : tms) {
            System.out.println("  " + i + " - " + teams.getCollaborators());
            i++;
        }
    }

    private String requestStatus() {
        do {
            System.out.println("Select the following status: ");
            System.out.println("[1] - Planned");
            System.out.print("Your choice: ");
            Scanner input = new Scanner(System.in);
            status = input.nextLine();

            switch (status) {
                case "1":
                    return "Planned";
                default:
                    System.out.println("Invalid choice. Please press '1'.");
            }
        } while (true);

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
        return input; // Return the validated employee birth date
    }

    }


