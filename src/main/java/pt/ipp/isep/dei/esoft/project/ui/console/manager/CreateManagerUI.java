package pt.ipp.isep.dei.esoft.project.ui.console.manager;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateManagerController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Manager;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Create Employee UI (console). This option is only available for administrators for demonstration purposes.
 */
public class CreateManagerUI implements Runnable {
    private final CreateManagerController controller;
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
    private String password = "admin";
    private String department = "HRM";
    private String role;
    private AuthenticationRepository authenticationRepository;

    public CreateManagerUI() {
        controller = new CreateManagerController();
    }

    private CreateManagerController getController() {
        return controller;
    }



    public void run() {
        System.out.println("\n\n--- Create Employee ------------------------");
        authenticationRepository = controller.getAuthenticationRepository();
        requestData();

        submitData();
    }

    private void submitData() {
        Manager manager = getController().addManager(name, dateOfBirth, admissionDate, address, mobile, email, idDocType, docTypeNumber, taxPayerIdNumber, job, password, department, role);

        if (manager != null) {
            System.out.println("\nEmployee successfully registered!");

            if (this.role.equals(AuthenticationController.ROLE_HRM)) {
                    authenticationRepository.addUserWithRole(this.name,this.email,this.password,
                            AuthenticationController.ROLE_HRM);
                }
                else if (this.role.equals(AuthenticationController.ROLE_VFM)) {
                    authenticationRepository.addUserWithRole(this.name,this.email,this.password,
                            AuthenticationController.ROLE_VFM);
                }
                else if (this.role.equals(AuthenticationController.ROLE_QAM)) {
                    authenticationRepository.addUserWithRole(this.name,this.email,this.password,
                            AuthenticationController.ROLE_QAM);
                }
                else if (this.role.equals(AuthenticationController.ROLE_GSM)) {
                    authenticationRepository.addUserWithRole(this.name,this.email,this.password,
                            AuthenticationController.ROLE_GSM);
                }
                else {
                    authenticationRepository.addUserWithRole(this.name,this.email,this.password,
                            AuthenticationController.ROLE_Collaborator);
                }



        } else {
            System.out.println("\nEmployee not created!");
        }
    }

    private void requestData() {
        String input;

        do {
            name = requestEmployeeName();
            dateOfBirth = LocalDate.parse(requestEmployeeBirthDate());
            admissionDate = LocalDate.parse(requestEmployeeAdmissionDate());
            address = requestEmployeeAddress();
            mobile = requestEmployeeMobile();
            email = requestEmployeeEmail();
            idDocType = requestEmployeeIdentificationDocumentType();
            docTypeNumber = requestEmployeeIdentificationDocumentNumber();
            taxPayerIdNumber = requestEmployeeTaxPayerNumber();

            System.out.println("\n\n\n---------- Submitted Data ----------\n");
            System.out.printf("Name: %s\n", name);
            System.out.printf("Birth Date: %s\n", dateOfBirth);
            System.out.printf("Admission Date: %s\n", admissionDate);
            System.out.printf("Address: %s\n", address);
            System.out.printf("Mobile: %s\n", mobile);
            System.out.printf("E-mail: %s\n", email);
            System.out.printf("Doc Type: %s\n", idDocType);
            System.out.printf("Doc Type Number: %s\n", docTypeNumber);
            System.out.printf("Tax Payer ID Number: %s\n", taxPayerIdNumber);

            input = Utils.readLineFromConsole("\n Do you confirm this data? (y/n): ");
            if (input.equalsIgnoreCase("y")) {
                System.out.println(" You can continue the registering process.");
                job = displayAndSelectJob();
                role = displayAndSelectRole();
                break;
            }

                do {
                    boolean modified = false;

                    String dataToModify = requestDataModification();
                    if (dataToModify != null) {
                        modified = true;
                        modifyEmployeeData(dataToModify); // Call a method to modify specific data
                        System.out.println("Data modified successfully.");
                    } else {
                        System.out.println("Invalid data selection. Please try again.");
                    }
                    input = Utils.readLineFromConsole("Do you want to modify another field? (y/n): ");

                } while (!input.equalsIgnoreCase("n"));


            // After confirmation, use the employee object for further processing or storage
            System.out.println("Data confirmed. You can now select the jobs and skills: ");

            job = displayAndSelectJob();
            role = displayAndSelectRole();

        }while (!input.equalsIgnoreCase("n")); // Loop until user confirms


    }
    private String requestDataModification() {
        System.out.println("\nSelect the data field you want to modify:");
        System.out.println("1. Name");
        System.out.println("2. Birth Date");
        System.out.println("3. Admission Date");
        System.out.println("4. Address");
        System.out.println("5. Mobile");
        System.out.println("6. E-mail");
        System.out.println("7. Doc Type");
        System.out.println("8. Doc Type Number");
        System.out.println("9. Tax Payer ID Number");

        String choice = Utils.readLineFromConsole("Enter your choice (1-9): ");
        switch (choice) {
            case "1":
                return "name"; // Modify name
            case "2":
                return "dateOfBirth"; // Modify dateOfBirth
            case "3":
                return "admissionDate"; // Modify admissionDate
            case "4":
                return "address"; // Modify address
            case "5":
                return "mobile"; // Modify mobile
            case "6":
                return "email"; // Modify email
            case "7":
                return "idDocType"; // Modify idDocType
            case "8":
                return "docTypeNumber"; // Modify docTypeNumber
            case "9":
                return "taxPayerIdNumber"; // Modify taxPayerIdNumber
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 9.");
                return null;
        }
    }

