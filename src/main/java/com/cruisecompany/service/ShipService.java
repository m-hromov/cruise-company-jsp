package com.cruisecompany.service;

import com.cruisecompany.entity.Ship;
import com.cruisecompany.exception.ServiceException;

import java.util.List;

public interface ShipService {
    List<Ship> getAll() throws ServiceException;
    void addShip(Ship ship) throws ServiceException;
}
