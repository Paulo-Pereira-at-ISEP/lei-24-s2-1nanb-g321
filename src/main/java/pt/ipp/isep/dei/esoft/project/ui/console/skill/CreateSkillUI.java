package pt.ipp.isep.dei.esoft.project.ui.console.skill;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

/**
 * Create Skill UI (console). This option is only available for administrators for demonstration purposes.
 */
public class CreateSkillUI implements Runnable {

    private final CreateSkillController controller;
    private String skillName;
    private String skillDescription;
    private SkillRepository skillRepository;

    public CreateSkillUI() {
        controller = new CreateSkillController();
    }

    private CreateSkillController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Create Skill ------------------------");

        requestData();

        submitData();
    }

    private void submitData() {
        Skill skill = controller.createSkill(skillName, skillDescription);

        if (skill != null) {
            System.out.println("\nSkill successfully created!");
        } else {
            System.out.println("\nSkill not created!");
        }
    }

    private void requestData() {
            String input;
            do {
                skillName = requestSkillName();
                skillDescription = requestSkillDescription();

                System.out.println("\n\n\n---------- Submitted Data ----------\n");
                System.out.printf("Title: %s\n", skillName);
                System.out.printf("Description: %s\n", skillDescription);

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
            System.out.println("1. Title");
            System.out.println("2. Description");


            String choice = Utils.readLineFromConsole("Enter your choice (1-2): ");
            switch (choice) {
                case "1":
                    return "Title"; // Modify name
                case "2":
                    return "Description"; // Modify dateOfBirth

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 2.");
                    return null;
            }
        }

        private void modifySkillData(String dataToModify) {
            switch (dataToModify) {
                case "Title":

                    skillName = requestSkillName();

                    // Get new name and set it
                    break;
                case "Description":
                    skillDescription = requestSkillDescription(); // Get new dateOfBirth and set it
                    break;

                default:
                    System.out.println("Invalid data field. Data modification failed.");
            }
        }

    /**
     * Prompts the user to input a skill description and validates the input.
     * The input is considered valid if it contains only letters.
     *
     * @return The validated skill description.
     */
    private String requestSkillDescription() {
        return Utils.requestOnlyLetters("Skill Description: ", "Skill Description must only contain letters.\n");
    }

    /**
     * Prompts the user to input a skill name and validates the input.
     * The input is considered valid if it contains only letters.
     *
     * @return The validated skill name.
     */
    private String requestSkillName() {
        return Utils.requestOnlyLetters("Skill Name: ", "Skill Name must only contain letters.\n");
    }
}


