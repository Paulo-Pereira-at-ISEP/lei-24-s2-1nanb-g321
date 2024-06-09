package pt.ipp.isep.dei.esoft.project.ui.console.menu;


import pt.ipp.isep.dei.esoft.project.ui.console.GraphAnalysisUI;
import pt.ipp.isep.dei.esoft.project.ui.console.GreenSpaces.GreenSpacesUI;
import pt.ipp.isep.dei.esoft.project.ui.console.agenda.AgendaUI;
import pt.ipp.isep.dei.esoft.project.ui.console.tasks.TaskUI;
import pt.ipp.isep.dei.esoft.project.ui.console.todolist.ToDoListUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class GSMUI implements Runnable {
    public GSMUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Graph analysis", new GraphAnalysisUI()));
        options.add(new MenuItem("Green Spaces", new GreenSpacesUI()));
        options.add(new MenuItem("Tasks", new TaskUI()));
        options.add(new MenuItem("To Do List", new ToDoListUI()));
        options.add(new MenuItem("Agenda", new AgendaUI()));
        options.add(new MenuItem("Evac sign routes (US17)", new AgendaUI()));
        options.add(new MenuItem("Evac sign routes (US18)", new AgendaUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- ADMIN MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}