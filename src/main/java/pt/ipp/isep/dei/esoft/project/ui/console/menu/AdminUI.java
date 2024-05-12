package pt.ipp.isep.dei.esoft.project.ui.console.menu;



import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.employee.CreateEmployeeUI;
import pt.ipp.isep.dei.esoft.project.ui.console.job.CreateJobUI;
import pt.ipp.isep.dei.esoft.project.ui.console.skill.CreateSkillUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class AdminUI implements Runnable {
    public AdminUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Create Skill", new CreateSkillUI()));
        options.add(new MenuItem("Create Job", new CreateJobUI()));
        options.add(new MenuItem("Register Collaborator", new CreateEmployeeUI()));
        options.add(new MenuItem("Assign one or more skills to a collaborator", new ShowTextUI("You have chosen Option 4.")));
        options.add(new MenuItem("Generate a team proposal", new ShowTextUI("You have chosen Option 3.")));


        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- ADMIN MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}