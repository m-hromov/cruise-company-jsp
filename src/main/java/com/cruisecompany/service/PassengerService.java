package com.cruisecompany.service;

import com.cruisecompany.db.dto.PassengerOrderDTO;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;

public interface PassengerService {
    List<PassengerOrderDTO> getAllPassengerOrderDTOList() throws ServiceException;
    Passenger getPassengerByAccountId(long id) throws ServiceException;
    void addMoney(long passengerId,BigDecimal money) throws ServiceException;
    void updateProfile(Passenger passenger) throws ServiceException;
    void updateDocument(Passenger passenger) throws ServiceException;
}
