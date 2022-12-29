package com.cruisecompany.dao;

import com.cruisecompany.dao.db.Tables;
import com.cruisecompany.dao.impl.*;
import com.cruisecompany.dao.mapper.RowMapperFactory;
import lombok.Getter;

@Getter
public final class DAOFactory {
    private static DAOFactory INSTANCE;
    private final CruiseDAO cruiseDAO;
    private final PassengerDAO passengerDAO;
    private final RouteDAO routeDAO;
    private final ShipDAO shipDAO;
    private final StaffDAO staffDAO;
    private final StationDAO stationDAO;
    private final UserAccountDAO userAccountDAO;
    private final TicketDAO ticketDAO;

    private DAOFactory() {
        RowMapperFactory rowMapperFactory = RowMapperFactory.getInstance();

        cruiseDAO = new CruiseDAOImpl(rowMapperFactory.getCruiseRowMapper());
        passengerDAO = new PassengerDAOImpl(rowMapperFactory.getPassengerRowMapper(), Tables.PASSENGER);
        routeDAO = new RouteDAOImpl(rowMapperFactory.getRouteRowMapper());
        shipDAO = new ShipDAOImpl(rowMapperFactory.getShipRowMapper(), Tables.SHIP);
        staffDAO = new StaffDAOImpl(rowMapperFactory.getStaffRowMapper(), Tables.STAFF);
        stationDAO = new StationDAOImpl(rowMapperFactory.getStationRowMapper(), Tables.STATION);
        userAccountDAO = new UserAccountDAOImpl(rowMapperFactory.getUserAccountRowMapper(), Tables.USER_ACCOUNT);
        ticketDAO = new TicketDAOImpl(rowMapperFactory.getTicketRowMapper(), Tables.TICKET);
    }

    public static DAOFactory getInstance() {
        if (INSTANCE == null) INSTANCE = new DAOFactory();
        return INSTANCE;
    }

}
