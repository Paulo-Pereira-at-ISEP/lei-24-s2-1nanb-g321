package pt.ipp.isep.dei.esoft.project.ui.console.agenda;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateAgendaEntryController;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateEntryController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.TeamDTO;

import java.util.List;
import java.util.Scanner;

public class AddNewEntryUI implements Runnable{

    /**
     * Create Skill UI (console). This option is only available for administrators for demonstration purposes.
     */

    private final CreateAgendaEntryController controller;
    private int hourOfDay;
    private String day;
    private String status;
    private Team team;
    private Entry entrys;
    private AgendaEntry agendaEntry;

    public AddNewEntryUI() {
        controller = new CreateAgendaEntryController();
    }

    private CreateAgendaEntryController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Create Entry to Agenda ------------------------");

        requestData();

        submitData();
    }

    private void submitData() {
        AgendaEntry entry = controller.createAgendaEntry(entrys.getName(), entrys.getDescription(), entrys.getUrgencyDegree(), entrys.getDuration(), entrys.getGreenSpace(), day, hourOfDay, status, team);

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
        day = requestDay();
        hourOfDay = requestHourOfDay();


        System.out.println("\n\n\n---------- Submitted Data ----------");
        System.out.printf("Entry: %s\n", entrys.getName());
        System.out.println("Team:\n" + team);
        System.out.printf("Day: %s\n", day);
        System.out.printf("Hour of the day: %s\n", hourOfDay);
        System.out.println("Status: " + "Planned");


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

    private String requestDay() {
        do {
            System.out.println("Choose one of the following days: ");
            System.out.println("[1] - Monday");
            System.out.println("[2] - Tuesday");
            System.out.println("[3] - Wednesday");
            System.out.println("[4] - Thursday");
            System.out.println("[5] - Friday");
            System.out.println("[6] - Saturday");

            System.out.print("Your choice: ");
            Scanner input = new Scanner(System.in);
            day = input.nextLine();

            switch (day) {
                case "1":
                    return "Monday";
                case "2":
                    return "Tuesday";
                case "3":
                    return "Wednesday";
                case "4":
                    return "Thursday";
                case "5":
                    return "Friday";
                case "6":
                    return "Saturday";
                default:
                    System.out.println("Invalid choice. Please enter '1', '2','3', '4', '5' or '6'.");
            }
        } while (true);

    }


    private int requestHourOfDay() {
        do {
            System.out.println("Choose one of the following Hours: ");
            System.out.println("[1] - 09:00 am");
            System.out.println("[2] - 10:00 am");
            System.out.println("[3] - 11:00 am");
            System.out.println("[4] - 12:00 am");
            System.out.println("[5] - 14:00 pm");
            System.out.println("[6] - 15:00 pm");
            System.out.println("[7] - 16:00 pm");

            System.out.print("Your choice: ");
            Scanner input = new Scanner(System.in);
            day = input.nextLine();

            switch (day) {
                case "1":
                    return 9;
                case "2":
                    return 10;
                case "3":
                    return 11;
                case "4":
                    return 12;
                case "5":
                    return 14;
                case "6":
                    return 15;
                case "7":
                    return 16;
                default:
                    System.out.println("Invalid choice. Please enter '1', '2','3', '4', '5','6' or '7'.");
            }
        } while (true);
    }
}

