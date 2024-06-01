package pt.ipp.isep.dei.esoft.project.ui.console.collaborator;

import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class CollaboratorUI implements Runnable {
    public CollaboratorUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register Collaborator", new CreateCollaboratorUI()));
        options.add(new MenuItem("List Collaborators", new ListCollaboratorUI()));
        options.add(new MenuItem("Assign one or more skills to a collaborator", new CreateAssignSkillsUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- HRM MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}