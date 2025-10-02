package model;

import java.util.*;

public class Employee extends Person {
    private double salary;

    private boolean vacation;
    private final List<String> drivingLicenseCategory = new ArrayList<>(); //atr. powtarzalny

    private final List<Vehicle> assignedCars = new ArrayList<>();
    private final TreeSet<Vehicle> testDriveCars = new TreeSet<>(Comparator.comparing(Vehicle::getYearOfProduction).reversed());


    public Employee(String name, String lastName, int age, Address address, double salary) {
        super(name, lastName, age, address);
        try{
            setSalary(salary);
            this.vacation = false;
        } catch (Exception e){
            e.printStackTrace();
            removeExtent();
        }
    }

    public Employee(String name, String lastName, int age, Address address, String middleName , double salary) {
        super(name, lastName, age, address, middleName);
        try{
            setSalary(salary);
            this.vacation = false;
        } catch (Exception e){
            e.printStackTrace();
            removeExtent();
        }
    }

    //SUBSET
    public void assignCar(Vehicle vehicle){
        if (vehicle != null){
            assignedCars.add(vehicle);
            vehicle.addAssignedEmployee(this);
        }
    }

    public void unassignCar(Vehicle vehicle) {
        if (testDriveCars.contains(vehicle)) {
            throw new IllegalStateException("Cannot unassign car assigned for test drive.");
        }
        assignedCars.remove(vehicle);
        vehicle.removeAssignedEmployee(this);
    }

    public void assignCarForTestDrive(Vehicle vehicle) {
        if (!assignedCars.contains(vehicle)) {
            throw new IllegalArgumentException("model.Car must be assigned before assigning for test drive.");
        }
        if (vehicle != null) {
            testDriveCars.add(vehicle);
            vehicle.addTestDriveEmployee(this);
        }
    }

    public void unassignCarFromTestDrive(Vehicle vehicle) {
        if (testDriveCars.remove(vehicle)) {
            vehicle.removeTestDriveEmployee(this);
        }
    }

    public List<Vehicle> getAssignedCars() {
        return Collections.unmodifiableList(assignedCars);
    }

    @Override
    public void removeExtent() {
        while (!testDriveCars.isEmpty()) {
            unassignCarFromTestDrive(testDriveCars.getFirst());
        }
        while (!assignedCars.isEmpty()) {
            unassignCar(assignedCars.getFirst());
        }

        super.removeExtent();
    }


    @Override
    public String getInfo(){
        return "model.Person{" +
                "name='" + getName() +
                ", lastName='" + getLastName() +
                ", age=" + getName() +
                ", vacation=" + isVacation() +
                '}';
    }

    public void setSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
        this.salary = salary;
    }

    public void setVacation(boolean vacation) {
        this.vacation = vacation;
    }
    public void addDrivingLicenseCategory(String category) {
        if (category != null && !category.trim().isBlank() && !drivingLicenseCategory.contains(category)) {
            drivingLicenseCategory.add(category);
        }
    }

    public boolean removeDrivingLicenseCategory(String category) {
        if (drivingLicenseCategory.size() <= 1) {
            throw new IllegalArgumentException("model.Employee must have at least one driving license category.");
        }
        return drivingLicenseCategory.remove(category);
    }

    public List<String> getDrivingLicenseCategory(){
        return Collections.unmodifiableList(drivingLicenseCategory);
    }

    public boolean isVacation() {
        return vacation;
    }

    public double getSalary() {
        return salary;
    }


    @Override
    public String toString() {
        return "model.Employee{" +
                "name=" + getName() +
                ", lastName=" + getLastName() +
                ", age=" + getAge() +
                "salary=" + getSalary() +
                ", position='" + isVacation() +
                getDrivingLicenseCategory() +
                '}' + '\n';
    }
}
