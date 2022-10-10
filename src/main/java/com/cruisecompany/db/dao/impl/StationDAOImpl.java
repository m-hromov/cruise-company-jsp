package com.cruisecompany.db.dao.impl;

import com.cruisecompany.db.Columns;
import com.cruisecompany.db.Tables;
import com.cruisecompany.db.dao.AbstractDAO;
import com.cruisecompany.db.dao.StationDAO;
import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.entity.Station;
import com.cruisecompany.exception.DAOException;

import java.util.List;

public class StationDAOImpl extends AbstractDAO<Station> implements StationDAO {
    public static final String INSERT = "INSERT INTO " + Tables.STATION + " (" + Columns.STATION_CITY +
            ", " + Columns.STATION_COUNTRY + ") VALUES (?, ?)";
    private static final String GET_ALL_ROUTE_BY_CRUISE_ID = "SELECT * FROM route AS r " +
            "JOIN station AS s ON s.station_id = r.station_id WHERE cruise_id = ? ORDER BY order_number";

    public StationDAOImpl(RowMapper<Station> rowMapper, String table) {
        super(rowMapper, table);
    }

    @Override
    public long save(Station station) throws DAOException {
        return executeInsert(INSERT, station.getCity(), station.getCountry());
    }

    @Override
    public void update(Station obj) throws DAOException {

    }

    @Override
    public List<Station> getAllStationsByCruiseID(long id) throws DAOException {
        return executeQuery(GET_ALL_ROUTE_BY_CRUISE_ID, id);
    }
}
