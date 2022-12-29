package com.cruisecompany.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Staff implements Identifiable{
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String speciality;
    private Ship ship;
}
