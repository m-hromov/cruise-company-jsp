package com.cruisecompany.db.dao.impl;

import com.cruisecompany.db.dao.AbstractDAO;
import com.cruisecompany.db.dao.StationDAO;
import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.db.entity.Route;
import com.cruisecompany.db.entity.Station;

import java.util.List;

public class StationDAOImpl extends AbstractDAO<Station> implements StationDAO {
    public StationDAOImpl(RowMapper<Station> rowMapper, String table) {
        super(rowMapper, table);
    }

    @Override
    public long save(Station obj) {
        return 0;
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
