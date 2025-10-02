package model;

import util.ObjcetPlus;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class Motorbike extends Vehicle  {

    private int cases;


    public Motorbike(String vin, String registrationNumber, String brand, String model, int yearOfProduction, int mileage, Display transaction, int cases) {
        super(vin, registrationNumber, brand, model, yearOfProduction, mileage, transaction);
        setCases(cases);
        calculatePrice();
    }

    @Override
    public double calculatePrice() {
        double price = 20000;
        price -= (LocalDate.now().getYear() - getYearOfProduction()) * 1500;
        price -= (double) getMileage() / 10000 * 300;
        if (cases > 0){
            price += 500 * cases;
        }
        return Math.max(price, 3000);
    }

    @Override
    public String getDescription() {
        return "Brand: " + getBrand() + " Model:" + getModel() + " Year: " + getYearOfProduction() + " cases: " + getCases();

    }
    public static List<Motorbike> getSortedExtentByProductionYear(){
        return ObjcetPlus.getExtentFromClass(Motorbike.class).stream().sorted(Comparator.comparing(Vehicle::getYearOfProduction).reversed()).toList();
    }

    public void setCases(int cases) {
        if (cases <= 0) {
            throw new IllegalArgumentException("Cases cant be negative");
        }
        this.cases = cases;
    }

    public int getCases() {
        return cases;
    }
}
