package model;

import util.ObjcetPlus;

public abstract class Display extends ObjcetPlus {
    private Vehicle vehicle;

    protected void setVehicle(Vehicle vehicle) {
        if (this.vehicle == null){
            this.vehicle = vehicle;
        }
    }

    protected Vehicle getVehicle() {
        return vehicle;
    }

    public abstract String getDescription();

}
