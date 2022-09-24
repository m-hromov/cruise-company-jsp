package com.cruisecompany.service;

import com.cruisecompany.db.dto.UserAccountDTO;
import com.cruisecompany.db.entity.Passenger;
import com.cruisecompany.db.entity.UserAccount;

import java.util.Optional;

public interface UserAccountService {
    Optional<UserAccountDTO> signIn(String login, String password);

}
