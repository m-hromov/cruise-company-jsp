package com.cruisecompany.db.dao.mapper.impl;

import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.db.entity.Station;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.cruisecompany.db.Columns.*;

public class StationRowMapper implements RowMapper<Station> {
    @Override
    public Station map(ResultSet rs) {
        try {
            Station station = new Station();
            station.setId(rs.getLong(STATION_ID))
                    .setName(rs.getString(STATION_NAME));
            return station;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
