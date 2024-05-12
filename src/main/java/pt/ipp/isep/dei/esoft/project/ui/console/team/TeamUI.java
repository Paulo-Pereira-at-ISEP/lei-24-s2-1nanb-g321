package pt.ipp.isep.dei.esoft.project.ui.console.team;

import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;
import pt.ipp.isep.dei.esoft.project.ui.console.skill.CreateSkillUI;
import pt.ipp.isep.dei.esoft.project.ui.console.skill.ListSkillsUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class TeamUI implements Runnable {
    public TeamUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Generate Team", new GenerateTeamUI()));
        //options.add(new MenuItem("List Teams", new ListTeamsUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- HRM MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}