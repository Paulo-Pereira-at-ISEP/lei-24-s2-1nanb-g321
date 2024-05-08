package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateVehicleController;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterVehicleMaintenanceController;

public class RegisterVehicleMaintenanceUI {

    private final RegisterVehicleMaintenanceController controller;

    public RegisterVehicleMaintenanceUI() {
        controller = new RegisterVehicleMaintenanceController();
    }

    private RegisterVehicleMaintenanceController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Register Vehicle Maintenance ------------------------");

        requestData();

        submitData();
    }

    private void submitData() {
        //submits data
    }

    private void requestData() {
        //requests data
    }
}
