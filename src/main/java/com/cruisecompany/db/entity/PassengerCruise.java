package com.cruisecompany.db.entity;

public class PassengerCruise implements Identifiable{

    private long id;
    private Passenger passenger;
    private Cruise cruise;
    private boolean paid;
    @Override
    public long getId() {
        return id;
    }

    public PassengerCruise setId(long id) {
        this.id = id;
        return this;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public PassengerCruise setPassenger(Passenger passenger) {
        this.passenger = passenger;
        return this;
    }

    public Cruise getCruise() {
        return cruise;
    }

    public PassengerCruise setCruise(Cruise cruise) {
        this.cruise = cruise;
        return this;
    }

    public boolean isPaid() {
        return paid;
    }

    public PassengerCruise setPaid(boolean paid) {
        this.paid = paid;
        return this;
    }
}
