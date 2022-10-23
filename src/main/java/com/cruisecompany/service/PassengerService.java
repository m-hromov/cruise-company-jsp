package com.cruisecompany.service;

import com.cruisecompany.db.dto.PassengerOrderDTO;
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
     * Gets a passenger by it's UserAccount ID.
     *
     * @param id user's account ID
     * @return Passenger
     * @throws ServiceException if something went wrong
     */
    Passenger getPassengerByAccountId(long id) throws ServiceException;

    /**
     * Adds money to passenger's account.
     *
     * @param passengerId ID of the passenger
     * @param money       an amount of money to be added
     * @throws ServiceException if something went wrong
     */
    void addMoney(long passengerId, BigDecimal money) throws ServiceException;

    /**
     * Updates information about first/last name, email, phone.
     *
     * @param passenger Passenger with a new first/last name or email, or phone.
     * @throws ServiceException if something went wrong
     */
    void updateProfile(Passenger passenger) throws ServiceException;

    /**
     * Updates passenger's document
     *
     * @param passenger       Passenger with a new document path.
     * @param filePart        request part with file
     * @param requestRealPath request real path
     * @throws ServiceException if something went wrong
     */
    void updateDocument(Passenger passenger, Part filePart, String requestRealPath) throws ServiceException;
}
