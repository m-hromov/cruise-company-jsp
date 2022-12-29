package com.cruisecompany.service.impl;

import com.cruisecompany.dao.db.DBProvider;
import com.cruisecompany.dao.DAOFactory;
import com.cruisecompany.dao.ShipDAO;
import com.cruisecompany.entity.Ship;
import com.cruisecompany.exception.DAOException;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.ShipService;
import com.cruisecompany.util.files.FileHelper;
import com.cruisecompany.util.files.FileType;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@Log4j2
public class ShipServiceImpl implements ShipService {
    private final DBProvider dbProvider;
    private final ShipDAO shipDAO;

    public ShipServiceImpl(DBProvider dbProvider) {
        this.dbProvider = dbProvider;
        this.shipDAO = DAOFactory.getInstance().getShipDAO();
    }

    @Override
    public List<Ship> getAll() throws ServiceException {
        Connection connection = dbProvider.getConnection();
        try {
            return shipDAO.getAll(connection);
        } catch (DAOException e) {
            log.error("Unable to get all ships!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }

    @Override
    public void addShip(Ship ship, Part photoPart, String requestRealPath) throws ServiceException {
        Connection connection = dbProvider.getConnection();
        try {
            long shipId = shipDAO.save(connection, ship);
            String relativePath = "resources" + File.separator + "images" + File.separator + "ships";
            String uploadPath = requestRealPath + relativePath;
            String filename = FileHelper.writeRecord(photoPart,uploadPath, FileType.SHIP_PHOTO,shipId);
            ship.setId(shipId);
            ship.setPhotoPath(relativePath + File.separator + filename);
            shipDAO.update(connection,ship);
            dbProvider.commit(connection);
        } catch (DAOException | IOException e) {
            log.error("Unable to add a ship!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }
}
