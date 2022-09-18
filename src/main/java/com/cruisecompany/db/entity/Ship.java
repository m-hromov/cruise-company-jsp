package com.cruisecompany.db.entity;

public class Ship implements Identifiable{

    private long id;
    private int passengerCapacity;
    private String name;
    private String photoPath;
    @Override
    public long getId() {
        return id;
    }

    public Ship setId(long id) {
        this.id = id;
        return this;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public Ship setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
        return this;
    }

    public String getName() {
        return name;
    }

    public Ship setName(String name) {
        this.name = name;
        return this;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public Ship setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
        return this;
    }
}
