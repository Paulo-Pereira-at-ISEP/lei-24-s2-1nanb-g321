package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CreateAssignSkillsUI implements Runnable {
        private Employee employee;
        private final CreateSkillController skillController;
    private String skillName;
    private String skillDescription;

        public CreateAssignSkillsUI() {
            skillController = new CreateSkillController();
        }

        public void run() {
            System.out.println("\n\n--- Assign a skill to a collaborator ------------------------");

            requestData();

            submitData();
        }

        private void submitData() {
            Skill skill = skillController.createSkill(skillName, skillDescription);

            if (employee != null) {
                System.out.println("\nSkills successfully assigned!");
            } else {
                System.out.println("\nNo skills selected!");
            }
        }

        private void requestData() {
        displayAndSelectEmployee();
        displayAndSelectSkill();
        }

        private void listSkills() {
            List<Skill> skills = skillController.getAllSkills();

            System.out.println("Registered Jobs:");
            int counter = 1;
            for (Skill skill : skills) {

                System.out.println("[" + counter + "]   Title: " + skill.getName());
                System.out.println("      Description: " + skill.getDescription());
                System.out.println("-------------------------");
                counter++;
            }
        }
    private Employee displayAndSelectEmployee() {
        //Display the list of task categories
        List<Employee> employees = skillController.getAllEmployees();

        int listSize = employees.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        do{
            displayEmployeesOptions(employees);
            System.out.print("Select an Employee: ");
            answer = input.nextInt();
            return employees.get(answer - 1);
        } while(answer < 0 || answer > listSize);

    }
    private void displayEmployeesOptions(List<Employee> employees) {
        //display the task categories as a menu with number options to select
        int i = 1;
        for (Employee employee : employees) {
            System.out.println("  " + i + " - " + employee.getName());
            i++;
        }
    }
    private List<Skill> displayAndSelectSkill() {
        //Display the list of task categories
        List<Skill> skills = skillController.getAllSkills();
        List<Skill> selectedSkills = new ArrayList<>();

        int listSize = skills.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        do {
            displaySkillsOptions(skills);
            System.out.print("Select a Skill: ");
            answer = input.nextInt();
            if (answer > 0 && answer <= listSize) {
                selectedSkills.add(skills.get(answer - 1));
            }
        } while (answer != 0);
        return selectedSkills;
    }

    private void displaySkillsOptions(List<Skill> skills) {
        //display the task categories as a menu with number options to select
        int i = 1;
        for (Skill skill : skills) {
            System.out.println("  " + i + " - " + skill.getDescription());
            i++;
        }
    }

}
