package com.cruisecompany.service;

import com.cruisecompany.db.dto.UserAccountDTO;
import com.cruisecompany.entity.Passenger;

import java.util.Optional;

public interface UserAccountService {
    Optional<UserAccountDTO> signIn(String login, String password);
    boolean signUp(Passenger passenger);

}
