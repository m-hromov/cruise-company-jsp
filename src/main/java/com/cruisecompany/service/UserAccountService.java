package com.cruisecompany.service;

import com.cruisecompany.dto.PassengerDTO;
import com.cruisecompany.dto.UserAccountDTO;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.exception.AuthorizationException;
import com.cruisecompany.exception.EmailAlreadyExistsException;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.exception.WrongPasswordException;

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
    Optional<PassengerDTO> signIn(String email, String password) throws ServiceException, AuthorizationException;

    /**
     * Signs up a passenger.
     *
     * @param passenger new passenger
     * @throws ServiceException if something went wrong
     * @throws EmailAlreadyExistsException if email has been already registered
     */
    void signUp(Passenger passenger) throws ServiceException, EmailAlreadyExistsException;

    /**
     * Updates user's password if the old one is the same
     *
     * @param userId      user's ID
     * @param oldPassword the old password to be checked
     * @param newPassword a new password
     * @throws ServiceException if the old password is not the same/something went wrong
     */
    void updatePassword(long userId, String oldPassword, String newPassword) throws ServiceException, WrongPasswordException;

}
