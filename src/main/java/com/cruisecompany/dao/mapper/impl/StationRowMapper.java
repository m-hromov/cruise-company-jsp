package com.cruisecompany.dao.mapper.impl;

import com.cruisecompany.dao.mapper.RowMapper;
import com.cruisecompany.entity.Station;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.cruisecompany.dao.db.Columns.*;

public class StationRowMapper implements RowMapper<Station> {
    @Override
    public Station map(Connection connection, ResultSet rs) throws SQLException {
        return Station.builder()
                .id(rs.getLong(STATION_ID))
                .city(rs.getString(STATION_CITY))
                .country(rs.getString(STATION_COUNTRY))
                .build();
    }
}
