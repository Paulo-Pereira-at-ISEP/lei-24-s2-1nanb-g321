package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.SkillController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

import java.util.Scanner;

/**
 * Create Skill UI (console). This option is only available for administrators for demonstration purposes.
 */
public class CreateSkillUI implements Runnable {

    private final SkillController controller;
    private String skillName;
    private String skillDescription;
    private SkillRepository skillRepository;

    public CreateSkillUI() {
        controller = new SkillController(skillRepository);
    }

    private SkillController getController() {
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