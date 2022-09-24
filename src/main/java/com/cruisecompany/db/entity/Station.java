package com.cruisecompany.db.entity;

public class Station implements Identifiable{

    private long id;
    private String city;
    private String country;
    @Override
    public long getId() {
        return id;
    }

    public Station setId(long id) {
        this.id = id;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Station setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Station setCountry(String country) {
        this.country = country;
        return this;
    }
}
