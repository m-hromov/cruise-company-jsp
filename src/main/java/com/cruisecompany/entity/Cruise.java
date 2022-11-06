package com.cruisecompany.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Cruise implements Identifiable {

    private long id;
    private LocalTime timeDeparture;
    private LocalDate dateDeparture;
    private LocalDate dateArrival;
    private int daysTotal;
    private BigDecimal price;
    private String description;
    private int ticketsPurchased;
    private Ship ship;
    private List<Station> stationList;

    @Override
    public long getId() {
        return id;
    }

    public Cruise setId(long id) {
        this.id = id;
        return this;
    }

    public LocalTime getTimeDeparture() {
        return timeDeparture;
    }

    public Cruise setTimeDeparture(LocalTime timeDeparture) {
        this.timeDeparture = timeDeparture;
        return this;
    }

    public LocalDate getDateDeparture() {
        return dateDeparture;
    }

    public Cruise setDateDeparture(LocalDate dateDeparture) {
        this.dateDeparture = dateDeparture;
        return this;
    }

    public LocalDate getDateArrival() {
        return dateArrival;
    }

    public Cruise setDateArrival(LocalDate dateArrival) {
        this.dateArrival = dateArrival;
        return this;
    }

    public int getDaysTotal() {
        return daysTotal;
    }

    public Cruise setDaysTotal(int daysTotal) {
        this.daysTotal = daysTotal;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Cruise setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Ship getShip() {
        return ship;
    }

    public Cruise setShip(Ship ship) {
        this.ship = ship;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Cruise setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getTicketsPurchased() {
        return ticketsPurchased;
    }

    public Cruise setTicketsPurchased(int ticketsPurchased) {
        this.ticketsPurchased = ticketsPurchased;
        return this;
    }

    public List<Station> getStationList() {
        return stationList;
    }

    public Cruise setStationList(List<Station> stationList) {
        this.stationList = stationList;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Cruise))
            return false;
        Cruise cruise = (Cruise) obj;
        return cruise.getId() == id;
    }
}
