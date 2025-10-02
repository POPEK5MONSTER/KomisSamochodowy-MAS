package model;

public class Rent extends Display {

    private double pricePerDay;

    private double deposit;

    public Rent(double pricePerDay, double deposit) {
        setPricePerDay(pricePerDay);
        setDeposit(deposit);
    }
    public Rent(Display display, double pricePerDay, double deposit) {
        setPricePerDay(pricePerDay);
        setDeposit(deposit);
        setVehicle(display.getVehicle());
    }

    public Sale changeToSale(boolean postAccident) {
        this.removeExtent();
        return  new Sale(this, postAccident);
    }

    public void setPricePerDay(double pricePerDay) {
        if (pricePerDay < 0) {
            throw new IllegalArgumentException("Price per day cant be negative");
        }
        this.pricePerDay = pricePerDay;
    }

    public void setDeposit(double deposit) {
        if (deposit < 0) {
            throw new IllegalArgumentException("Deposit cant be negative");
        }
        this.deposit = deposit;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public double getDeposit() {
        return deposit;
    }

    @Override
    public String getDescription() {
        return "Price per dat: " + pricePerDay + " " +
                "Price of deposit " + deposit;
    }
}
