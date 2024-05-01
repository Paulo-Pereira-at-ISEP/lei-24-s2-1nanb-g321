package pt.ipp.isep.dei.esoft.project.ui.console;

/*import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamController;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

import java.util.Scanner;

public class GenerateTeamUI implements Runnable {

    private final GenerateTeamController controller;
    private int teamSize;
    private SkillRepository skillRepository;

    public GenerateTeamUI() {
        controller = new GenerateTeamController();
    }

    private GenerateTeamController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Create Team ------------------------");

        requestData();

        submitData();
    }

    private void submitData() {
        Team team = getController().generateTeam(teamSize, listOfSkills);

        if (team != null) {
            System.out.println("\nTeam successfully generated!");
        } else {
            System.out.println("\nTeam not generated!");
        }
    }

    private void requestData() {

        //Request the Team Size from the console
        teamSize = requestTeamSize();

    }

    private int requestTeamSize() {
        Scanner input = new Scanner(System.in);
        System.out.print("Team Size: ");
        return input.nextInt();
    }

}
 */