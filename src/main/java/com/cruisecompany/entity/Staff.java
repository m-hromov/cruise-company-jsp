package com.cruisecompany.entity;

public class Staff implements Identifiable{

    private long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String speciality;
    private Ship ship;
    @Override
    public long getId() {
        return id;
    }

    public Staff setId(long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Staff setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Staff setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Staff setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getSpeciality() {
        return speciality;
    }

    public Staff setSpeciality(String speciality) {
        this.speciality = speciality;
        return this;
    }

    public Ship getShip() {
        return ship;
    }

    public Staff setShip(Ship ship) {
        this.ship = ship;
        return this;
    }
}
