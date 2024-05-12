package pt.ipp.isep.dei.esoft.project.ui.console.vehicle;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateVehicleController;

import java.util.Date;

public class CreateVehicleUI implements Runnable{


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