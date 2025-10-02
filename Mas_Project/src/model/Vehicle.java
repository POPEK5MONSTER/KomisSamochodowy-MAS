package model;

import util.ObjcetPlus;

import java.time.LocalDateTime;
import java.util.*;

public abstract class Vehicle extends ObjcetPlus {
    private String vin;
    private String registrationNumber;
    private String brand;
    private String model;
    private int yearOfProduction;
    private int mileage;
    private int previousMileage = 0;

    private Display transaction;

    private final List<Employee> assignedEmployees = new ArrayList<>();

    private final List<Employee> testDriveEmployees = new ArrayList<>();

    private final List<Reservation> reservations = new ArrayList<>();

    private final List<Opinion> opinions = new ArrayList<>();


    public Vehicle(String vin, String registrationNumber, String brand, String model, int yearOfProduction, int mileage, Display transaction) {
        try {
            setVin(vin);
            setMileage(mileage);
            setRegistrationNumber(registrationNumber);
            setBrand(brand);
            setModel(model);
            setYearOfProduction(yearOfProduction);
            setTransaction(transaction);
            transaction.setVehicle(this);

            validateBusinessRules();
        } catch (Exception e){
        e.printStackTrace();
        removeExtent();
        }
    }

    public Reservation addReservation(Client client, LocalDateTime from, LocalDateTime to) {
        return new Reservation(this, client, from, to);
    }


    protected void addReservation(Reservation reservation){
        if (reservation == null){
            throw new NullPointerException();
        }
        reservations.add(reservation);
    }

    protected void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    public void removeClient(Client client){
        Reservation historyToRemove = null;
        for (Reservation history : reservations) {
            if (history.getClient() == client) {
                historyToRemove = history;
                break;
            }
        }

        if (historyToRemove != null) {
            historyToRemove.removeExtent();
        }
    }

    public void addOpinion(Opinion opinion) {
        if (opinion == null){
            throw new NullPointerException("Service cant be null");
        }
        if (!opinions.contains(opinion)) {
            opinions.add(opinion);
        }
    }
    public void removeOpinion(Opinion opinion) {
        if (opinions.contains(opinion)) {
            opinions.remove(opinion);
            opinion.removeExtent();
        }
    }

    public abstract double calculatePrice();

    //własne
    private void validateBusinessRules() {
        if (this.mileage > 200000) {
            throw new IllegalArgumentException("Cars older than 20 years cant have more than 200,000 km");
        }
    }

    //SUBSET

    public void addAssignedEmployee(Employee emp) {
        assignedEmployees.add(emp);
    }
    public void removeAssignedEmployee(Employee emp) {
        assignedEmployees.remove(emp);
    }

    public void addTestDriveEmployee(Employee emp) {
        testDriveEmployees.add(emp);
    }

    public void removeTestDriveEmployee(Employee emp) {
        testDriveEmployees.remove(emp);
    }

    public List<Employee> getAssignedEmployees() {
        return Collections.unmodifiableList(assignedEmployees);
    }
    public List<Reservation> getReservations(){
        return Collections.unmodifiableList(reservations);
    }
    public List<Employee> getTestDriveEmployees(){
        return Collections.unmodifiableList(testDriveEmployees);
    }

    public List<Opinion> getHistory(){
        return Collections.unmodifiableList(opinions);
    }



    @Override
    public void removeExtent() {
        while (!testDriveEmployees.isEmpty()) {
            Employee emp = testDriveEmployees.getFirst();
            emp.unassignCarFromTestDrive(this);
        }

        while (!assignedEmployees.isEmpty()) {
            Employee emp = assignedEmployees.getFirst();
            emp.unassignCar(this);
        }

        while (!reservations.isEmpty()){
            reservations.get(0).removeExtent();
        }
        while (!opinions.isEmpty()) {
            removeOpinion(opinions.get(0));
        }

        super.removeExtent();
    }


    //zAtrybutem
    //UNIQE
    public void setVin(String vin) {
        if (vin == null || !vin.matches("[A-Z0-9]{17}")) {
            throw new IllegalArgumentException("Invalid vin");
        }
        if(ObjcetPlus.getExtentFromClass(this.getClass()).stream().anyMatch(carOgr -> carOgr != this && carOgr.getRegistrationNumber().equals(registrationNumber))) {
            throw new IllegalArgumentException("Registration number already exists");
        }
        this.vin = vin;
    }

    public void setRegistrationNumber(String registrationNumber) {
        if (registrationNumber == null || registrationNumber.isBlank()) {
            throw new IllegalArgumentException("Registration cannot be empty");
        }
        this.registrationNumber = registrationNumber;
    }

    public void setMileage(int mileage) {
        if (mileage < this.previousMileage){
            throw new IllegalArgumentException("Invalid mileage");
        }
        if (mileage < 0){
            throw new IllegalArgumentException("Mileage cannot under 0");
        }
        this.previousMileage = this.mileage;
        this.mileage = mileage;
    }

    public void setBrand(String brand) {
        if (brand == null || brand.isBlank()) {
            throw new IllegalArgumentException("Brand cannot be empty");
        }
        this.brand = brand;
    }

    public void setModel(String model) {
        if (model == null || model.isBlank()) {
            throw new IllegalArgumentException("Model cannot be empty");
        }
        this.model = model;
    }

    public void setYearOfProduction(int yearOfProduction) {
        if (yearOfProduction < 1769){
            throw new IllegalArgumentException("Year of production cannot under 1769");
        }
        this.yearOfProduction = yearOfProduction;
    }

    public Display getTransaction() {
        return transaction;
    }

    public void setTransaction(Display transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction cant be null");
        }
        this.transaction = transaction;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getVin() {
        return vin;
    }

    public int getMileage() {
        return mileage;
    }
    
    
    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }


    public abstract String getDescription();

    @Override
    public String toString() {
        return "Vehicle{" +
                "vin='" + vin + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", yearOfProduction=" + yearOfProduction +
                ", mileage=" + mileage +
                ", transaction=" + transaction +
                '}';
    }
}
