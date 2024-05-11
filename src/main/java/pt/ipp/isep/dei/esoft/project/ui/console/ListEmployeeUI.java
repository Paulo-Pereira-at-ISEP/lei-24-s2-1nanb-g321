package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateEmployeeController;
import pt.ipp.isep.dei.esoft.project.domain.Employee;

import java.util.List;

/**
 * List Employee UI (console). This option is only available for administrators for demonstration purposes.
 */
public class ListEmployeeUI implements Runnable {

    private final CreateEmployeeController controller;

    public ListEmployeeUI() {
        controller = new CreateEmployeeController();
    }

    public void run() {
        System.out.println("\n\n--- List Employees ------------------------");
        listEmployees();
    }

    /**
     * Lists all registered employees along with their details.
     */
    private void listEmployees() {
        List<Employee> employees = controller.getAllEmployees();
        System.out.println("Registered Employees:");
        int counter = 1;
        for (Employee employee: employees) {
            System.out.println("[" + counter + "] ");
            System.out.println("    Name: " + employee.getName());
            System.out.println("    Birth Date: " + employee.getDateOfBirth());
            System.out.println("    Admission Date: " + employee.getAdmissionDate());
            System.out.println("    Address: " + employee.getAddress());
            System.out.println("    Mobile: " + employee.getMobile());
            System.out.println("    Email: " + employee.getEmail());
            System.out.println("    ID: " + employee.getIdDocType());
            System.out.println("    ID Number: " + employee.getDocTypeNumber());
            System.out.println("    Tax Payer Number: " + employee.getTaxPayerIdNumber());
            System.out.println("    Role: " + employee.getRole());
            System.out.println("    Job: " + employee.getJob());
            System.out.println("    Skills: " + employee.getSkills());
            System.out.println("-----------------------------");
            counter++;
        }
    }
}
