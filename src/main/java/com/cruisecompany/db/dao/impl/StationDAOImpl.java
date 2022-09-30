package com.cruisecompany.db.dao.impl;

import com.cruisecompany.db.Columns;
import com.cruisecompany.db.Tables;
import com.cruisecompany.db.dao.AbstractDAO;
import com.cruisecompany.db.dao.StationDAO;
import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.entity.Station;

import java.util.List;

public class StationDAOImpl extends AbstractDAO<Station> implements StationDAO {
    public StationDAOImpl(RowMapper<Station> rowMapper, String table) {
        super(rowMapper, table);
    }
    public static final String INSERT = "INSERT INTO "+ Tables.STATION + " (" + Columns.STATION_CITY +
            ", " + Columns.STATION_COUNTRY + ") VALUES (?, ?)";
    @Override
    public long save(Station station) {
        return executeInsert(INSERT, station.getCity(), station.getCountry());
    }

    @Override
    public void update(Station obj) {

    }
    private static final String GET_ALL_ROUTE_BY_CRUISE_ID = "SELECT * FROM route AS r " +
            "JOIN station AS s ON s.station_id = r.station_id WHERE cruise_id = ? ORDER BY order_number";
    @Override
    public List<Station> getAllStationsByCruiseID(long id) {
        return executeQuery(GET_ALL_ROUTE_BY_CRUISE_ID,id);
    }
}
