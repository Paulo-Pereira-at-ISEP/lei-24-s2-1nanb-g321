package pt.ipp.isep.dei.esoft.project.ui.console.agenda;

import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class AgendaUI implements Runnable {
    public AgendaUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Add Entry Agenda", new AddNewEntryUI()));
        options.add(new MenuItem("List Agenda", new ListAgendaUI()));
        options.add(new MenuItem("Add Team to an Entry", new AddTeamToEntry()));
        options.add(new MenuItem("Entries Between Two Dates", new FilterEntriesUI()));


        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- GSM MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
