package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.domain.VehicleMaintenance;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleMaintenanceRepository;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.Date;
import java.util.List;

public class RegisterVehicleMaintenanceController {

    private VehicleRepository vehicleRepository;
    private VehicleMaintenanceRepository vehicleMaintenanceRepository;
    public RegisterVehicleMaintenanceController() {

        getVehicleRepository();
        getVehicleMaintenanceRepository();
    }

    public RegisterVehicleMaintenanceController(VehicleRepository vehicleRepository,
                                                VehicleMaintenanceRepository vehicleMaintenanceRepository) {

        this.vehicleRepository = vehicleRepository;
        this.vehicleMaintenanceRepository = vehicleMaintenanceRepository;
    }

    private VehicleRepository getVehicleRepository() {
        if (vehicleRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            //vehicleRepository = repositories.getVehicleRepository();
        }
        return vehicleRepository;
    }
    private VehicleMaintenanceRepository getVehicleMaintenanceRepository() {
        if (vehicleMaintenanceRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the VehicleMaintenanceRepository
            //vehicleMaintenanceRepository = repositories.getVehicleMaintenanceRepository();
        }
        return null;
    }


    public List<Vehicle> getAllVehicles(){
        return vehicleRepository.getVehicles();
    }

    public VehicleMaintenance registerVehicleMaintenance(Vehicle vehicle, Date date, int km){

        //VehicleMaintenance vehicleMaintenance=new VehicleMaintenance();
        //vehicleRepository.add(vehicleMaintenance);
        return null;
    }

}
