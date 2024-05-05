package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class CreateVehicleController {
    private AuthenticationRepository authenticationRepository;
    private VehicleRepository vehicleRepository;


    public CreateVehicleController() {

        getVehicleRepository();
        getAuthenticationRepository();
    }

    //Allows receiving the repositories as parameters for testing purposes
    public CreateVehicleController(VehicleRepository vehicleRepository,
                                AuthenticationRepository authenticationRepository) {

        this.vehicleRepository = vehicleRepository;
        this.authenticationRepository = authenticationRepository;
    }

    private VehicleRepository getVehicleRepository() {
        if (vehicleRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            //vehicleRepository = repositories.getVehicleRepository();
        }
        return vehicleRepository;
    }

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    public Optional<Vehicle> createVehicle(String brand, String model, String type, int tareWeight, int grossWeight, int currentKm, Date registerDate,
                                           Date acquisitionDate, int maintenanceFrequencyInKm, String plateNumber, int numOfPassengers, int numOfDoors,
                                           String fuelType, boolean trailerMachinesAble) {


        Employee employee = getEmployeeFromSession();

        //some validations are yet to be made
        Optional<Vehicle> newVehicle = Optional.empty();

        newVehicle = Optional.of(new Vehicle(brand, model, type, tareWeight, grossWeight, currentKm, registerDate,
                acquisitionDate, maintenanceFrequencyInKm, plateNumber, numOfPassengers, numOfDoors, fuelType, trailerMachinesAble,
                employee));


        return newVehicle;
    }

    private Employee getEmployeeFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new Employee(email.getEmail());
    }

}
