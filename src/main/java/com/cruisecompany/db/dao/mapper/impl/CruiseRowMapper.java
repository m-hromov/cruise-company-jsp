package com.cruisecompany.db.dao.mapper.impl;

import com.cruisecompany.db.dao.DAOFactory;
import com.cruisecompany.db.dao.ShipDAO;
import com.cruisecompany.db.dao.StationDAO;
import com.cruisecompany.db.dao.impl.ShipDAOImpl;
import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.db.dao.mapper.RowMapperFactory;
import com.cruisecompany.db.entity.Cruise;
import com.cruisecompany.db.entity.Ship;
import com.cruisecompany.db.entity.Station;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.cruisecompany.db.Tables.SHIP;
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
                    .setTimeDeparture(rs.getTime(TIME_DEPARTURE))
                    .setDateDeparture(rs.getDate(DATE_DEPARTURE))
                    .setDateArrival(rs.getDate(DATE_ARRIVAL))
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
