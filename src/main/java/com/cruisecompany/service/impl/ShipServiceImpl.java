package com.cruisecompany.service.impl;

import com.cruisecompany.db.dao.DAOFactory;
import com.cruisecompany.db.dao.ShipDAO;
import com.cruisecompany.entity.Ship;
import com.cruisecompany.exception.DAOException;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.ShipService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ShipServiceImpl implements ShipService {
    final static Logger logger = LogManager.getLogger(ShipServiceImpl.class);
    private final ShipDAO shipDAO;

    public ShipServiceImpl() {
        this.shipDAO = DAOFactory.getInstance().getShipDAO();
    }
    @Override
    public List<Ship> getAll() throws ServiceException {
        try {
            return shipDAO.getAll();
        } catch (DAOException e) {
            logger.error("Unable to get all ships!");
            throw new ServiceException(e.getMessage(),e);
        }
    }

    @Override
    public void addShip(Ship ship) throws ServiceException {
        try {
            shipDAO.save(ship);
        } catch (DAOException e) {
            logger.error("Unable to add a ship!");
            throw new ServiceException(e.getMessage(),e);
        }
    }
}
