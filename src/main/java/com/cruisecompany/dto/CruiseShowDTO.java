package com.cruisecompany.dto;

import com.cruisecompany.entity.Station;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
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
}
