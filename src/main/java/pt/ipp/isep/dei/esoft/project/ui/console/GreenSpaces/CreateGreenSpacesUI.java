package pt.ipp.isep.dei.esoft.project.ui.console.GreenSpaces;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateGreenSpacesController;

import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

/**
 * Create Green Spaces UI (console). This option is only available for administrators for demonstration purposes.
 */
public class CreateGreenSpacesUI implements Runnable {

    private final CreateGreenSpacesController controller;
    private String name;
    private double area;
    private Employee manager;

    public CreateGreenSpacesUI() {
        controller = new CreateGreenSpacesController();
    }

    public CreateGreenSpacesUI(CreateGreenSpacesController controller) {
        this.controller = controller;
    }

    private CreateGreenSpacesController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Create Green Spaces ------------------------");

        //requestData();

        //submitData();
    }
/*
    private void submitData() {
        GreenSpace greenSpace = getController().createGreenSpaces(name, area);

        if (job != null) {
            System.out.println("\nJob successfully created!");
        } else {
            System.out.println("\nJob not created!");
        }
    }

    private void requestData() {
        String input;
        do {
            jobName = requestJobName();
            jobDescription = requestJobDescription();

            System.out.println("\n\n\n---------- Submitted Data ----------\n");
            System.out.printf("Name: %s\n", jobName);
            System.out.printf("Description: %s\n", jobDescription);

            input = Utils.readLineFromConsole("\n Do you confirm this data? (y/n)");
            if (input.equalsIgnoreCase("y")) {
                System.out.println(" Skill successfully registered!.");
                break;
            }

            do {
                boolean modified = false;

                String dataToModify = requestDataModification();
                if (dataToModify != null) {
                    modified = true;
                    modifySkillData(dataToModify); // Call a method to modify specific data
                    System.out.println("Data modified successfully.");
                } else {
                    System.out.println("Invalid data selection. Please try again.");
                }
                input = Utils.readLineFromConsole("Do you want to modify another field? (y/n)");

            } while (!input.equalsIgnoreCase("n"));


            // After confirmation, use the employee object for further processing or storage

        }while (!input.equalsIgnoreCase("n")); // Loop until user confirms

        // After confirmation, use the employee object for further processing or storage
        System.out.println("Data confirmed.");
    }



    private String requestDataModification() {
        System.out.println("\nSelect the data field you want to modify:");
        System.out.println("1. Name");
        System.out.println("2. Description");

        String choice = Utils.readLineFromConsole("Enter your choice (1-2): ");
        switch (choice) {
            case "1":
                return "Name"; // Modify name
            case "2":
                return "Description"; // Modify dateOfBirth
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 2.");
                return null;
        }
    }

    private void modifySkillData(String dataToModify) {
        switch (dataToModify) {
            case "Name":
                name = requestName();
                // Get new name and set it
                break;
            case "Description":
                jobDescription = requestJobDescription(); // Get new dateOfBirth and set it
                break;
            default:
                System.out.println("Invalid data field. Data modification failed.");
        }
    }
    */
}