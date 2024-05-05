package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.TaskCategory;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VehicleRepository {

    private final List<Vehicle> vehicles;
    public VehicleRepository() {
        vehicles = new ArrayList<>();
    }


    public Optional<Vehicle> add(Vehicle vehicle) {

        Optional<Vehicle> newVehicle = Optional.empty();
        boolean operationSuccess = false;

        if (validateVehicle(vehicle)) {
            newVehicle = Optional.of(vehicle.clone());
            operationSuccess = vehicles.add(newVehicle.get());
        }

        if (!operationSuccess) {
            newVehicle = Optional.empty();
        }

        return newVehicle;
    }


    private boolean validateVehicle(Vehicle vehicle) {
        boolean isValid = !vehicles.contains(vehicle);
        return isValid;
    }

    public List<Vehicle> getVehicles() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(vehicles);
    }

}
