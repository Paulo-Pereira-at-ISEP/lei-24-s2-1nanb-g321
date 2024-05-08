package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.domain.VehicleMaintenance;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleMaintenanceRepository;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.List;

public class VehiclesNeedingMaintenanceController {

    private VehicleMaintenanceRepository vehicleMaintenanceRepository;
    public VehiclesNeedingMaintenanceController() {

        getVehicleMaintenanceRepository();
    }

    public VehiclesNeedingMaintenanceController(VehicleMaintenanceRepository vehicleMaintenanceRepository) {

        this.vehicleMaintenanceRepository = vehicleMaintenanceRepository;
    }

    private VehicleMaintenanceRepository getVehicleMaintenanceRepository() {
        if (vehicleMaintenanceRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the VehicleMaintenanceRepository
            //vehicleMaintenanceRepository = repositories.getVehicleMaintenanceRepository();
        }
        return null;
    }

    public List<VehicleMaintenance> getAllVehicleMaintenances(){
        return vehicleMaintenanceRepository.getVehicleMaintenances();
    }

    public List<VehicleMaintenance> listVehiclesNeedingMaintenance(List<VehicleMaintenance> vehicleMaintenances) {
        return  vehicleMaintenanceRepository.listVehiclesNeedingMaintenance();
    }
}
