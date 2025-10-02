package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Client extends Person {

    private String email;

    private String number;

    private final List<Reservation> reservations = new ArrayList<>();


    public Client(String name, String lastName, int age, Address address, String email, String number) {
        super(name, lastName, age, address);
        try{
            setEmail(email);
            setNumber(number);
        } catch (Exception e){
            e.printStackTrace();
            removeExtent();
        }
    }

    public Client(String name, String lastName, int age, Address address, String middleName, String email, String number) {
        super(name, lastName, age, address, middleName);
        try{
            setEmail(email);
            setNumber(number);
        } catch (Exception e){
            e.printStackTrace();
            removeExtent();
        }
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

    public void removeVehicle(Vehicle vehicle){
        Reservation historyToRemove = null;
        for (Reservation history : reservations) {
            if (history.getVehicle() == vehicle) {
                historyToRemove = history;
                break;
            }
        }

        if (historyToRemove != null) {
            historyToRemove.removeExtent();
        }
    }

    @Override
    public String getInfo(){
        return "model.Person{" +
                "name='" + getName() +
                ", lastName='" + getLastName() +
                ", age=" + getName() +
                ", email =" + getEmail() +
                ", number =" + getNumber() +
                '}';
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("email cannot be empty");
        }
        this.email = email;
    }

    public void setNumber(String number) {
        if (!number.matches("\\d{9}")) {
            throw new IllegalArgumentException("Number must be exactly 9 digits");
        }
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public String getNumber() {
        return number;
    }
    public List<Reservation> getReservation(){
        return Collections.unmodifiableList(reservations);
    }

    @Override
    public String toString() {
        return "model.Employee{" +
                "name=" + getName() +
                ", lastName=" + getLastName() +
                ", age=" + getAge() +
                "email=" + getEmail() +
                ", number='" + getNumber() +
                '}' + '\n';
    }
    @Override
    public void removeExtent() {
        while (!reservations.isEmpty()){
            reservations.get(0).removeExtent();
        }

        super.removeExtent();
    }

}
