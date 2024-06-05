package pt.ipp.isep.dei.esoft.project.ui.console.agenda;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateTeamToEntryController;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.List;
import java.util.Scanner;

public class AddTeamToEntry implements Runnable {

    private final CreateTeamToEntryController controller;
    private Team team;
    private Entry entry;

    public AddTeamToEntry() {
        controller = new CreateTeamToEntryController();
    }

    public void run() {
        System.out.println("\n\n--- Add Team to Entry in Agenda ------------------------");

        requestData();
        submitData();
    }

    private void submitData() {
        List<Entry> entriesFiltered = controller.getEntriesByDate(entry.getEntryDate());

        boolean teamBooked = false;
        for (Entry e : entriesFiltered) {
            if (e.getTeam() != null && e.getTeam().getId() == team.getId()) {
                teamBooked = true;
                break;
            }
        }

        if (teamBooked) {
            System.out.println("\nYou can't add that team.\n They are booked for this day.");
        } else {
            entry.setTeam(team);
            System.out.println("\nTeam successfully added to the Entry!");
        }
    }

    private void requestData() {
        entry = displayAndSelectEntry();
        team = displayAndSelectTeam();
    }

    private Team displayAndSelectTeam() {
        List<Team> teams = controller.getAllTeams();
        int listSize = teams.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);
        while (answer < 1 || answer > listSize) {
            displayTeamsOptions(teams);
            System.out.print("Select a Team: ");
            answer = input.nextInt();
        }

        return teams.get(answer - 1);
    }

    private void displayTeamsOptions(List<Team> teams) {
        int i = 1;
        for (Team team1 : teams) {
            System.out.println("  " + i + " - " + team1.getId() + "\n" + team1.getCollaborators());
            i++;
        }
    }

    private Entry displayAndSelectEntry() {
        List<Entry> entries = controller.getAllEntries();
        int listSize = entries.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);
        while (answer < 1 || answer > listSize) {
            displayEntriesOptions(entries);
            System.out.print("Select an Entry: ");
            answer = input.nextInt();
        }

        return entries.get(answer - 1);
    }

    private void displayEntriesOptions(List<Entry> entries) {
        int i = 1;
        for (Entry entry : entries) {
            System.out.println("  " + i + " - " + entry.getName() + "\n" + entry.getDescription() +
                    "\n" + entry.getEntryDate() + "\n");
            i++;
        }
    }
}
