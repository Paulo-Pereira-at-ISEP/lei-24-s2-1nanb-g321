package pt.ipp.isep.dei.esoft.project.ui.console.GreenSpaces;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateGreenSpacesController;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Manager;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.List;
import java.util.Scanner;

/**
 * Create Green Spaces UI (console). This option is only available for administrators for demonstration purposes.
 */
public class CreateGreenSpacesUI implements Runnable {

    private final CreateGreenSpacesController controller;
    private String name;
    private double area;
    private String classification;
    private Manager manager;

    public CreateGreenSpacesUI() {
        controller = new CreateGreenSpacesController();
    }

    private CreateGreenSpacesController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Create Green Spaces ------------------------");

        requestData();

        submitData();
    }

    private void submitData() {
        GreenSpace greenSpace = getController().createGreenSpace(name, area, classification, manager);

        if (greenSpace != null) {
            System.out.println("\nGreen Space successfully registered!");
        } else {
            System.out.println("\nGreen Space not registered!");
        }
    }

    private void requestData() {
        name = requestGreenSpaceName();
        area = requestArea();
        classification = requestClassification();
        manager = displayAndSelectManager();

        System.out.println("\n\n\n---------- Submitted Data ----------\n");
        System.out.printf("Name: %s\n", name);
        System.out.printf("Area: %s\n", area);
        System.out.printf("Classification: %s\n", classification);
    }

    private String requestGreenSpaceName() {

        String input;
        do {
            input = Utils.readLineFromConsole("Green Space Name: "); // Prompt user for green space name
            assert input != null; // Ensure input is not null
            if (!Utils.isValidInput(input)) {
                System.out.print("Green Space Name must only contain letters.\n"); // Print error message if input is invalid
            }
        } while (!Utils.isValidInput(input)); // Loop until a valid green space name is input

        return input; // Return the validated green space name
    }

    private double requestArea() {
        double number = 0;
        boolean valid = false;

        while (!valid) {
            try {
                String input = Utils.readLineFromConsole("Green Space Area: "); // Prompt user for green space area
                assert input != null;
                number = Double.parseDouble(input);
                if (Utils.isValidInputInt(input)) {
                    valid = true; // Input is valid, exit the loop
                } else {
                    System.out.print("Green Space Area must only contain numbers.\n"); // Print error message if input is invalid
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number.\n"); // Handle non-numeric input
            }
        }

        return number; // Return the validated green space area
    }

    private String requestClassification() {
        do {
            System.out.println("Choose one of the following Green Space classifications: ");
            System.out.println("[1] - Garden");
            System.out.println("[2] - Medium-Sized Park");
            System.out.println("[3] - Large-Sized Park");
            System.out.print("Your choice: ");
            Scanner input = new Scanner(System.in);
            classification = input.nextLine();

            switch (classification) {
                case "1":
                    return "Garden";
                case "2":
                    return "Medium-Sized Park";
                case "3":
                    return "Large-Sized Park";
                default:
                    System.out.println("Invalid choice. Please enter '1', '2', or '3'.");
            }
        } while (true);

    }

    private Manager displayAndSelectManager() {
        // Retrieve the list of available managers
        List<Manager> managers = controller.getAllManagers();
        int listSize = managers.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);
        while (answer < 1 || answer > listSize) {
            displayManagerOptions(managers); // Display the list of available managers
            System.out.print("Select a Manager: ");
            answer = input.nextInt(); // Prompt user to select a manager
        }

        return managers.get(answer - 1); // Return the selected manager
    }

    private void displayManagerOptions(List<Manager> managers) {
        // Display the managers as a menu with number options to select
        int i = 1;
        for (Manager manager : managers) {
            System.out.println("  " + i + " - " + manager.getName());
            i++;
        }
    }
}