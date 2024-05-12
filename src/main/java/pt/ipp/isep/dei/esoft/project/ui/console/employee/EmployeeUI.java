package pt.ipp.isep.dei.esoft.project.ui.console.employee;

import pt.ipp.isep.dei.esoft.project.ui.console.job.CreateJobUI;
import pt.ipp.isep.dei.esoft.project.ui.console.job.ListJobsUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class EmployeeUI implements Runnable {
    public EmployeeUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register Collaborator", new CreateEmployeeUI()));
        options.add(new MenuItem("List Collaborators", new ListEmployeeUI()));
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