package pt.ipp.isep.dei.esoft.project.ui.console.collaborator;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Manager;

import java.util.List;

/**
 * List Collaborators UI (console). This option is only available for administrators for demonstration purposes.
 */
public class ListCollaboratorUI implements Runnable {

    private final CreateCollaboratorController controller;

    public ListCollaboratorUI() {
        controller = new CreateCollaboratorController();
    }

    public void run() {
        System.out.println("\n\n--- List Collaborators ------------------------");
        listCollaborators();
    }

    /**
     * Lists all registered employees along with their details.
     */
    private void listCollaborators() {
        List<Collaborator> collaborators = controller.getAllCollaborators();
        System.out.println("Registered Employees:");
        int counter = 1;
        for (Collaborator collaborator: collaborators) {
            System.out.println("[" + counter + "] ");
            System.out.println("    Name: " + collaborator.getName());
            System.out.println("    Birth Date: " + collaborator.getDateOfBirth());
            System.out.println("    Admission Date: " + collaborator.getAdmissionDate());
            System.out.println("    Address: " + collaborator.getAddress());
            System.out.println("    Mobile: " + collaborator.getMobile());
            System.out.println("    Email: " + collaborator.getEmail());
            System.out.println("    ID (CC-Passport): " + collaborator.getIdDocType());
            System.out.println("    ID Number: " + collaborator.getDocTypeNumber());
            System.out.println("    Tax Payer Number: " + collaborator.getTaxPayerIdNumber());
            System.out.println("    Job: " + collaborator.getJob());

            System.out.println("    Skills: " + collaborator.getSkills());
            System.out.println("-----------------------------");
            counter++;
        }
    }
}
