package com.cruisecompany.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
public class Cruise implements Identifiable {
    private Long id;
    private LocalTime timeDeparture;
    private LocalDate dateDeparture;
    private LocalDate dateArrival;
    private Integer daysTotal;
    private BigDecimal price;
    private String description;
    private Integer ticketsPurchased;
    private Ship ship;
    private List<Station> stationList;
}
