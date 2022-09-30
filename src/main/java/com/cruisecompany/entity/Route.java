package com.cruisecompany.entity;

public class Route implements Identifiable{

    private long id;
    private Cruise cruise;
    private Station station;
    private int orderNumber;
    @Override
    public long getId() {
        return id;
    }

    public Route setId(long id) {
        this.id = id;
        return this;
    }

    public Cruise getCruise() {
        return cruise;
    }

    public Route setCruise(Cruise cruise) {
        this.cruise = cruise;
        return this;
    }

    public Station getStation() {
        return station;
    }

    public Route setStation(Station station) {
        this.station = station;
        return this;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public Route setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }
}
