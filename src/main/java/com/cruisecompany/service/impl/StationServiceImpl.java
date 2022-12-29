package com.cruisecompany.service.impl;

import com.cruisecompany.dao.DAOFactory;
import com.cruisecompany.dao.StationDAO;
import com.cruisecompany.dao.db.DBProvider;
import com.cruisecompany.entity.Station;
import com.cruisecompany.exception.DAOException;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.StationService;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.util.List;

@Log4j2
public class StationServiceImpl implements StationService {
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
            log.error("Unable to get all stations!");
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
            log.error("Unable to add stations!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }
}
