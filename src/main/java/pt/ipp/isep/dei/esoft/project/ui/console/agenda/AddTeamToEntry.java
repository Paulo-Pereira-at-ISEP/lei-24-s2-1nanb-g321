package pt.ipp.isep.dei.esoft.project.ui.console.agenda;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateEntryToAgendaController;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateTeamToEntryController;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.AgendaRepository;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddTeamToEntry implements Runnable {

    private final CreateTeamToEntryController controller;

    private Team team;
    private Entry entrys;
    private AgendaRepository agendaRepository;
    public AddTeamToEntry() {
        controller = new CreateTeamToEntryController();
    }

    private CreateTeamToEntryController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Add Team to Entry in Agenda ------------------------");

        requestData();

        submitData();
    }

    private void submitData() {

        Entry entry = controller.createEntry(entrys.getName(), entrys.getDescription(), entrys.getUrgencyDegree(), entrys.getDuration(), entrys.getGreenSpace(), entrys.getEntryDate(), entrys.getStatus(), entrys.getHour(), team);

        if (entry != null) {
            System.out.println("\nTeam successfully added to the Entry!");
        } else {
            System.out.println("\nTeam not added to the Entry!");
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
        agendaRepository = new AgendaRepository();
        entrys = displayAndSelectEntry();
        team = displayAndSelectTeam();
        List<Entry> filteredEntries = agendaRepository.getEntriesByDate(entrys.getEntryDate());
        if (!filteredEntries.isEmpty()) {
            for (Entry entry : filteredEntries) {
                if (entry.getTeam().equals(entrys.getTeam())) ;
                System.out.println("You cannot book this date");
                break;
            }
        }



        System.out.println("\n\n\n---------- Submitted Data ----------");
        System.out.printf("Entry: %s\n", entrys.getName());
        System.out.printf("Date: %s\n", entrys.getEntryDate());
        System.out.printf("Hour: %s\n", entrys.getHour());
        System.out.printf("Status: %s\n", entrys.getStatus());
        System.out.printf("Team : %s \n", team);


    }

    private Team displayAndSelectTeam() {
        // Retrieve the list of available jobs
        List<Team> teams = controller.getAllTeams();
        int listSize = teams.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);
        while (answer < 1 || answer > listSize) {
            displayTeamsOptions(teams); // Display the list of available jobs
            System.out.print("Select a Team: ");
            answer = input.nextInt(); // Prompt user to select a job
        }

        return teams.get(answer - 1); // Return the selected job
    }


    private void displayTeamsOptions(List<Team> teams) {
        // Display the task categories as a menu with number options to select
        int i = 1;
        for (Team team1 : teams) {
            System.out.println("  " + i + " - " + team1.getId() + "\n" + team1.getCollaborators());
            i++;
        }
    }
    private Entry displayAndSelectEntry() {
        // Retrieve the list of available jobs
        List<Entry> entrys1 = controller.getAllEntrys();
        int listSize = entrys1.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);
        while (answer < 1 || answer > listSize) {
            displayEntrysOptions(entrys1); // Display the list of available jobs
            System.out.print("Select an Entry: ");
            answer = input.nextInt(); // Prompt user to select a job
        }

        return entrys1.get(answer - 1);
    }


    private void displayEntrysOptions(List<Entry> entrys) {
        // Display the task categories as a menu with number options to select
        int i = 1;
        for (Entry entry : entrys) {
            System.out.println("  " + i + " - " + entry.getName() + "\n" + entry.getDescription() +
                     "\n" + entry.getEntryDate() + "\n");
            i++;
        }
    }
}
