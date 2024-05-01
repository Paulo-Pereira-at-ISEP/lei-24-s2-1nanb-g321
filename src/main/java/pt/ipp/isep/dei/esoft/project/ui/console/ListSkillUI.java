package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.SkillController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.List;

/**
 * Create Skill UI (console). This option is only available for administrators for demonstration purposes.
 */
public class ListSkillUI implements Runnable {

    private final SkillController skillController;

    public ListSkillUI() {
        skillController = new SkillController();
    }

    public void run() {
        System.out.println("\n\n--- List Skills ------------------------");
        listSkills();
    }

    private void listSkills() {
        List<Skill> skills = skillController.getAllSkills();
        System.out.println("Habilidades cadastradas:");
        for (Skill skill : skills) {
            System.out.println("Nome: " + skill.getName());
            System.out.println("Descrição: " + skill.getDescription());
            System.out.println("-------------------------");
        }
    }
}