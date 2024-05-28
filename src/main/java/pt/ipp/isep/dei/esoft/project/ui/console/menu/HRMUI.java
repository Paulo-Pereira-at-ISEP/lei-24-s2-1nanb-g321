package pt.ipp.isep.dei.esoft.project.ui.console.menu;


import pt.ipp.isep.dei.esoft.project.ui.console.tasks.TaskUI;
import pt.ipp.isep.dei.esoft.project.ui.console.job.JobUI;
import pt.ipp.isep.dei.esoft.project.ui.console.collaborator.CollaboratorUI;
import pt.ipp.isep.dei.esoft.project.ui.console.manager.ManagerUI;
import pt.ipp.isep.dei.esoft.project.ui.console.skill.SkillUI;
import pt.ipp.isep.dei.esoft.project.ui.console.team.TeamUI;
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
        options.add(new MenuItem("Skills", new SkillUI()));
        options.add(new MenuItem("Jobs", new JobUI()));
        options.add(new MenuItem("Manager", new ManagerUI()));
        options.add(new MenuItem("Collaborator", new CollaboratorUI()));
        options.add(new MenuItem("Team", new TeamUI()));
        options.add(new MenuItem("Tasks", new TaskUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- HRM MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}