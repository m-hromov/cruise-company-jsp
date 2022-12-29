package com.cruisecompany.service.impl;

import com.cruisecompany.dao.CruiseDAO;
import com.cruisecompany.dao.DAOFactory;
import com.cruisecompany.dao.RouteDAO;
import com.cruisecompany.dao.db.DBProvider;
import com.cruisecompany.dto.CruiseShowDTO;
import com.cruisecompany.dto.mapper.DTOMapper;
import com.cruisecompany.entity.Cruise;
import com.cruisecompany.entity.Route;
import com.cruisecompany.entity.Station;
import com.cruisecompany.exception.DAOException;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.CruiseService;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
public class CruiseServiceImpl implements CruiseService {
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
            log.error("Unable to get all CruiseShowDTO!");
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
            log.error("Unable to get all filtered CruiseShowDTO!");
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
            log.error("Unable to get page amount!");
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
    public int hashCode() {
        return 48;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public void addCruise(Cruise cruise) throws ServiceException {
        Connection connection = dbProvider.getConnection();
        try {
            long cruiseId = cruiseDAO.save(connection, cruise);
            cruise.setId(cruiseId);
            List<Station> stationList = cruise.getStationList();
            int i = 0;
            for (Station station : stationList) {
                Route route = Route.builder()
                        .cruise(cruise)
                        .station(station)
                        .orderNumber(i)
                        .build();
                routeDAO.save(connection, route);
            }
            dbProvider.commit(connection);
        } catch (DAOException e) {
            dbProvider.rollback(connection);
            log.error("Unable to add cruise!");
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
            log.error("Unable to get CruiseShowDTO by id!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }
}
