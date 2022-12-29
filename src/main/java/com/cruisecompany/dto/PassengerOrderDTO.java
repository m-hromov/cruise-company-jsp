package com.cruisecompany.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PassengerOrderDTO   {
    private long orderId;
    private long passengerId;
    private long cruiseId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private boolean paid;
    private boolean banned;
    private boolean confirmed;
    private String documentPath;
}
