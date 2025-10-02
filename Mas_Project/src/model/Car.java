package model;

import util.ObjcetPlus;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public class Car extends Vehicle {

    private int doors;

    private final EnumSet<Type> carTypes;

    private int maxSpeed;
    private boolean fourByFour;
    private String luxuryInterior;


    public Car(String vin, String registrationNumber, String brand, String model, int yearOfProduction, int mileage, Display transaction, int doors, EnumSet<Type> carTypes) {
        super(vin, registrationNumber, brand, model, yearOfProduction, mileage, transaction);
        setDoors(doors);
        this.carTypes = EnumSet.copyOf(carTypes);
        calculatePrice();
    }

    @Override
    public double calculatePrice() {
        double price = 40000;
        price -= (LocalDate.now().getYear() - getYearOfProduction()) * 2000;
        price -= (double) getMileage() / 10000 * 500;
        if (doors >= 5){
            price += 1000;
        }
        return Math.max(price, 5000);
    }


    @Override
    public String getDescription() {
        return "Brand: " + getBrand() + " Model:" + getModel() + " Year: " + getYearOfProduction() + " doors: " + getDoors();
    }


    public static List<Car> getSortedExtentByProductionYear(){
        return ObjcetPlus.getExtentFromClass(Car.class).stream().sorted(Comparator.comparing(Vehicle::getYearOfProduction).reversed()).toList();
    }

    public static List<Car> findCar(String brand){  //met. klasowa
        return  ObjcetPlus.getExtentFromClass(Car.class).stream()
                .filter(car -> car.getBrand().equals(brand))
                .collect(Collectors.toList());
    }

    public static List<Car> findCar(String brand, String model){  //przeciążenie
        return ObjcetPlus.getExtentFromClass(Car.class).stream()
                .filter(car -> car.getBrand().equals(brand) && car.getModel().equals(model))
                .collect(Collectors.toList());
    }

    private void setDoors(int doors) {
        if (doors < 0) {
            throw new IllegalArgumentException("Doors cant be negative");
        }
        this.doors = doors;
    }

    public void setMaxSpeed(int maxSpeed) {
        if (!carTypes.contains(Type.SPORT)) {
            throw new IllegalArgumentException("model.Car is not SPORT type");
        }
        if (maxSpeed < 0) {
            throw new IllegalArgumentException("Max speed cant be negative");
        }
        this.maxSpeed = maxSpeed;
    }

    public void setFourByFour(boolean fourByFour) {
        if (!carTypes.contains(Type.OFFROAD)) {
            throw new IllegalArgumentException("model.Car is not SUV type");
        }
        this.fourByFour = fourByFour;
    }

    public void setLuxuryInterior(String luxuryInterior) {
        if (!carTypes.contains(Type.LUXURY)) {
            throw new IllegalArgumentException("model.Car is not LUXURY type");
        }
        this.luxuryInterior = luxuryInterior;
    }

    public int getMaxSpeed() {
        if (!carTypes.contains(Type.SPORT)) {
            throw new IllegalArgumentException("model.Car is not SPORT type");
        }
        return maxSpeed;
    }

    public boolean isFourByFour() {
        if (!carTypes.contains(Type.OFFROAD)) {
            throw new IllegalArgumentException("model.Car is not SUV type");
        }
        return fourByFour;
    }

    public String getLuxuryInterior() {
        if (!carTypes.contains(Type.LUXURY)) {
            throw new IllegalArgumentException("model.Car is not LUXURY type");
        }
        return luxuryInterior;
    }

    public int getDoors() {
        return doors;
    }

    @Override
    public String toString() {
        return "Car{" +
                super.toString() +
                "doors=" + doors +
                "price=" + calculatePrice() +
                '}';
    }
}
