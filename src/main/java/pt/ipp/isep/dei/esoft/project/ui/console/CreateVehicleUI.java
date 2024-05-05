package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateTaskController;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateVehicleController;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.TaskCategory;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CreateVehicleUI {


    private final CreateVehicleController controller;

    private String brand;
    private String model;
    private String type;
    private int tareWeight;
    private int grossWeight;
    private int currentKm;
    private Date registerDate;
    private Date acquisitionDate;
    private int maintenanceFrequencyInKm;
    private String plateNumber;
    private int numOfPassengers;
    private int numOfDoors;
    private String fuelType;
    private boolean trailerMachinesAble;


    public CreateVehicleUI() {
        controller = new CreateVehicleController();
    }

    private CreateVehicleController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Create Vehicle ------------------------");

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