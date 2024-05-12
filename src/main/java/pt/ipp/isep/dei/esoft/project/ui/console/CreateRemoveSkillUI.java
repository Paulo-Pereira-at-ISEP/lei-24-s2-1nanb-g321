package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Assign Skills UI (console). This option is only available for administrators for demonstration purposes.
 */
public class CreateRemoveSkillUI implements Runnable {
    private Skill employee;
    private final CreateSkillController skillController;

    public CreateRemoveSkillUI() {
        skillController = new CreateSkillController();
    }

    public void run() {
        System.out.println("\n\n--- Remove a skill to a collaborator ------------------------");

        requestData();

        submitData();
    }

    private void submitData() {
        if (employee != null) {
            System.out.println("\nSkills successfully removed!");
        } else {
            System.out.println("\nNo skills removed!");
        }
    }

    private void requestData() {
        employee = displayAndSelectEmployee();
        employee.addSkill(displayAndSelectSkill(employee));
    }


    /**
     * Displays the list of available employees and allows the user to select one.
     *
     * @return The selected employee.
     */
    private Employee displayAndSelectEmployee() {
        // Display the list of task categories
        List<Employee> employees = skillController.getAllEmployees();

        int listSize = employees.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        do {
            displayEmployeesOptions(employees);
            System.out.print("Select an Employee: ");
            answer = input.nextInt();
            return employees.get(answer - 1);
        } while (answer < 0 || answer > listSize);
    }

    /**
     * Displays the list of employee options as a menu with numbered options to select.
     *
     * @param employees The list of employees to display.
     */
    private void displayEmployeesOptions(List<Employee> employees) {
        // Display the employee categories as a menu with number options to select
        int i = 1;
        for (Employee employee : employees) {
            System.out.println("  " + i + " - " + employee.getName() + " - " + employee.getEmail());
            i++;
        }
    }

    /**
     * Displays the list of available skills that an employee can be assigned and allows the user to select multiple skills.
     * The user can exit the selection process by entering '0'.
     *
     * @param employee The employee for whom skills are being selected.
     * @return The list of selected skills.
     */
    private ArrayList<Skill> displayAndSelectSkill(Employee employee) {
        Scanner input = new Scanner(System.in);

        // Display the list of task categories
        List<Skill> skills = new ArrayList<>(List.copyOf(skillController.getAllSkills()));
        ArrayList<Skill> selectedSkills = new ArrayList<>();

        // Get the skills already possessed by the employee
        List<Skill> employeeSkills = employee.getSkills();

        // Remove skills that the employee already has
        for (Skill skill : employeeSkills) {
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

    /**
     * Displays the list of skill options as a menu with numbered options to select.
     *
     * @param skills The list of skills to display.
     */
    private void displaySkillsOptions(List<Skill> skills) {
        // Display the skill categories as a menu with number options to select
        int i = 1;
        for (Skill skill : skills) {
            System.out.println("  " + i + " - " + skill.getName());
            i++;
        }
    }

}
