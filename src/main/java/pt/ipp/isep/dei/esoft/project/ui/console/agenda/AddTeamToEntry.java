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
        // Get entries filtered by date
        if (controller.getAllEntries().isEmpty()) {
            System.out.println("You need to Create an entry first");
        } else {
            List<Entry> entriesFiltered = controller.getEntriesByDate(entry.getEntryDate());

            // Calculate start and end times for the selected entry
            LocalTime startTime = LocalTime.of(entry.getHour(), 0); // Assuming start time is at the beginning of the hour
            LocalTime endTime = startTime.plusHours(entry.getDuration());

            // Check team availability for the specified time range
            boolean teamBooked = false;
            for (Entry e : entriesFiltered) {
                LocalTime eStartTime = e.getStartTime();
                LocalTime eEndTime = e.getEndTime();

                // Check if selected time range overlaps with existing entry's time range
                if ((startTime.isAfter(eStartTime) && startTime.isBefore(eEndTime)) ||
                        (endTime.isAfter(eStartTime) && endTime.isBefore(eEndTime)) ||
                        (startTime.equals(eStartTime) && endTime.equals(eEndTime))) {
                    if (e.getTeam() != null && e.getTeam().getId() == team.getId()) {
                        teamBooked = true;
                        break;
                    }
                }
            }

            // Display appropriate message based on team availability
            if (teamBooked) {
                System.out.println("\nYou can't add that team. They are booked for this time range.");
            } else {

                entry.setTeam(team);
                String message = "You have been assigned to a new task on this date: " + entry.getName() + "\n" + entry.getDescription()
                        + "\n" + entry.getEntryDate() + "at" + entry.getStartTime();
                controller.sendMessageToCollaborators(message, entry.getTeam().getCollaborators());
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
        }else {
            team = displayAndSelectTeam();
        }
    }

    private Team displayAndSelectTeam() {
        // Retrieve the list of available entries
        List<Team> teams = controller.getAllTeams();
        int listSize = teams.size();

        if (listSize == 0) {
            System.out.println("No teams available.");
            return null;
        }

        int answer = -1;
        Scanner input = new Scanner(System.in);
        while (answer < 1 || answer > listSize) {
            displayTeamsOptions(teams); // Display the list of available teams
            System.out.print("Select a Team: ");

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
        // Retrieve the list of available entries
        List<Entry> entry = controller.getAllEntries();
        int listSize = entry.size();

        if (listSize == 0) {
            System.out.println("No entries available.");
            return null; // or handle appropriately
        }

        int answer = -1;
        Scanner input = new Scanner(System.in);
        while (answer < 1 || answer > listSize) {
            displayEntriesOptions(entry); // Display the list of available entries
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

    private void displayEntriesOptions(List<Entry> entries) {
        int i = 1;
        for (Entry entry : entries) {
            System.out.println("  " + i + " - " + entry.getName() + "\n" + entry.getDescription() +
                    "\n" + entry.getEntryDate() + "\n");
            i++;
        }
    }
}
