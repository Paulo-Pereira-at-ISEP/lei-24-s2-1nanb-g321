package pt.ipp.isep.dei.esoft.project.ui.console.team;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateManagerController;
import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Manager;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * List Managers UI (console). This option is only available for administrators for demonstration purposes.
 */
public class ListTeamUI implements Runnable {

    private final GenerateTeamController controller;

    public ListTeamUI() {
        controller = new GenerateTeamController();
    }

    public void run() {
        System.out.println("\n\n--- List Team ------------------------");
        listTeams();
    }

    /**
     * Lists all registered employees along with their details.
     */
    private void listTeams() {
        List<Team> teams = controller.getAllTeams();
        System.out.println("Registered Teams:");
        int counter = 1;
        for (Team team: teams) {
            int counter2 = 1;
            List <Collaborator> collaborators = team.getCollaborators();
            System.out.println("[Team " + counter + "] ");
            for (Collaborator collaborator: collaborators){
                System.out.print("[" + counter2 +"] ");
                System.out.println(" Name: " + collaborator.getName());
                counter2++;
            }
            System.out.println("-----------------------------");
            counter++;
        }
    }
}
