package com.cruisecompany.dao.mapper.impl;

import com.cruisecompany.dao.mapper.RowMapper;
import com.cruisecompany.entity.Ship;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.cruisecompany.dao.db.Columns.*;

public class ShipRowMapper implements RowMapper<Ship> {
    @Override
    public Ship map(Connection connection, ResultSet rs) throws SQLException {
        return Ship.builder()
                .id(rs.getLong(SHIP_ID))
                .name(rs.getString(SHIP_NAME))
                .passengerCapacity(rs.getInt(PASSENGER_CAPACITY))
                .photoPath(rs.getString(PHOTO_PATH))
                .build();
    }
}
