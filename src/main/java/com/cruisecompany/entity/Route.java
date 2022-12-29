package com.cruisecompany.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Route {
    private Cruise cruise;
    private Station station;
    private Integer orderNumber;
}
