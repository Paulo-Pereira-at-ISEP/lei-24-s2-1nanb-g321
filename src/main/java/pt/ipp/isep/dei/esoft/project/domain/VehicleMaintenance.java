package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Date;

public class VehicleMaintenance {

    private Date date;
    private int  kmsOfVehicle;
    Vehicle vehicle;

    public VehicleMaintenance(Date date, int kmsOfVehicle, Vehicle vehicle) {
        this.date = date;
        this.kmsOfVehicle = kmsOfVehicle;
        this.vehicle = vehicle;
    }

    public Date getDate() {
        return date;
    }

    public int getKmsOfVehicle() {
        return kmsOfVehicle;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }


    public void setDate(Date date) {
        this.date = date;
    }

    public void setKmsOfVehicle(int kmsOfVehicle) {
        this.kmsOfVehicle = kmsOfVehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean needsMaintenance (){
        //fazer a operação necessária para verificar se necessita de manutenção
        return false;
    }

    public VehicleMaintenance clone(){
        return new VehicleMaintenance(this.date, this.kmsOfVehicle, this.vehicle);
    }
}
