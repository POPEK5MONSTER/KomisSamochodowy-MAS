package model;

public class Sale extends Display {
    private final boolean postAccident;

    public Sale(boolean postAccident) {
        this.postAccident = postAccident;
    }

    public Sale(Display display, boolean postAccident) {
        this.postAccident = postAccident;
        setVehicle(display.getVehicle());
    }


    public boolean isPostAccident() {
        return postAccident;
    }

    public Rent changeToRent(double pricePerDay, double deposit) {
        this.removeExtent();
        return new Rent(this, pricePerDay, deposit);
    }


    @Override
    public String getDescription() {
        return "model.Sale" + (postAccident ? " with post-accident" : " without post-accident");
    }

}
