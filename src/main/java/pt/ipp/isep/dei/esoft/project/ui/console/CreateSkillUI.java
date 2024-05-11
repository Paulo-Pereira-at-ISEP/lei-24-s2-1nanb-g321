package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

/**
 * Create Skill UI (console). This option is only available for administrators for demonstration purposes.
 */
public class CreateSkillUI implements Runnable {

    private final CreateSkillController controller;
    private String skillName;
    private String skillDescription;
    private SkillRepository skillRepository;

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
        Skill skill = controller.createSkill(skillName, skillDescription);

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

    /**
     * Prompts the user to input a skill description and validates the input.
     * The input is considered valid if it contains only letters.
     *
     * @return The validated skill description.
     */
    private String requestSkillDescription() {
        return Utils.requestOnlyLetters("Skill Description: ", "Skill Description must only contain letters.\n");
    }

    /**
     * Prompts the user to input a skill name and validates the input.
     * The input is considered valid if it contains only letters.
     *
     * @return The validated skill name.
     */
    private String requestSkillName() {
        return Utils.requestOnlyLetters("Skill Name: ", "Skill Name must only contain letters.\n");
    }
}


