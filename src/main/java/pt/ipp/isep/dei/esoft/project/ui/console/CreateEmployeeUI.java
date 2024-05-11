package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateEmployeeController;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Create Skill UI (console). This option is only available for administrators for demonstration purposes.
 */
public class CreateEmployeeUI implements Runnable {
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
        Employee employee = getController().createEmployee(name, dateOfBirth, admissionDate, address, mobile, email, idDocType, docTypeNumber, taxPayerIdNumber, job, skill);

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
        boolean validDate = false;
        String input="";
        while (!validDate) {
            try {
                input = (Utils.readLineFromConsole("Birth Date: "));
                validDate = Utils.parseDate(input);
                if (validDate) {
                    assert input != null;
                    if(!Utils.isAtLeast18YearsOld(LocalDate.parse(input))){
                        System.out.println("The Employee must be 18 years old or higher.");
                        validDate = false;
                    }
                } else {
                    System.out.println("Invalid date format (YYYY-MM-DD).");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid date format.");
            }
        }
        return input;
    }

    private String requestEmployeeAdmissionDate () {
        boolean validDate = false;
        String input="";
        while (!validDate) {
            try {
                input = (Utils.readLineFromConsole("Admission Date: "));
                validDate = Utils.parseDate(input);
                if (!validDate) {
                    System.out.println("Invalid date format (YYYY-MM-DD).");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid date format.");
            }
        }
        return input;
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
        String input;
        do{
            input = Objects.requireNonNull(Utils.readLineFromConsole("Email: ")).toLowerCase();
            if(!Utils.isValidEmail(input)){
                System.out.println("Email address must be a valid email address.");
            }
        }while(!Utils.isValidEmail(input));
        return input;
    }

    private String requestEmployeeIdentificationDocumentType () {
        String input;
        do {
            input = Utils.readLineFromConsole("Identification Document Type: ");
            assert input != null;
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
        String input;
        do {
            input = Utils.readLineFromConsole("Tax Payer Number: ");
            assert input != null;
            if (!Utils.isValidNIF(input)){
                System.out.print("Invalid Tax Payer Number.\n");
            }
        } while(!Utils.isValidNIF(input));
        return Integer.parseInt(input);
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
        Scanner input = new Scanner(System.in);

        //Display the list of task categories
        List<Skill> skills = new ArrayList<>(List.copyOf(controller.getAllSkills()));
        ArrayList<Skill> selectedSkills = new ArrayList<>();
        int listSize = skills.size();
        int answer = -1;

        do {
            displaySkillsOptions(skills);
            System.out.println("  0 - Exit");
            System.out.print("Select a Skill: ");
            answer = input.nextInt();
            if (answer > 0 && answer <= listSize) {
                selectedSkills.add(skills.get(answer - 1));
                skills.remove(answer-1);
                listSize--;
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