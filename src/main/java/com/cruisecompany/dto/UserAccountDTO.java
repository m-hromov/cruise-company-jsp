package com.cruisecompany.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAccountDTO {
    private long id;
    private String login;
    private String role;
}
