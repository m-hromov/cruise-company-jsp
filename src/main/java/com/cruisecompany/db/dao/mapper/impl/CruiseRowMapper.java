package com.cruisecompany.db.dao.mapper.impl;

import com.cruisecompany.db.DBProvider;
import com.cruisecompany.db.dao.DAOFactory;
import com.cruisecompany.db.dao.StationDAO;
import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.db.dao.mapper.RowMapperFactory;
import com.cruisecompany.entity.Cruise;
import com.cruisecompany.entity.Ship;
import com.cruisecompany.entity.Station;
import com.cruisecompany.exception.DAOException;
import com.cruisecompany.exception.DatabaseException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.cruisecompany.db.Columns.*;

public class CruiseRowMapper implements RowMapper<Cruise> {
    @Override
    public Cruise map(Connection connection, ResultSet rs) throws DAOException, SQLException {
        Ship ship = RowMapperFactory.getInstance().getShipRowMapper().map(connection, rs);

        StationDAO stationDAO = DAOFactory.getInstance().getStationDAO();
        List<Station> stationList = stationDAO.getAllStationsByCruiseID(connection,
                    rs.getLong(CRUISE_ID));

        Cruise cruise = new Cruise();
        cruise.setId(rs.getLong(CRUISE_ID))
                .setTimeDeparture(LocalTime.parse(rs.getString(TIME_DEPARTURE)))
                .setDateDeparture(LocalDate.parse(rs.getString(DATE_DEPARTURE)))
                .setDateArrival(LocalDate.parse(rs.getString(DATE_ARRIVAL)))
                .setDaysTotal(rs.getInt(DAYS_TOTAL))
                .setDescription(rs.getString(CRUISE_DESCRIPTION))
                .setPrice(BigDecimal.valueOf(rs.getDouble(PRICE)))
                .setShip(ship)
                .setStationList(stationList);
        return cruise;
    }
}
