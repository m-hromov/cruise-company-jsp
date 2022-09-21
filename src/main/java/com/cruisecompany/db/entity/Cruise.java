package com.cruisecompany.db.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Cruise implements Identifiable{

    private long id;
    private Time timeDeparture;
    private Date dateDeparture;
    private Date dateArrival;
    private int daysTotal;
    private BigDecimal price;
    private String description;
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

    public Time getTimeDeparture() {
        return timeDeparture;
    }

    public Cruise setTimeDeparture(Time timeDeparture) {
        this.timeDeparture = timeDeparture;
        return this;
    }

    public Date getDateDeparture() {
        return dateDeparture;
    }

    public Cruise setDateDeparture(Date dateDeparture) {
        this.dateDeparture = dateDeparture;
        return this;
    }

    public Date getDateArrival() {
        return dateArrival;
    }

    public Cruise setDateArrival(Date dateArrival) {
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
        Cruise cruise = (Cruise)obj;
        return cruise.getId() == id;
    }
}
