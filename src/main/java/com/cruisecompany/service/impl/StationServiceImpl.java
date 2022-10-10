package com.cruisecompany.service.impl;

import com.cruisecompany.db.dao.DAOFactory;
import com.cruisecompany.db.dao.StationDAO;
import com.cruisecompany.entity.Station;
import com.cruisecompany.exception.DAOException;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.StationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class StationServiceImpl implements StationService {
    final static Logger logger = LogManager.getLogger(StaffServiceImpl.class);
    private final StationDAO stationDAO;

    public StationServiceImpl() {
        this.stationDAO = DAOFactory.getInstance().getStationDAO();
    }
    @Override
    public List<Station> getAll() throws ServiceException {
        try {
            return stationDAO.getAll();
        } catch (DAOException e) {
            logger.error("Unable to get all stations!");
            throw new ServiceException(e.getMessage(),e);
        }
    }

    @Override
    public void addStation(Station station) throws ServiceException {
        try {
            stationDAO.save(station);
        } catch (DAOException e) {
            logger.error("Unable to add stations!");
            throw new ServiceException(e.getMessage(),e);
        }
    }
}
