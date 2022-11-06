package com.cruisecompany.dto;

import com.cruisecompany.entity.Station;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class CruiseShowDTO {
    private long id;
    private LocalTime timeDeparture;
    private LocalDate dateDeparture;
    private LocalDate dateArrival;
    private int daysTotal;
    private int ticketsPurchased;
    private int shipCapacity;
    private BigDecimal price;
    private String description;
    private String shipName;
    private String photoPath;
    private Station start;
    private Station end;

    public long getId() {
        return id;
    }

    public CruiseShowDTO setId(long id) {
        this.id = id;
        return this;
    }

    public LocalTime getTimeDeparture() {
        return timeDeparture;
    }

    public CruiseShowDTO setTimeDeparture(LocalTime timeDeparture) {
        this.timeDeparture = timeDeparture;
        return this;
    }

    public LocalDate getDateDeparture() {
        return dateDeparture;
    }

    public CruiseShowDTO setDateDeparture(LocalDate dateDeparture) {
        this.dateDeparture = dateDeparture;
        return this;
    }

    public LocalDate getDateArrival() {
        return dateArrival;
    }

    public CruiseShowDTO setDateArrival(LocalDate dateArrival) {
        this.dateArrival = dateArrival;
        return this;
    }

    public int getDaysTotal() {
        return daysTotal;
    }

    public CruiseShowDTO setDaysTotal(int daysTotal) {
        this.daysTotal = daysTotal;
        return this;
    }

    public int getTicketsPurchased() {
        return ticketsPurchased;
    }

    public CruiseShowDTO setTicketsPurchased(int ticketsPurchased) {
        this.ticketsPurchased = ticketsPurchased;
        return this;
    }

    public int getShipCapacity() {
        return shipCapacity;
    }

    public CruiseShowDTO setShipCapacity(int shipCapacity) {
        this.shipCapacity = shipCapacity;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CruiseShowDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CruiseShowDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getShipName() {
        return shipName;
    }

    public CruiseShowDTO setShipName(String shipName) {
        this.shipName = shipName;
        return this;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public CruiseShowDTO setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
        return this;
    }

    public Station getStart() {
        return start;
    }

    public CruiseShowDTO setStart(Station start) {
        this.start = start;
        return this;
    }

    public Station getEnd() {
        return end;
    }

    public CruiseShowDTO setEnd(Station end) {
        this.end = end;
        return this;
    }

}
