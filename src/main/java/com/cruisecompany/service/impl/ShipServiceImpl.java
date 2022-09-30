package com.cruisecompany.service.impl;

import com.cruisecompany.db.dao.DAOFactory;
import com.cruisecompany.db.dao.ShipDAO;
import com.cruisecompany.entity.Ship;
import com.cruisecompany.service.ShipService;

import java.util.List;

public class ShipServiceImpl implements ShipService {
    private final ShipDAO shipDAO;

    public ShipServiceImpl() {
        this.shipDAO = DAOFactory.getInstance().getShipDAO();
    }
    @Override
    public List<Ship> getAll() {
        return shipDAO.getAll();
    }

    @Override
    public void addShip(Ship ship) {
        shipDAO.save(ship);
    }
}
