package com.cruisecompany.service.impl;

import com.cruisecompany.db.DBProvider;
import com.cruisecompany.db.dao.DAOFactory;
import com.cruisecompany.db.dao.StationDAO;
import com.cruisecompany.entity.Station;
import com.cruisecompany.exception.DAOException;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.StationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class StationServiceImpl implements StationService {
    final static Logger logger = LogManager.getLogger(StaffServiceImpl.class);
    private final DBProvider dbProvider;
    private final StationDAO stationDAO;

    public StationServiceImpl(DBProvider dbProvider) {
        this.dbProvider = dbProvider;
        this.stationDAO = DAOFactory.getInstance().getStationDAO();
    }

    @Override
    public List<Station> getAll() throws ServiceException {
        Connection connection = dbProvider.getConnection();
        try {
            return stationDAO.getAll(connection);
        } catch (DAOException e) {
            logger.error("Unable to get all stations!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }

    @Override
    public void addStation(Station station) throws ServiceException {
        Connection connection = dbProvider.getConnection();
        try {
            stationDAO.save(connection, station);
            dbProvider.commit(connection);
        } catch (DAOException e) {
            logger.error("Unable to add stations!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }
}
