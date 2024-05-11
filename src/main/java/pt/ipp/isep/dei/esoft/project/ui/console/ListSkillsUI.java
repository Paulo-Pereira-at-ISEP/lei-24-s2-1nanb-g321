package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateSkillController;

import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.List;

/**
 * List Skill UI (console). This option is only available for administrators for demonstration purposes.
 */
public class ListSkillsUI implements Runnable {

    private final CreateSkillController skillController;

    public ListSkillsUI() {
        skillController = new CreateSkillController();
    }

    public void run() {
        System.out.println("\n\n--- List Skills ------------------------");
        listSkills();
    }

    /**
     * Lists all registered skills along with their details.
     */
    private void listSkills() {
        List<Skill> skills = skillController.getAllSkills();

        System.out.println("Registered Skills:");
        int counter = 1;
        for (Skill skill : skills) {

            System.out.println("[" + counter + "]   Title: " + skill.getName());
            System.out.println("      Description: " + skill.getDescription());
            System.out.println("-------------------------");
            counter++;
        }
    }

}