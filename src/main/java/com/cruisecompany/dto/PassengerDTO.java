package com.cruisecompany.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PassengerDTO {
    private long passengerId;
    private long userAccountId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private BigDecimal money;
    private String documentPath;
    private String role;
}
