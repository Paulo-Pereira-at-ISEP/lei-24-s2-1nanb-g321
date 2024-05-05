package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateEmployeeController;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.EmployeeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Create Skill UI (console). This option is only available for administrators for demonstration purposes.
 */
public class ListEmployeeUI implements Runnable {
    private EmployeeRepository employeeRepository;
    private final CreateEmployeeController controller;
    private String name;
    private LocalDate dateOfBirth;
    private LocalDate admissionDate;
    private String address;
    private int mobile;
    private String email;
    private String idDocType;
    private int docTypeNumber;
    private int taxPayerIdNumber;
    private String role;

    private Job job;

    public ListEmployeeUI() {
        controller = new CreateEmployeeController();
    }

    private CreateEmployeeController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- List Employees ------------------------");
        listEmployees();
    }

    private void listEmployees() {
        List<Employee> employees = controller.getAllEmployees();
        System.out.println("Registered Jobs:");
        for (Employee employee: employees) {
            System.out.println("Name: " + employee.getName());
            System.out.println("Birth Date: " + employee.getDateOfBirth());
            System.out.println("Admission Date: " + employee.getAdmissionDate());
            System.out.println("Address: " + employee.getAddress());
            System.out.println("Mobile: " + employee.getMobile());
            System.out.println("Email: " + employee.getEmail());
            System.out.println("ID: " + employee.getIdDocType());
            System.out.println("ID Number: " + employee.getDocTypeNumber());
            System.out.println("Tax Payer Number: " + employee.getTaxPayerIdNumber());
            System.out.println("Role: " + employee.getRole());
            System.out.println("Job: " + employee.getJob());
            System.out.println("-------------------------");
        }
    }

}