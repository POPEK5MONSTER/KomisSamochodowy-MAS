package model;

import util.ObjcetPlus;

public class Opinion extends ObjcetPlus {
    private String rating;
    private String description;

    private Vehicle vehicle;

    public Opinion(String rating, String description, Vehicle vehicle) {
        setRating(rating);
        setDescription(description);
        setVehicle(vehicle);
    }

    public void setRating(String rating) {
        if (rating == null || rating.isBlank()){
            throw new IllegalArgumentException("Description cannot be empty");
        }
        if (rating.matches("[1-5]")){
            throw new IllegalArgumentException("Rating must be between 1 - 5 ");
        }
        this.rating = rating;
    }

    public void setDescription(String description) {
        if (description == null || description.isBlank()){
            throw new IllegalArgumentException("Description cannot be empty");
        }
        this.description = description;
    }

    protected void setVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            throw new NullPointerException("model.Opinion is null");
        }
        this.vehicle = vehicle;
        vehicle.addOpinion(this);
    }

    public String getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public void removeExtent() {
        vehicle.removeOpinion(this);
        vehicle = null;
        super.removeExtent();
    }
}
