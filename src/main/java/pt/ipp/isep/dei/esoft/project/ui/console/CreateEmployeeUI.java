package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateEmployeeController;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.EmployeeRepository;

import java.time.LocalDate;
import java.util.ArrayList;
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
    private ArrayList<Skill> skill;

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
        Employee employee = controller.createEmployee(name, dateOfBirth, admissionDate, address, mobile, email, idDocType, docTypeNumber, taxPayerIdNumber, role, job, skill);

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
        skill = displayAndSelectSkill();


    }
    private static boolean isValidString(String inputString) {
        if (inputString.isEmpty()) {
            System.out.println("Error: Input cannot be empty.");
            return false;
        } else if (!inputString.matches("[a-zA-Z]+")) {
            System.out.println("Error: Input must contain only letters.");
            return false;
        }
        return true;
    }
    private static boolean isValidInteger(int inputInt) {
        if (inputInt < 0) {
            System.out.println("Error: Input cannot be negative.");
            return false;
        }
        return true;
    }
    private String requestEmployeeName() {
        Scanner input = new Scanner(System.in);
        String ler;
        do {
            System.out.print("Employee Name: " );
        ler = input.nextLine();

        } while(!isValidString(ler));
        return ler;
        }

    private String requestEmployeeBirthDate() {
        Scanner input = new Scanner(System.in);
        System.out.print("Birth Date (YYYY-MM-DD): ");
        return input.nextLine();
    }

    private String requestEmployeeAdmissionDate() {
        Scanner input = new Scanner(System.in);
        System.out.print("Admission Date: ");
        return input.nextLine();
    }

    private String requestEmployeeAddress() {
        Scanner input = new Scanner(System.in);
        String ler;
            System.out.print("Address: ");
            ler= input.nextLine();

        return ler;
    }

    private int requestEmployeeMobile() {
        Scanner input = new Scanner(System.in);
        int ler;
                    System.out.print("Mobile: ");
                    ler= input.nextInt();
        return ler;

    }

    private String requestEmployeeEmail() {
        Scanner input = new Scanner(System.in);
        System.out.print("E-mail: ");
        return input.nextLine();
    }

    private String requestEmployeeIdentificationDocumentType() {
        Scanner input = new Scanner(System.in);
        do {
            System.out.print("Identification Document Type: ");
            return input.nextLine();
        }while(!isValidString(input.nextLine()));
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
    private ArrayList<Skill> displayAndSelectSkill() {
        //Display the list of task categories
        ArrayList<Skill> skills = controller.getAllSkills();
        ArrayList<Skill> selectedSkills = new ArrayList<>();

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
