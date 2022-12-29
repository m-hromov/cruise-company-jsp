package com.cruisecompany.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Passenger implements Identifiable{
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private BigDecimal money;
    private String documentPath;
    private UserAccount userAccount;
}
