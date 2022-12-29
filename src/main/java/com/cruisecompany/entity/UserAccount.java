package com.cruisecompany.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAccount implements Identifiable{
    private Long id;
    private String email;
    private String password;
    private String passwordSalt;
    private String role;
}
