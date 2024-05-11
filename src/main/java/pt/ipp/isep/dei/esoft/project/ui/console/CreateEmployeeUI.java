package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateEmployeeController;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.EmployeeRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        Employee employee = controller.createEmployee(name, dateOfBirth, admissionDate, address, mobile, email, idDocType, docTypeNumber, taxPayerIdNumber, job, skill);

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
        job = displayAndSelectJob();
        skill = displayAndSelectSkill();
    }


    private String requestEmployeeName() {
        String input;
        do {
            input = Utils.readLineFromConsole("Employee Name: ");
            assert input != null;
            if (!Utils.isValidInput(input)){
                System.out.print("Employee Name must only contain letters.\n");
            }
        } while(!Utils.isValidInput(input));
        return input;
    }

    private String requestEmployeeBirthDate() {
        String input;
        do {
            input = Utils.readLineFromConsole("Birth Date (YYYY-MM-DD): ");
            assert input != null;
            if(!Utils.isAtLeast18YearsOld(LocalDate.parse(input))){
                System.out.println("The Employee must be 18 years old or higher.");
            }
        } while (!Utils.isAtLeast18YearsOld(LocalDate.parse(input)));
        return input;
    }

        private String requestEmployeeAdmissionDate () {
            return (Utils.readLineFromConsole("Admission Date (YYYY-MM-DD): "));
        }

        private String requestEmployeeAddress () {
            return (Utils.readLineFromConsole("Address: "));
        }

    private int requestEmployeeMobile() {
        boolean validNumber = false;
        int number = 0;
        while (!validNumber) {
            try {
                number = Integer.parseInt(Objects.requireNonNull(Utils.readLineFromConsole("Mobile Number: ")));
                validNumber = Utils.isMobileNumberCorrect(String.valueOf(number));
            } catch (NumberFormatException e) {
                System.out.println("Error: Enter numbers only");
            }
            if (!validNumber) {
                System.out.println("Nine digits and start with 9.");
            }
        }
        return number;
    }

        private String requestEmployeeEmail () {
            String ler;
            do{
                ler = Utils.readLineFromConsole("Email: ").toLowerCase();
                if(!Utils.isValidEmail(ler)){
                    System.out.println("Email address must be a valid email address.");
                }
            }while(!Utils.isValidEmail(ler));
            return ler;
        }

        private String requestEmployeeIdentificationDocumentType () {
            String input;
            do {
                input = Utils.readLineFromConsole("Identification Document Type: ");
                if (!Utils.isValidInput(input)) {
                    System.out.print("Identification Document Type must only contain letters.\n");
                }
            } while (!Utils.isValidInput(input));
            return input;
        }

        private int requestEmployeeIdentificationDocumentNumber () {
            return Utils.readIntegerFromConsole("Identification Document Number: ");
        }

        private int requestEmployeeTaxPayerNumber () {
            Scanner input = new Scanner(System.in);
            System.out.print("Tax Payer Number: ");
            int ler;
                ler = input.nextInt();
                    System.out.print("Tax Payer Number must have 9 digits. \nTax Payer Number: ");
                    ler = input.nextInt();
            return ler;
        }


        private Job displayAndSelectJob () {
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

        private void displayJobsOptions (List < Job > jobs) {
            //display the task categories as a menu with number options to select
            int i = 1;
            for (Job job : jobs) {
                System.out.println("  " + i + " - " + job.getName());
                i++;
            }
        }

        private ArrayList<Skill> displayAndSelectSkill () {
            //Display the list of task categories
            ArrayList<Skill> skills = controller.getAllSkills();
            ArrayList<Skill> selectedSkills = new ArrayList<>();

            int listSize = skills.size();
            int answer = -1;

            Scanner input = new Scanner(System.in);

            do {
                displaySkillsOptions(skills);
                System.out.println("  0 - Exit");
                System.out.print("Select a Skill: ");
                answer = input.nextInt();
                if (answer > 0 && answer <= listSize) {
                    selectedSkills.add(skills.get(answer - 1));
                }
            } while (answer != 0);
            return selectedSkills;
        }

        private void displaySkillsOptions (List < Skill > skills) {
            //display the task categories as a menu with number options to select
            int i = 1;
            for (Skill skill : skills) {
                System.out.println("  " + i + " - " + skill.getName());
                i++;
            }
        }
    }
