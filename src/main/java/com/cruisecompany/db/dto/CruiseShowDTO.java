package com.cruisecompany.db.dto;

import com.cruisecompany.db.entity.Station;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class CruiseShowDTO {
    private long id;
    private Time timeDeparture;
    private Date dateDeparture;
    private Date dateArrival;
    private int daysTotal;
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

    public Time getTimeDeparture() {
        return timeDeparture;
    }

    public CruiseShowDTO setTimeDeparture(Time timeDeparture) {
        this.timeDeparture = timeDeparture;
        return this;
    }

    public Date getDateDeparture() {
        return dateDeparture;
    }

    public CruiseShowDTO setDateDeparture(Date dateDeparture) {
        this.dateDeparture = dateDeparture;
        return this;
    }

    public Date getDateArrival() {
        return dateArrival;
    }

    public CruiseShowDTO setDateArrival(Date dateArrival) {
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
