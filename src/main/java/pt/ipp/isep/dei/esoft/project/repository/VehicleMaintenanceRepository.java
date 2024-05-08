package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.domain.VehicleMaintenance;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VehicleMaintenanceRepository {

    private final List<VehicleMaintenance> vehicleMaintenances;

    public VehicleMaintenanceRepository() {
        vehicleMaintenances = new ArrayList<>();
    }

    public List<VehicleMaintenance> getVehicleMaintenances(){
        return List.copyOf(vehicleMaintenances);
    }

    public Optional<VehicleMaintenance> add(VehicleMaintenance vehicleMaintenance) {

        Optional<VehicleMaintenance> newVehicleMaintenance = Optional.empty();
        boolean operationSuccess = false;

        if (validateVehicleMaintenance(vehicleMaintenance)) {
            newVehicleMaintenance = Optional.of(vehicleMaintenance.clone());
            operationSuccess = vehicleMaintenances.add(newVehicleMaintenance.get());
        }

        if (!operationSuccess) {
            newVehicleMaintenance = Optional.empty();
        }

        return newVehicleMaintenance;
    }

    public List<VehicleMaintenance> listVehiclesNeedingMaintenance() {
        //list all the vehicles nedding maintenance
        return null;
    }


    private boolean validateVehicleMaintenance(VehicleMaintenance vehicleMaintenance) {
        boolean isValid = !vehicleMaintenances.contains(vehicleMaintenance);
        return isValid;
    }
    //falta implementar o método equals na classe VehicleMaintenance para este método funcionar

}
