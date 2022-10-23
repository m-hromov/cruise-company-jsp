package com.cruisecompany.service;

import com.cruisecompany.db.dto.UserAccountDTO;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.exception.ServiceException;

import java.util.Optional;

public interface UserAccountService {
    /**
     * Signs in a user if an email and password is correct.
     *
     * @param email    user's email
     * @param password user's password
     * @return Nullable Optional of UserAccountDTO
     * @throws ServiceException if password/email is incorrect
     */
    Optional<UserAccountDTO> signIn(String email, String password) throws ServiceException;

    /**
     * Signs up a passenger.
     *
     * @param passenger new passenger
     * @throws ServiceException if email has already been registered/something went wrong
     */
    void signUp(Passenger passenger) throws ServiceException;

    /**
     * Updates user's password if the old one is the same
     *
     * @param userId      user's ID
     * @param oldPassword the old password to be checked
     * @param newPassword a new password
     * @throws ServiceException if the old password is not the same/something went wrong
     */
    void updatePassword(long userId, String oldPassword, String newPassword) throws ServiceException;

}
