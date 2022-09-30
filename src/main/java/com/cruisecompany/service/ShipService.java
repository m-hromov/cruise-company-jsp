package com.cruisecompany.service;

import com.cruisecompany.entity.Ship;

import java.util.List;

public interface ShipService {
    List<Ship> getAll();
    void addShip(Ship ship);
}
