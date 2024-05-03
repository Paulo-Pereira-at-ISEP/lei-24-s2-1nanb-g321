package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.application.controller.SkillController;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class HRMUI implements Runnable {
    public HRMUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Create Skill", new CreateSkillUI()));
        options.add(new MenuItem("List Skills", new ListSkillUI()));
        options.add(new MenuItem("Create Job", new CreateJobUI()));
        options.add(new MenuItem("List Job", new ListJobsUI()));
        options.add(new MenuItem("Register Collaborator", new CreateEmployeeUI()));
        options.add(new MenuItem("List Collaborator", new ListEmployeeUI()));

        options.add(new MenuItem("Assign one or more skills to a collaborator", new ListSkillUI()));
        options.add(new MenuItem("Generate a team proposal", new ShowTextUI("You have chosen Option 5.")));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- HRM MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}