package com.cruisecompany.service;

import com.cruisecompany.dto.PassengerDTO;
import com.cruisecompany.dto.PassengerOrderDTO;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.exception.ServiceException;

import javax.servlet.http.Part;
import java.math.BigDecimal;
import java.util.List;

public interface PassengerService {
    /**
     * Gets all passenger's orders and converts them to PassengerOrderDTO
     *
     * @return List of PassengerOrderDTO
     * @throws ServiceException if something went wrong
     */
    List<PassengerOrderDTO> getAllPassengerOrderDTOList() throws ServiceException;

    /**
     * Gets a passenger by its UserAccount ID.
     *
     * @param id user's account ID
     * @return Passenger
     * @throws ServiceException if something went wrong
     */
    Passenger getPassengerByAccountId(long id) throws ServiceException;

    /**
     * Adds money to passenger's account.
     *
     * @param passengerDTO PassengerDTO with ID of the passenger
     * @param money       an amount of money to be added
     * @throws ServiceException if something went wrong
     */
    void addMoney(PassengerDTO passengerDTO, BigDecimal money) throws ServiceException;

    /**
     * Updates information about first/last name, email, phone.
     *
     * @param passengerDTO Passenger with a new first/last name or email, or phone.
     * @throws ServiceException if something went wrong
     */
    void updateProfile(PassengerDTO passengerDTO) throws ServiceException;

    /**
     * Updates passenger's document
     *
     * @param passengerDTO    Passenger
     * @param filePart        request part with file
     * @param requestRealPath request real path
     * @throws ServiceException if something went wrong
     */
    void updateDocument(PassengerDTO passengerDTO, Part filePart, String requestRealPath) throws ServiceException;
}
