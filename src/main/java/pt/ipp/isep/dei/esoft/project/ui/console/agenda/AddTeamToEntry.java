package pt.ipp.isep.dei.esoft.project.ui.console.agenda;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateTeamToEntryController;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.time.LocalTime;
import java.util.InputMismatchException;
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

        if (controller.getAllEntries().isEmpty()) {
            System.out.println("You need to Create an entry first");
        } else {
            List<Entry> entriesFiltered = controller.getEntriesByDate(entry.getEntryDate());

            LocalTime startTime = LocalTime.of(entry.getHour(), 0);
            LocalTime endTime = startTime.plusHours(entry.getDuration());

            boolean teamBooked = false;
            for (Entry e : entriesFiltered) {
                LocalTime eStartTime = e.getStartTime();
                LocalTime eEndTime = e.getEndTime();

                if ((startTime.isAfter(eStartTime) && startTime.isBefore(eEndTime)) ||
                        (endTime.isAfter(eStartTime) && endTime.isBefore(eEndTime)) ||
                        (startTime.equals(eStartTime) && endTime.equals(eEndTime))) {
                    if (e.getTeam() != null && e.getTeam().getId() == team.getId()) {
                        teamBooked = true;
                        break;
                    }
                }
            }

            if (teamBooked) {
                System.out.println("\nYou can't add that team. They are booked for this time range.");
            } else {
                entry.setTeam(team);
                controller.sendMessageToCollaborators(team, entry.getName());
                System.out.println("\nTeam successfully added to the Entry!");
            }
        }
    }

    private void requestData() {
        AgendaUI agendaUI = new AgendaUI();
        entry = displayAndSelectEntry();
        if (entry == null) {
            System.out.println("\nYou need to Create an entry first");
            agendaUI.run();
        } else if (controller.getAllTeams().isEmpty()) {
            System.out.println("The HRM have not created any teams");
            agendaUI.run();
        } else {
            team = displayAndSelectTeam();
        }
    }

    private Team displayAndSelectTeam() {
        List<Team> teams = controller.getAllTeams();
        int listSize = teams.size();

        if (listSize == 0) {
            System.out.println("No teams available.");
            return null;
        }

        int answer = -1;
        Scanner input = new Scanner(System.in);
        while (answer < 1 || answer > listSize) {
            displayTeamsOptions(teams);
            System.out.print("Select a Team: ");

            try {
                answer = input.nextInt();
                if (answer < 1 || answer > listSize) {
                    System.out.println("Invalid selection. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                input.next();
            }
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
        List<Entry> entry = controller.getAllEntries();
        int listSize = entry.size();

        if (listSize == 0) {
            System.out.println("No entries available.");
            return null;
        }

        int answer = -1;
        Scanner input = new Scanner(System.in);
        while (answer < 1 || answer > listSize) {
            displayEntriesOptions(entry);
            System.out.print("Select an entry: ");

            try {
                answer = input.nextInt();
                if (answer < 1 || answer > listSize) {
                    System.out.println("Invalid selection. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                input.next();
            }
        }

        return entry.get(answer - 1);
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
