package pt.ipp.isep.dei.esoft.project.ui.console.agenda;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateEntryController;
import pt.ipp.isep.dei.esoft.project.domain.Entry;

import java.util.List;

public class ListAgendaUI implements Runnable{

    private final CreateEntryController entryController;

    public ListAgendaUI() {
        entryController = new CreateEntryController();
    }

    public void run() {
        System.out.println("\n\n--- Agenda ------------------------");
        listAgenda();
    }

    /**
     * Lists all registered skills along with their details.
     */
    private void listAgenda() {
        List<Entry> entrys = entryController.getAllEntrys();

        System.out.println("Agenda:");
        int counter = 1;
        for (Entry entry : entrys) {

            System.out.println("[" + counter + "]   GreenSpace: " + entry.getGreenSpace().getName());
            System.out.println("      Manager: " + entry.getGreenSpace().getManager().getName());
            System.out.println("      Title: " + entry.getName());
            System.out.println("      Description: " + entry.getDescription());
            System.out.println("      Urgency Degree: " + entry.getUrgencyDegree());
            System.out.println("-------------------------");
            counter++;
        }
    }

}
