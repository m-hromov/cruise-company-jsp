package com.cruisecompany.db.dao.mapper.impl;

import com.cruisecompany.db.dao.DAOFactory;
import com.cruisecompany.db.dao.StationDAO;
import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.db.dao.mapper.RowMapperFactory;
import com.cruisecompany.entity.Cruise;
import com.cruisecompany.entity.Ship;
import com.cruisecompany.entity.Station;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.cruisecompany.db.Columns.*;

public class CruiseRowMapper implements RowMapper<Cruise> {
    @Override
    public Cruise map(ResultSet rs) {
        try {
            Ship ship = RowMapperFactory.getInstance().getShipRowMapper().map(rs);

            StationDAO stationDAO = DAOFactory.getInstance().getStationDAO();
            List<Station> stationList = stationDAO.getAllStationsByCruiseID(rs.getLong(CRUISE_ID));

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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
