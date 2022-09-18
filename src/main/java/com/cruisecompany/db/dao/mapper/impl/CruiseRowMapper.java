package com.cruisecompany.db.dao.mapper.impl;

import com.cruisecompany.db.dao.ShipDAO;
import com.cruisecompany.db.dao.impl.ShipDAOImpl;
import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.db.dao.mapper.RowMapperFactory;
import com.cruisecompany.db.entity.Cruise;
import com.cruisecompany.db.entity.Ship;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.cruisecompany.db.dao.mapper.Tables.SHIP;
import static com.cruisecompany.db.dao.mapper.impl.Columns.*;

public class CruiseRowMapper implements RowMapper<Cruise> {
    @Override
    public Cruise map(ResultSet rs) {
        try {
            RowMapper<Ship> shipRowMapper = RowMapperFactory.getInstance().getShipRowMapper();
            ShipDAO shipDAO = new ShipDAOImpl(shipRowMapper, SHIP);
            Ship ship = shipDAO.get(rs.getLong(ID_SHIP)).orElse(new Ship());

            Cruise cruise = new Cruise();
            cruise.setId(rs.getLong(ID))
                    .setTimeDeparture(rs.getTime(TIME_DEPARTURE))
                    .setDateDeparture(rs.getDate(DATE_DEPARTURE))
                    .setDateArrival(rs.getDate(DATE_ARRIVAL))
                    .setDaysTotal(rs.getInt(DAYS_TOTAL))
                    .setDescription(rs.getString(CRUISE_DESCRIPTION))
                    .setPrice(BigDecimal.valueOf(rs.getDouble(PRICE)))
                    .setShip(ship);
            return cruise;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
