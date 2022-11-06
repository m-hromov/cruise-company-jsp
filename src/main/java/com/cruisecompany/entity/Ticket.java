package com.cruisecompany.entity;

public class Ticket implements Identifiable{

    private long id;
    private Passenger passenger;
    private Cruise cruise;
    private boolean paid;
    private boolean banned;
    private boolean confirmed;
    @Override
    public long getId() {
        return id;
    }

    public Ticket setId(long id) {
        this.id = id;
        return this;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Ticket setPassenger(Passenger passenger) {
        this.passenger = passenger;
        return this;
    }

    public Cruise getCruise() {
        return cruise;
    }

    public Ticket setCruise(Cruise cruise) {
        this.cruise = cruise;
        return this;
    }

    public boolean isPaid() {
        return paid;
    }

    public Ticket setPaid(boolean paid) {
        this.paid = paid;
        return this;
    }

    public boolean isBanned() {
        return banned;
    }

    public Ticket setBanned(boolean banned) {
        this.banned = banned;
        return this;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public Ticket setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
        return this;
    }
}
