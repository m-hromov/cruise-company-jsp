package com.cruisecompany.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ship implements Identifiable {
    private Long id;
    private Integer passengerCapacity;
    private String name;
    private String photoPath;
}
