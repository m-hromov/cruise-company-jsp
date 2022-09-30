package com.cruisecompany.db.dao.mapper.impl;

import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.entity.Ship;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.cruisecompany.db.Columns.*;

public class ShipRowMapper implements RowMapper<Ship> {
    @Override
    public Ship map(ResultSet rs) {
        try {
            Ship ship = new Ship();
            ship.setId(rs.getLong(SHIP_ID))
                    .setName(rs.getString(SHIP_NAME))
                    .setPassengerCapacity(rs.getInt(PASSENGER_CAPACITY))
                    .setPhotoPath(rs.getString(PHOTO_PATH));
            return ship;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
