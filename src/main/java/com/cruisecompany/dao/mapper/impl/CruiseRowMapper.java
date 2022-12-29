package com.cruisecompany.dao.mapper.impl;

import com.cruisecompany.dao.DAOFactory;
import com.cruisecompany.dao.StationDAO;
import com.cruisecompany.dao.mapper.RowMapper;
import com.cruisecompany.dao.mapper.RowMapperFactory;
import com.cruisecompany.entity.Cruise;
import com.cruisecompany.entity.Ship;
import com.cruisecompany.entity.Station;
import com.cruisecompany.exception.DAOException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.cruisecompany.dao.db.Columns.*;

public class CruiseRowMapper implements RowMapper<Cruise> {
    @Override
    public Cruise map(Connection connection, ResultSet rs) throws DAOException, SQLException {
        Ship ship = RowMapperFactory.getInstance().getShipRowMapper().map(connection, rs);

        StationDAO stationDAO = DAOFactory.getInstance().getStationDAO();
        List<Station> stationList = stationDAO.getAllStationsByCruiseID(connection,
                    rs.getLong(CRUISE_ID));

        return Cruise.builder()
                .id(rs.getLong(CRUISE_ID))
                .timeDeparture(LocalTime.parse(rs.getString(TIME_DEPARTURE)))
                .dateDeparture(LocalDate.parse(rs.getString(DATE_DEPARTURE)))
                .dateArrival(LocalDate.parse(rs.getString(DATE_ARRIVAL)))
                .daysTotal(rs.getInt(DAYS_TOTAL))
                .description(rs.getString(CRUISE_DESCRIPTION))
                .price(BigDecimal.valueOf(rs.getDouble(PRICE)))
                .ticketsPurchased(rs.getInt(TICKETS_PURCHASED))
                .ship(ship)
                .stationList(stationList)
                .build();
    }
}
