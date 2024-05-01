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
public class CreateEmployeeUI implements Runnable {
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

    public CreateEmployeeUI() {
        controller = new CreateEmployeeController();
    }

    private CreateEmployeeController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Create Employee ------------------------");

        requestData();

        submitData();
    }

    private void submitData() {
        Employee employee = getController().createEmployee(name, dateOfBirth, admissionDate, address, mobile, email, idDocType, docTypeNumber, taxPayerIdNumber, role, job);

        if (employee != null) {
            System.out.println("\nEmployee successfully registered!");
        } else {
            System.out.println("\nEmployee not created!");
        }
    }

    private void requestData() {

        name = requestEmployeeName();
        dateOfBirth = LocalDate.parse(requestEmployeeBirthDate());
        admissionDate = LocalDate.parse(requestEmployeeAdmissionDate());
        address = requestEmployeeAddress();
        mobile = requestEmployeeMobile();
        email = requestEmployeeEmail();
        idDocType = requestEmployeeIdentificationDocumentType();
        docTypeNumber = requestEmployeeIdentificationDocumentNumber();
        taxPayerIdNumber = requestEmployeeTaxPayerNumber();
        role = requestEmployeeRole();
        job = displayAndSelectJob();

    }

    private String requestEmployeeName() {
        Scanner input = new Scanner(System.in);
        System.out.print("Employee Name: ");
        return input.nextLine();
    }

    private String requestEmployeeBirthDate() {
        Scanner input = new Scanner(System.in);
        System.out.print("Birth Date: ");
        return input.nextLine();
    }

    private String requestEmployeeAdmissionDate() {
        Scanner input = new Scanner(System.in);
        System.out.print("Admission Date: ");
        return input.nextLine();
    }

    private String requestEmployeeAddress() {
        Scanner input = new Scanner(System.in);
        System.out.print("Address: ");
        return input.nextLine();
    }

    private int requestEmployeeMobile() {
        Scanner input = new Scanner(System.in);
        System.out.print("Mobile: ");
        return input.nextInt();
    }

    private String requestEmployeeEmail() {
        Scanner input = new Scanner(System.in);
        System.out.print("E-mail: ");
        return input.nextLine();
    }

    private String requestEmployeeIdentificationDocumentType() {
        Scanner input = new Scanner(System.in);
        System.out.print("Identification Document Type: ");
        return input.nextLine();
    }

    private int requestEmployeeIdentificationDocumentNumber() {
        Scanner input = new Scanner(System.in);
        System.out.print("Identification Document Number: ");
        return input.nextInt();
    }

    private int requestEmployeeTaxPayerNumber() {
        Scanner input = new Scanner(System.in);
        System.out.print("Tax Payer Number: ");
        return input.nextInt();
    }

    private String requestEmployeeRole() {
        Scanner input = new Scanner(System.in);
        System.out.print("Employee Role: ");
        return input.nextLine();
    }

    private Job displayAndSelectJob() {
        //Display the list of task categories
        List<Job> jobs = controller.getAllJobs();

        int listSize = jobs.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            displayJobsOptions(jobs);
            System.out.print("Select a job: ");
            answer = input.nextInt();
        }

        return jobs.get(answer - 1);
    }

    private void displayJobsOptions(List<Job> jobs) {
        //display the task categories as a menu with number options to select
        int i = 1;
        for (Job job : jobs) {
            System.out.println("  " + i + " - " + job.getName());
            i++;
        }
    }

}
