package model;

import java.io.Serializable;

public class Address implements Serializable {
    private String street;
    private String city;
    private static String country = "Poland"; //złożony

    public Address(String street, String city) {
        try {
            setStreet(street);
            setCity(city);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setCity(String city) {
        if(city == null || city.isEmpty()){
            throw new IllegalArgumentException("City cannot be empty");
        }
        this.city = city;
    }

    public void setStreet(String street) {
        if(street == null || street.isEmpty()){
            throw new IllegalArgumentException("Street cannot be empty");
        }
        this.street = street;
    }

    public void setCountry(String country){
        if(country == null || country.isEmpty()){
            throw new IllegalArgumentException("Country cannot be empty");
        }
        Address.country = country;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCountry(){
        return country;
    }
}