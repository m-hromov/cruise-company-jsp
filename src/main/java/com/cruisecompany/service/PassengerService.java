package com.cruisecompany.service;

import com.cruisecompany.db.dto.PassengerOrderDTO;
import com.cruisecompany.entity.Passenger;

import java.math.BigDecimal;
import java.util.List;

public interface PassengerService {
    List<PassengerOrderDTO> getAllPassengerOrderDTOList();
    Passenger getPassengerByAccountId(long id);
    void addMoney(long passengerId,BigDecimal money);
    boolean updateProfile(Passenger passenger);
    void updateDocument(Passenger passenger);
}
