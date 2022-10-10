package com.cruisecompany.service;

import com.cruisecompany.db.dto.UserAccountDTO;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.exception.ServiceException;

import java.util.Optional;

public interface UserAccountService {
    Optional<UserAccountDTO> signIn(String login, String password) throws ServiceException;
    void signUp(Passenger passenger) throws ServiceException;
    void updatePassword(long userId, String oldPassword, String newPassword) throws ServiceException;

}
