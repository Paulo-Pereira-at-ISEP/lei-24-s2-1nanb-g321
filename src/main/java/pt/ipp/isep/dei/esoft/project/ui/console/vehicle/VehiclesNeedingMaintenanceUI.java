package pt.ipp.isep.dei.esoft.project.ui.console.vehicle;

import pt.ipp.isep.dei.esoft.project.application.controller.VehiclesNeedingMaintenanceController;
import pt.ipp.isep.dei.esoft.project.domain.VehicleMaintenance;

import java.util.List;

public class VehiclesNeedingMaintenanceUI {
    private final VehiclesNeedingMaintenanceController controller;
    private List<VehicleMaintenance> vehicleMaintenances;

    public VehiclesNeedingMaintenanceUI() {
        controller = new VehiclesNeedingMaintenanceController();
    }

    private VehiclesNeedingMaintenanceController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Vehicles needing Maintenance ------------------------");

        listVehiclesNeedingMaintenance(vehicleMaintenances);
    }

    private void listVehiclesNeedingMaintenance(List<VehicleMaintenance> vehicleMaintenances) {
        //print -> controller.listVehiclesNeedingMaintenance(vehicleMaintenances);
    }

}
