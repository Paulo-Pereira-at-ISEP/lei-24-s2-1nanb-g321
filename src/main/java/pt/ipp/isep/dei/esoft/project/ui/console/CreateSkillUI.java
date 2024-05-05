package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateJobController;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;

import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.List;
import java.util.Scanner;

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

    private String requestSkillDescription() {
        Scanner input = new Scanner(System.in);
        String ler;
        do {
            System.out.print("Skill Description: ");
            ler = input.nextLine();
            if (!Utils.isValidInput(ler)) {
                System.out.print("Skill Description must only contain letters.\n");
            }
        } while (!Utils.isValidInput(ler));
        return ler;
    }

    private String requestSkillName() {
        Scanner input = new Scanner(System.in);
        String ler;

        do {
            System.out.print("Skill Name: ");
            ler = input.nextLine();
            if (!Utils.isValidInput(ler)) {
                System.out.print("Skill Name must only contain letters.\n");
            }
        } while (!Utils.isValidInput(ler));
        return ler;
    }
}


