package pt.ipp.isep.dei.esoft.project.ui.console.agenda;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateEntryToAgendaController;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListMyAgendaUI implements Runnable {

    private final CreateEntryToAgendaController entryController;
    private LocalDate startDate;
    private LocalDate endDate;

    public ListMyAgendaUI() {
        entryController = new CreateEntryToAgendaController();
    }

    public void run() {
        System.out.println("\n\n--- Agenda ------------------------");

        requestData();

        listMyAgenda();
    }

    private void requestData() {
        startDate = LocalDate.parse(requestDate("Start"));

        endDate = LocalDate.parse(requestDate("End"));
    }

    /**
     * Lists all registered skills along with their details.
     */
    private void listMyAgenda() {
        List<Entry> entries = entryController.getAllEntries();
        List<Entry> sortedEntries = entryController.getSortedEntriesByDate(entries);

        List<Entry> myEntries = new ArrayList<>();

        for (Entry entry : sortedEntries) {
            LocalDate entryDate = entry.getEntryDate();
            if (entryDate.isEqual(startDate) || entryDate.isEqual(endDate) || (entryDate.isAfter(startDate) && entryDate.isBefore(endDate))) {
                if (entry.getTeam() != null){
                    for (Collaborator collaborator : entry.getTeam().getCollaborators()) {
                        if (Objects.equals(collaborator.getEmail(), ApplicationSession.getInstance().getCurrentSession().getUserEmail())){
                            myEntries.add(entry);
                        }
                    }
                }
            }
        }

        if (myEntries.isEmpty()) {
            System.out.println("There are no entries within the specified period.");
        } else {
            System.out.println("Agenda for the specified period:");
            int counter = 1;
            for (Entry entry : myEntries) {
                System.out.println("[" + counter + "]   GreenSpace: " + entry.getGreenSpace().getName());
                System.out.println("      Manager: " + entry.getGreenSpace().getManager().getName());
                System.out.println("      Title: " + entry.getName());
                System.out.println("      Description: " + entry.getDescription());
                System.out.println("      Urgency Degree: " + entry.getUrgencyDegree());
                System.out.println("      Date: " + entry.getEntryDate());
                if (entry.getTeam() != null) {
                    System.out.println("      Team: " + entry.getTeam().getId()); // Assuming Team has a getId method
                }
                System.out.println("      Status: " + entry.getStatus());
                System.out.println("-------------------------");
                counter++;
            }
        }
    }

    private String requestDate(String string) {
        boolean validDate = false;
        String inputDate = "";
        while (!validDate) {
            try {
                inputDate = (Utils.readLineFromConsole(string + "Date (YYYY-MM-DD): ")); // Prompt user for admission date
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
}