    private void modifyEmployeeData(String dataToModify) {
        switch (dataToModify) {
            case "name":
                name = requestEmployeeName();
                    // Get new name and set it
                break;
            case "dateOfBirth":
                dateOfBirth = LocalDate.parse(requestEmployeeBirthDate()); // Get new dateOfBirth and set it
                break;
            case "admissionDate":
                admissionDate = LocalDate.parse(requestEmployeeAdmissionDate()); // Get new admissionDate and set it
                break;
            case "address":
                address =requestEmployeeAddress(); // Get new address and set it
                break;
            case "mobile":
                mobile = requestEmployeeMobile(); // Get new mobile and set it
                break;
            case "email":
                email = requestEmployeeEmail(); // Get new email and set it
                break;
            case "idDocType":
                idDocType = requestEmployeeIdentificationDocumentType(); // Get new idDocType and set it
                break;
            case "docTypeNumber":
                docTypeNumber = requestEmployeeIdentificationDocumentNumber(); // Get new docTypeNumber and set it
                break;
            case "taxPayerIdNumber":
                taxPayerIdNumber = requestEmployeeTaxPayerNumber(); // Get new taxPayerIdNumber and set it
                break;
            default:
                System.out.println("Invalid data field. Data modification failed.");
        }
    }



    /**
     * Prompts the user to input an employee name and validates the input.
     * The input is considered valid if it contains only letters.
     *
     * @return The validated employee name.
     */
    private String requestEmployeeName() {
        String input;
        do {
            input = Utils.readLineFromConsole("Employee Name: "); // Prompt user for employee name
            assert input != null; // Ensure input is not null
            if (!Utils.isValidInput(input)){
                System.out.print("Employee Name must only contain letters.\n"); // Print error message if input is invalid
            }
        } while(!Utils.isValidInput(input)); // Loop until a valid employee name is input
        return input; // Return the validated employee name
    }

    /**
     * Prompts the user to input an employee birth date and validates the input.
     * The input is considered valid if it is in the format "YYYY-MM-DD" and represents
     * a date where the employee is at least 18 years old.
     *
     * @return The validated employee birth date.
     */
    private String requestEmployeeBirthDate() {
        boolean validDate = false;
        String input = "";
        while (!validDate) {
            try {
                input = (Utils.readLineFromConsole("Birth Date (YYYY-MM-DD): ")); // Prompt user for birth date
                validDate = Utils.parseDate(input); // Validate the input date format
                if (validDate) {
                    assert input != null;
                    if (!Utils.isAtLeast18YearsOld(LocalDate.parse(input))) {
                        System.out.println("The Employee must be 18 years old or higher."); // Print error if employee is not at least 18 years old
                        validDate = false;
                    }
                } else {
                    System.out.println("Invalid date format (YYYY-MM-DD)."); // Print error if input date format is invalid
                }
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid date format."); // Print error if there's an exception parsing the date
            }
        }
        return input; // Return the validated employee birth date
    }

    /**
     * Prompts the user to input an employee admission date and validates the input.
     * The input is considered valid if it is in the format "YYYY-MM-DD".
     *
     * @return The validated employee admission date.
     */
    private String requestEmployeeAdmissionDate() {
        boolean validDate = false;
        String input = "";
        while (!validDate) {
            try {
                input = (Utils.readLineFromConsole("Admission Date (YYYY-MM-DD): ")); // Prompt user for admission date
                validDate = Utils.parseDate(input); // Validate the input date format
                if (!validDate) {
                    System.out.println("Invalid date format (YYYY-MM-DD)."); // Print error if input date format is invalid
                }
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid date format."); // Print error if there's an exception parsing the date
            }
        }
        return input; // Return the validated employee admission date
    }

    /**
     * Prompts the user to input an employee address.
     *
     * @return The input employee address.
     */
    private String requestEmployeeAddress() {
        return (Utils.readLineFromConsole("Address: ")); // Prompt user for address and return the input
    }

