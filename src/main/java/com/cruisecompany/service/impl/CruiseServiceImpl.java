package com.cruisecompany.service.impl;

import com.cruisecompany.db.DBProvider;
import com.cruisecompany.db.dao.CruiseDAO;
import com.cruisecompany.db.dao.DAOFactory;
import com.cruisecompany.db.dao.RouteDAO;
import com.cruisecompany.dto.CruiseShowDTO;
import com.cruisecompany.dto.mapper.DTOMapper;
import com.cruisecompany.entity.Cruise;
import com.cruisecompany.entity.Route;
import com.cruisecompany.entity.Station;
import com.cruisecompany.exception.DAOException;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.CruiseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CruiseServiceImpl implements CruiseService {
    final static Logger logger = LogManager.getLogger(CruiseServiceImpl.class);
    private final DBProvider dbProvider;
    private final CruiseDAO cruiseDAO;
    private final RouteDAO routeDAO;

    public CruiseServiceImpl(DBProvider dbProvider) {
        this.dbProvider = dbProvider;
        cruiseDAO = DAOFactory.getInstance().getCruiseDAO();
        routeDAO = DAOFactory.getInstance().getRouteDAO();
    }

    @Override
    public List<CruiseShowDTO> getAllCruiseShowDTO() throws ServiceException {
        Connection connection = dbProvider.getConnection();
        try {
            List<Cruise> cruiseList = cruiseDAO.getAll(connection);
            return cruiseList.stream().map(DTOMapper::toCruiseShowDTO).collect(Collectors.toList());
        } catch (DAOException e) {
            logger.error("Unable to get all CruiseShowDTO!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }

    @Override
    public List<CruiseShowDTO> getAllFilteredCruiseShowDTO(LocalDate dateFrom, LocalDate dateTo, int durationFrom, int durationTo, int limit, int offset) throws ServiceException {
        Connection connection = dbProvider.getConnection();
        try {
            List<Cruise> cruiseList = cruiseDAO.getAllFiltered(connection, dateFrom, dateTo, durationFrom, durationTo, limit, offset);
            return cruiseList.stream().map(DTOMapper::toCruiseShowDTO).collect(Collectors.toList());
        } catch (DAOException e) {
            logger.error("Unable to get all filtered CruiseShowDTO!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }

    @Override
    public int getPageAmount(LocalDate dateFrom, LocalDate dateTo, int durationFrom, int durationTo, int limit) throws ServiceException {
        Connection connection = dbProvider.getConnection();
        try {
            long rowAmount = cruiseDAO.getCruiseRowAmount(connection, dateFrom, dateTo, durationFrom, durationTo);
            return (int) Math.ceil((double) rowAmount / limit);
        } catch (DAOException e) {
            logger.error("Unable to get page amount!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }

    @Override
    public List<CruiseShowDTO> getAllCruiseShowDTOBySearch(String str) {
        throw new RuntimeException();
    }

    @Override
    public void addCruise(Cruise cruise) throws ServiceException {
        Connection connection = dbProvider.getConnection();
        try {
            long cruiseId = cruiseDAO.save(connection, cruise);
            cruise.setId(cruiseId);
            List<Station> stationList = cruise.getStationList();
            for (int i = 0; i < stationList.size(); i++) {
                Route route = new Route();
                route.setCruise(cruise)
                        .setStation(stationList.get(i))
                        .setOrderNumber(i);
                routeDAO.save(connection, route);
            }
            dbProvider.commit(connection);
        } catch (DAOException e) {
            dbProvider.rollback(connection);
            logger.error("Unable to add cruise!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }

    @Override
    public Optional<CruiseShowDTO> getCruiseShowDTOById(long id) throws ServiceException {
        Connection connection = dbProvider.getConnection();
        try {
            Optional<Cruise> optional = cruiseDAO.get(connection, id);
            if (optional.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(DTOMapper.toCruiseShowDTO(optional.get()));
        } catch (DAOException e) {
            logger.error("Unable to get CruiseShowDTO by id!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }
}
