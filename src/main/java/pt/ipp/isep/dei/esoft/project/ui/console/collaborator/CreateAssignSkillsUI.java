package pt.ipp.isep.dei.esoft.project.ui.console.collaborator;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateAssignSkillsUI implements Runnable {
    private Collaborator collaborator;
    private final CreateSkillController skillController;

    public CreateAssignSkillsUI() {
        skillController = new CreateSkillController();
    }

    public void run() {
        System.out.println("\n\n--- Assign a skill to a collaborator ------------------------");

        requestData();

        submitData();
    }

    private void submitData() {
        if (collaborator != null) {
            System.out.println("\nSkills successfully assigned!");
        } else {
            System.out.println("\nNo skills selected!");
        }
    }

    private void requestData() {
        collaborator = displayAndSelectCollaborator();
        collaborator.addSkill(displayAndSelectSkill(collaborator));
    }

    private Collaborator displayAndSelectCollaborator() {
        // Display the list of task categories
        List<Collaborator> collaborators = skillController.getAllCollaborators();

        int listSize = collaborators.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        do {
            displayEmployeesOptions(collaborators);
            System.out.print("Select an Collaborator: ");
            answer = input.nextInt();
            return collaborators.get(answer - 1);
        } while (answer < 0 || answer > listSize);
    }

    private void displayEmployeesOptions(List<Collaborator> collaborators) {
        // Display the employee categories as a menu with number options to select
        int i = 1;
        for (Collaborator collaborator : collaborators) {
            System.out.println("  " + i + " - " + collaborator.getName() + " - " + collaborator.getEmail());
            i++;
        }
    }

    private ArrayList<Skill> displayAndSelectSkill(Collaborator collaborator) {
        Scanner input = new Scanner(System.in);

        // Display the list of task categories
        List<Skill> skills = new ArrayList<>(List.copyOf(skillController.getAllSkills()));
        ArrayList<Skill> selectedSkills = new ArrayList<>();

        // Get the skills already possessed by the employee
        List<Skill> collaboratorSkills = collaborator.getSkills();

        // Remove skills that the employee already has
        for (Skill skill : collaboratorSkills) {
            skills.remove(skill);
        }

        int listSize = skills.size();
        int answer = -1;

        do {
            displaySkillsOptions(skills); // Display the list of available skills
            System.out.println("  0 - Exit");
            System.out.print("Select a Skill: ");
            answer = input.nextInt(); // Prompt user to select a skill or exit
            if (answer > 0 && answer <= listSize) {
                selectedSkills.add(skills.get(answer - 1));
                skills.remove(answer - 1);
                listSize--;
            }
        } while (answer != 0); // Continue the selection process until the user exits
        return selectedSkills; // Return the list of selected skills
    }

    private void displaySkillsOptions(List<Skill> skills) {
        // Display the skill categories as a menu with number options to select
        int i = 1;
        for (Skill skill : skills) {
            System.out.println("  " + i + " - " + skill.getName());
            i++;
        }
    }



}
