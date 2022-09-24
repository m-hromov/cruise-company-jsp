package com.cruisecompany.db.dao.mapper;

import com.cruisecompany.db.dao.mapper.impl.*;
import com.cruisecompany.db.entity.*;

public class RowMapperFactory {
    private static RowMapperFactory INSTANCE;
    private final RowMapper<Cruise> cruiseRowMapper;
    private final RowMapper<Passenger> passengerRowMapper;
    private final RowMapper<Order> orderRowMapper;
    private final RowMapper<Route> routeRowMapper;
    private final RowMapper<Ship> shipRowMapper;
    private final RowMapper<Staff> staffRowMapper;
    private final RowMapper<Station> stationRowMapper;
    private final RowMapper<UserAccount> userAccountRowMapper;

    private RowMapperFactory() {
        cruiseRowMapper = new CruiseRowMapper();
        passengerRowMapper = new PassengerRowMapper();
        orderRowMapper = new OrderRowMapper();
        routeRowMapper = new RouteRowMapper();
        shipRowMapper = new ShipRowMapper();
        staffRowMapper = new StaffRowMapper();
        stationRowMapper = new StationRowMapper();
        userAccountRowMapper = new UserAccountRowMapper();
    }

    public static RowMapperFactory getInstance() {
        if (INSTANCE == null) INSTANCE = new RowMapperFactory();
        return INSTANCE;
    }

    public RowMapper<Cruise> getCruiseRowMapper() {
        return cruiseRowMapper;
    }

    public RowMapper<Passenger> getPassengerRowMapper() {
        return passengerRowMapper;
    }

    public RowMapper<Order> getPassengerCruiseRowMapper() {
        return orderRowMapper;
    }

    public RowMapper<Route> getRouteRowMapper() {
        return routeRowMapper;
    }

    public RowMapper<Ship> getShipRowMapper() {
        return shipRowMapper;
    }

    public RowMapper<Staff> getStaffRowMapper() {
        return staffRowMapper;
    }

    public RowMapper<Station> getStationRowMapper() {
        return stationRowMapper;
    }

    public RowMapper<UserAccount> getUserAccountRowMapper() {
        return userAccountRowMapper;
    }
}
