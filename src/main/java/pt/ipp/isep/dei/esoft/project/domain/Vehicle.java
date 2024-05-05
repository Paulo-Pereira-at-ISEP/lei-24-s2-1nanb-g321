package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Date;

public class Vehicle {

    private String brand;
    private String model;
    private String vehicleType;
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
    private Employee creator;

    public Vehicle(String brand, String model, String vehicleType, int tareWeight, int grossWeight, int currentKm, Date registerDate,
                   Date acquisitionDate, int maintenanceFrequencyInKm, String plateNumber, int numOfPassengers, int numOfDoors,
                   String fuelType, boolean trailerMachinesAble) {
        this.brand = brand;
        this.model = model;
        this.vehicleType = vehicleType;
        this.tareWeight = tareWeight;
        this.grossWeight = grossWeight;
        this.currentKm = currentKm;
        this.registerDate = registerDate;
        this.acquisitionDate = acquisitionDate;
        this.maintenanceFrequencyInKm = maintenanceFrequencyInKm;
        this.plateNumber = plateNumber;
        this.numOfPassengers = numOfPassengers;
        this.numOfDoors = numOfDoors;
        this.fuelType = fuelType;
        this.trailerMachinesAble = trailerMachinesAble;
        this.creator=null;
    }

    public Vehicle(String brand, String model, String vehicleType, int tareWeight, int grossWeight, int currentKm, Date registerDate,
                   Date acquisitionDate, int maintenanceFrequencyInKm, String plateNumber, int numOfPassengers, int numOfDoors,
                   String fuelType, boolean trailerMachinesAble, Employee creator) {
        this.brand = brand;
        this.model = model;
        this.vehicleType = vehicleType;
        this.tareWeight = tareWeight;
        this.grossWeight = grossWeight;
        this.currentKm = currentKm;
        this.registerDate = registerDate;
        this.acquisitionDate = acquisitionDate;
        this.maintenanceFrequencyInKm = maintenanceFrequencyInKm;
        this.plateNumber = plateNumber;
        this.numOfPassengers = numOfPassengers;
        this.numOfDoors = numOfDoors;
        this.fuelType = fuelType;
        this.trailerMachinesAble = trailerMachinesAble;
        this.creator = creator;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return vehicleType;
    }

    public int getTareWeight() {
        return tareWeight;
    }

    public int getGrossWeight() {
        return grossWeight;
    }

    public int getCurrentKm() {
        return currentKm;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

    public int getMaintenanceFrequencyInKm() {
        return maintenanceFrequencyInKm;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public int getNumOfPassengers() {
        return numOfPassengers;
    }

    public int getNumOfDoors() {
        return numOfDoors;
    }

    public String getFuelType() {
        return fuelType;
    }

    public boolean isTrailerMachinesAble() {
        return trailerMachinesAble;
    }

    public Employee getCreator() {
        return creator;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setType(String type) {
        this.vehicleType = type;
    }

    public void setTareWeight(int tareWeight) {
        this.tareWeight = tareWeight;
    }

    public void setGrossWeight(int grossWeight) {
        this.grossWeight = grossWeight;
    }

    public void setCurrentKm(int currentKm) {
        this.currentKm = currentKm;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public void setAcquisitionDate(Date acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public void setMaintenanceFrequencyInKm(int maintenanceFrequencyInKm) {
        this.maintenanceFrequencyInKm = maintenanceFrequencyInKm;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public void setNumOfPassengers(int numOfPassengers) {
        this.numOfPassengers = numOfPassengers;
    }

    public void setNumOfDoors(int numOfDoors) {
        this.numOfDoors = numOfDoors;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public void setTrailerMachinesAble(boolean trailerMachinesAble) {
        this.trailerMachinesAble = trailerMachinesAble;
    }

    public void setCreator(Employee creator) {
        this.creator = creator;
    }

    public Vehicle clone(){
        return new Vehicle(this.brand, this.model, this.vehicleType, this.tareWeight, this.grossWeight, this.currentKm, this.registerDate,
                this.acquisitionDate, this.maintenanceFrequencyInKm, this.plateNumber, this.numOfPassengers, this.numOfDoors,
        this.fuelType, this.trailerMachinesAble, this.creator);
    }
}
