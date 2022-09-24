package com.cruisecompany.db.entity;

public class Order implements Identifiable{

    private long id;
    private Passenger passenger;
    private Cruise cruise;
    private boolean paid;
    private boolean banned;
    @Override
    public long getId() {
        return id;
    }

    public Order setId(long id) {
        this.id = id;
        return this;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Order setPassenger(Passenger passenger) {
        this.passenger = passenger;
        return this;
    }

    public Cruise getCruise() {
        return cruise;
    }

    public Order setCruise(Cruise cruise) {
        this.cruise = cruise;
        return this;
    }

    public boolean isPaid() {
        return paid;
    }

    public Order setPaid(boolean paid) {
        this.paid = paid;
        return this;
    }

    public boolean isBanned() {
        return banned;
    }

    public Order setBanned(boolean banned) {
        this.banned = banned;
        return this;
    }
}
