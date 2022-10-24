package com.cruisecompany.db.dao.mapper.impl;

import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.entity.Station;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.cruisecompany.db.Columns.*;

public class StationRowMapper implements RowMapper<Station> {
    @Override
    public Station map(Connection connection, ResultSet rs) throws SQLException {
        Station station = new Station();
        station.setId(rs.getLong(STATION_ID))
                .setCity(rs.getString(STATION_CITY))
                .setCountry(rs.getString(STATION_COUNTRY));
        return station;
    }
}
