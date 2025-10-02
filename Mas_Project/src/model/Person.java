package model;

import util.ObjcetPlus;

import java.time.LocalDate;

public abstract class Person extends ObjcetPlus {
    private String name;


    private String middleName; //atr. opcjonalny

    private String lastName;
    private int year;

    private Address address; //atr. zlożony

    public Person(String name, String lastName, int age, Address address)  {
        try{
            setName(name);
            setLastName(lastName);
            setYear(age);
            this.address = address;
            calculateAge();
        } catch (Exception e){
            e.printStackTrace();
            removeExtent();
        }
    }

    public Person(String name, String lastName, int age, Address address, String middleName)  {
        try{
            setName(name);
            setLastName(lastName);
            setYear(age);
            this.address = address;
            setMidleName(middleName);
            calculateAge();
        } catch (Exception e){
            e.printStackTrace();
            removeExtent();
        }
    }


    public int calculateAge() { //atr. pochodny
        return LocalDate.now().getYear() - year;
    }//atr. pochodny

    public String getInfo(){
        return "model.Person{" +
                "name='" + name +
                ", lastName='" + lastName +
                ", age=" + calculateAge() +
                '}';
    }

    public void setMidleName(String middleName) {
        if(middleName == null || middleName.isBlank()) { //isBlank nie dziala
            this.middleName = null;
        } else {
            this.middleName = middleName;
        }
    }

    public void setName(String name) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public void setLastName(String lastName) {
        if(lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name cannot be empty");
        }
        this.lastName = lastName;
    }

    public void setYear(int year) {
        if (year >= 2009) {
            throw new IllegalArgumentException("Age cannot be under 16");
        }
        this.year = year;
    }

    public String getName(){
        return name;
    }

    public String getLastName(){
        return lastName;
    }

    public int getYear() {
        return year;
    }

    public int getAge() {
        return calculateAge();
    }

    public String getMiddleName() {
        return middleName;
    }

    public Address getAddress(){
        return address;
    }

    @Override
    public String toString() {
        return "model.Person{" +
                "name='" + name +
                ", age=" + calculateAge() +
                ", lastName='" + lastName +
                ", address=" + address +
                '}';
    }
}
