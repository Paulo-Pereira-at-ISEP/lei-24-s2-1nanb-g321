package pt.ipp.isep.dei.esoft.project.ui.console.manager;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateManagerController;
import pt.ipp.isep.dei.esoft.project.domain.Manager;

import java.util.List;

/**
 * List Managers UI (console). This option is only available for administrators for demonstration purposes.
 */
public class ListManagerUI implements Runnable {

    private final CreateManagerController controller;

    public ListManagerUI() {
        controller = new CreateManagerController();
    }

    public void run() {
        System.out.println("\n\n--- List Managers ------------------------");
        listManagers();
    }

    /**
     * Lists all registered employees along with their details.
     */
    private void listManagers() {
        List<Manager> managers = controller.getAllManagers();
        System.out.println("Registered Employees:");
        int counter = 1;
        for (Manager manager: managers) {
            System.out.println("[" + counter + "] ");
            System.out.println("    Name: " + manager.getName());
            System.out.println("    Birth Date: " + manager.getDateOfBirth());
            System.out.println("    Admission Date: " + manager.getAdmissionDate());
            System.out.println("    Address: " + manager.getAddress());
            System.out.println("    Mobile: " + manager.getMobile());
            System.out.println("    Email: " + manager.getEmail());
            System.out.println("    ID (CC-Passport): " + manager.getIdDocType());
            System.out.println("    ID Number: " + manager.getDocTypeNumber());
            System.out.println("    Tax Payer Number: " + manager.getTaxPayerIdNumber());
            System.out.println("    Job: " + manager.getJob());

            System.out.println("    Department: " + manager.getDepartment());
            System.out.println("-----------------------------");
            counter++;
        }
    }
}
