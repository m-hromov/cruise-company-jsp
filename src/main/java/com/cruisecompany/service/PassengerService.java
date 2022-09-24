package com.cruisecompany.service;

import com.cruisecompany.db.dto.PassengerOrderDTO;
import com.cruisecompany.db.entity.Passenger;

import java.util.List;

public interface PassengerService {
    List<PassengerOrderDTO> getAllPassengerOrderDTOList();
    Passenger getPassengerByAccountId(long id);
}
