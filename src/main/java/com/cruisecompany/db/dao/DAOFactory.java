package com.cruisecompany.db.dao;

import com.cruisecompany.db.dao.impl.*;
import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.db.dao.mapper.RowMapperFactory;
import com.cruisecompany.db.dao.mapper.impl.*;
import com.cruisecompany.db.entity.*;

import static com.cruisecompany.db.Tables.*;

public class DAOFactory {
    private static DAOFactory INSTANCE;
    private final CruiseDAO cruiseDAO;
    private final PassengerDAO passengerDAO;
    private final RouteDAO routeDAO;
    private final ShipDAO shipDAO;
    private final StaffDAO staffDAO;
    private final StationDAO stationDAO;
    private final UserAccountDAO userAccountDAO;
    private final PassengerCruiseDAO passengerCruiseDAO;
    private DAOFactory() {
        RowMapperFactory rowMapperFactory = RowMapperFactory.getInstance();

        cruiseDAO = new CruiseDAOImpl(rowMapperFactory.getCruiseRowMapper());
        passengerDAO = new PassengerDAOImpl(rowMapperFactory.getPassengerRowMapper(), PASSENGER);
        routeDAO = new RouteDAOImpl(rowMapperFactory.getRouteRowMapper(),ROUTE );
        shipDAO = new ShipDAOImpl(rowMapperFactory.getShipRowMapper(),SHIP );
        staffDAO = new StaffDAOImpl(rowMapperFactory.getStaffRowMapper(),STAFF );
        stationDAO = new StationDAOImpl(rowMapperFactory.getStationRowMapper(),STATION );
        userAccountDAO = new UserAccountDAOImpl(rowMapperFactory.getUserAccountRowMapper(),USER_ACCOUNT );
        passengerCruiseDAO = new PassengerCruiseDAOImpl(rowMapperFactory.getPassengerCruiseRowMapper(),PASSENGER_CRUISE );
    }

    public static DAOFactory getInstance() {
        if (INSTANCE == null) INSTANCE = new DAOFactory();
        return INSTANCE;
    }

    public CruiseDAO getCruiseDAO() {
        return cruiseDAO;
    }

    public PassengerDAO getPassengerDAO() {
        return passengerDAO;
    }

    public RouteDAO getRouteDAO() {
        return routeDAO;
    }

    public ShipDAO getShipDAO() {
        return shipDAO;
    }

    public StaffDAO getStaffDAO() {
        return staffDAO;
    }

    public StationDAO getStationDAO() {
        return stationDAO;
    }

    public UserAccountDAO getUserAccountDAO() {
        return userAccountDAO;
    }

    public PassengerCruiseDAO getPassengerCruiseDAO() {
        return passengerCruiseDAO;
    }
}
