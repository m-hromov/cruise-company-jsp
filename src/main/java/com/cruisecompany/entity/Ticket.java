package com.cruisecompany.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ticket implements Identifiable{
    private Long id;
    private Passenger passenger;
    private Cruise cruise;
    private boolean paid;
    private boolean banned;
    private boolean confirmed;
}