    /**
     * Prompts the user to input an employee mobile number and validates the input.
     * The input is considered valid if it is a 9-digit number starting with '9'.
     *
     * @return The validated employee mobile number.
     */
    private int requestEmployeeMobile() {
        boolean validNumber = false;
        int number = 0;
        while (!validNumber) {
            try {
                number = Integer.parseInt(Objects.requireNonNull(Utils.readLineFromConsole("Mobile Number: "))); // Prompt user for mobile number and parse to integer
                validNumber = Utils.isMobileNumberCorrect(String.valueOf(number)); // Validate the input mobile number
            } catch (NumberFormatException e) {
                System.out.println("Error: Enter numbers only"); // Print error if input is not a valid number
            }
            if (!validNumber) {
                System.out.println("Nine digits and start with 9."); // Print error if input mobile number is invalid
            }
        }
        return number; // Return the validated employee mobile number
    }

    /**
     * Prompts the user to input an employee email address and validates the input.
     * The input is considered valid if it is a correctly formatted email address.
     *
     * @return The validated employee email address.
     */
    private String requestEmployeeEmail() {
        String input;
        do {
            input = Objects.requireNonNull(Utils.readLineFromConsole("Email: ")).toLowerCase(); // Prompt user for email address and convert to lowercase
            if (!Utils.isValidEmail(input)) {
                System.out.println("Email address must be a valid email address."); // Print error if input email address is invalid
            }
        } while (!Utils.isValidEmail(input)); // Continue prompting until a valid email address is input
        return input; // Return the validated employee email address
    }

    /**
     * Prompts the user to input the type of employee identification document and validates the input.
     * The input is considered valid if it contains only letters.
     *
     * @return The validated employee identification document type.
     */
    private String requestEmployeeIdentificationDocumentType() {
        String input;
        do {
            input = Utils.readLineFromConsole("Identification Document Type: "); // Prompt user for identification document type
            assert input != null; // Ensure input is not null
            if (!Utils.isValidInput(input)) {
                System.out.print("Identification Document Type must only contain letters.\n"); // Print error if input is invalid
            }
        } while (!Utils.isValidInput(input)); // Continue prompting until a valid identification document type is input
        return input; // Return the validated employee identification document type
    }

    /**
     * Requests the employee's identification document number from the user.
     * This method prompts the user to input the identification document number,
     * validates the input to ensure it contains only numeric characters,
     * and returns the validated identification document number as an integer.
     *
     * @return The validated employee identification document number.
     */
    private int requestEmployeeIdentificationDocumentNumber () {
        String input;
        do {
            input = Utils.readLineFromConsole("Identification Document Number: "); // Prompt user for identification document type
            assert input != null;
            if (!Utils.isValidInputInt(input)) {
                System.out.print("Identification Document Number must only contain numbers.\n"); // Print error if input is invalid
            }
        } while (!Utils.isValidInputInt(input)); // Continue prompting until a valid identification document type is input
        return Integer.parseInt(input); // Return the validated employee identification document type
    }

    /**
     * Prompts the user to input an employee tax payer number and validates the input.
     * The input is considered valid if it is a valid Portuguese NIF (Número de Identificação Fiscal).
     *
     * @return The validated employee tax payer number.
     */
    private int requestEmployeeTaxPayerNumber() {
        String input;
        do {
            input = Utils.readLineFromConsole("Tax Payer Number: "); // Prompt user for tax payer number
            assert input != null; // Ensure input is not null
            if (!Utils.isValidNIF(input)){
                System.out.print("Invalid Tax Payer Number.\n"); // Print error if input is invalid
            }
        } while(!Utils.isValidNIF(input)); // Continue prompting until a valid tax payer number is input
        return Integer.parseInt(input); // Return the validated employee tax payer number
    }

    /**
     * Displays the list of available jobs and allows the user to select one.
     *
     * @return The selected job.
     */
    private Job displayAndSelectJob() {
        // Retrieve the list of available jobs
        List<Job> jobs = controller.getAllJobs();
        int listSize = jobs.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);
        while (answer < 1 || answer > listSize) {
            displayJobsOptions(jobs); // Display the list of available jobs
            System.out.print("Select a job: ");
            answer = input.nextInt(); // Prompt user to select a job
        }

        return jobs.get(answer - 1); // Return the selected job
    }

    /**
     * Displays the list of job options as a menu with numbered options to select.
     *
     * @param jobs The list of jobs to display.
     */
    private void displayJobsOptions(List<Job> jobs) {
        // Display the task categories as a menu with number options to select
        int i = 1;
        for (Job job : jobs) {
            System.out.println("  " + i + " - " + job.getName());
            i++;
        }
    }

    /**************************/

    private String displayAndSelectRole() {
        // Retrieve the list of available jobs
        List<String> roles = AuthenticationController.roles;
        int listSize = roles.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);
        while (answer < 1 || answer > listSize) {
            displayRolesOptions(roles); // Display the list of available jobs
            System.out.print("Select a Role: ");
            answer = input.nextInt(); // Prompt user to select a job
        }

        return roles.get(answer - 1); // Return the selected job
    }

    private void displayRolesOptions(List<String> roles) {
        // Display the task categories as a menu with number options to select
        int i = 1;
        for (String role : roles) {
            System.out.println("  " + i + " - " + role);
            i++;
        }
    }



}