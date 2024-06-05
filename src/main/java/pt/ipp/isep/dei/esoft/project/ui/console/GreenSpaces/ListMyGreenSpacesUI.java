package pt.ipp.isep.dei.esoft.project.ui.console.GreenSpaces;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateGreenSpacesController;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

import java.util.List;

public class ListMyGreenSpacesUI implements Runnable {

    private final CreateGreenSpacesController greenSpacesController;

    ListMyGreenSpacesUI() {
        greenSpacesController = new CreateGreenSpacesController();
    }

    @Override
    public void run() {
        System.out.println("\n\n-- ListGreenSpacesUI ------------------");
        listGreenSpaces();
    }

    private void listGreenSpaces() {
        List<GreenSpace> greenSpaces = greenSpacesController.getGreenSpacesManaged(ApplicationSession.getInstance().getCurrentSession().getUserEmail());

        System.out.println("Registered Green Spaces:");
        int counter = 1;
        for (GreenSpace greenSpace : greenSpaces) {

            System.out.println("[" + counter + "]\nTitle: " + greenSpace.getName());
            System.out.println("Description: " + greenSpace.getArea());
            System.out.println("Classification: " + greenSpace.getClassification());
            System.out.println("Manager: " + greenSpace.getManager().getName());
            System.out.println("-------------------------");
            counter++;
        }
    }
}
