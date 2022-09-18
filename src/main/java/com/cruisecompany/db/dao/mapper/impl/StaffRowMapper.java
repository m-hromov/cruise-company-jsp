package com.cruisecompany.db.dao.mapper.impl;

import com.cruisecompany.db.dao.ShipDAO;
import com.cruisecompany.db.dao.impl.ShipDAOImpl;
import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.db.dao.mapper.RowMapperFactory;
import com.cruisecompany.db.entity.Route;
import com.cruisecompany.db.entity.Ship;
import com.cruisecompany.db.entity.Staff;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.cruisecompany.db.dao.mapper.Tables.SHIP;
import static com.cruisecompany.db.dao.mapper.impl.Columns.*;

public class StaffRowMapper implements RowMapper<Staff> {
    @Override
    public Staff map(ResultSet rs) {
        try {
            RowMapper<Ship> shipRowMapper = RowMapperFactory.getInstance().getShipRowMapper();
            ShipDAO shipDAO = new ShipDAOImpl(shipRowMapper, SHIP);
            Ship ship = shipDAO.get(rs.getLong(ID_SHIP)).orElse(new Ship());

            Staff staff = new Staff();
            staff.setId(rs.getLong(ID))
                    .setFirstName(rs.getString(FIRST_NAME))
                    .setLastName(LAST_NAME)
                    .setSpeciality(SPECIALITY)
                    .setShip(ship);
            return staff;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
