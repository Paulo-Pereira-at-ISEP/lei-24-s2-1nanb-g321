package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateSkillController;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateTaskController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.TaskCategory;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Create Skill UI (console). This option is only available for administrators for demonstration purposes.
 */
public class CreateSkillUI implements Runnable {

    private final CreateSkillController controller;
    private String skillName;
    private String skillDescription;

    public CreateSkillUI() {
        controller = new CreateSkillController();
    }

    private CreateSkillController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Create Skill ------------------------");

        requestData();

        submitData();
    }

    private void submitData() {
        Skill skill = getController().createSkill(skillName, skillDescription);

        if (skill != null) {
            System.out.println("\nSkill successfully created!");
        } else {
            System.out.println("\nSkill not created!");
        }
    }

    private void requestData() {

        //Request the Skill Name from the console
        skillName = requestSkillName();

        //Request the Task Description from the console
        skillDescription = requestSkillDescription();
    }

    private String requestSkillDescription() {
        Scanner input = new Scanner(System.in);
        System.out.print("Skill Description: ");
        return input.nextLine();
    }

    private String requestSkillName() {
        Scanner input = new Scanner(System.in);
        System.out.print("Skill Name: ");
        return input.nextLine();
    }

}