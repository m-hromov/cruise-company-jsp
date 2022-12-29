package com.cruisecompany.dao.mapper;

import com.cruisecompany.dao.mapper.impl.*;
import com.cruisecompany.entity.*;
import lombok.Getter;

@Getter
public final class RowMapperFactory {
    private static RowMapperFactory INSTANCE;
    private final RowMapper<Cruise> cruiseRowMapper;
    private final RowMapper<Passenger> passengerRowMapper;
    private final RowMapper<Ticket> ticketRowMapper;
    private final RowMapper<Route> routeRowMapper;
    private final RowMapper<Ship> shipRowMapper;
    private final RowMapper<Staff> staffRowMapper;
    private final RowMapper<Station> stationRowMapper;
    private final RowMapper<UserAccount> userAccountRowMapper;

    private RowMapperFactory() {
        cruiseRowMapper = new CruiseRowMapper();
        passengerRowMapper = new PassengerRowMapper();
        ticketRowMapper = new TicketRowMapper();
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
}
