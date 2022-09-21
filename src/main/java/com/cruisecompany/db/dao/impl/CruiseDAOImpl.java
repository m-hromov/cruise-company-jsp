package com.cruisecompany.db.dao.impl;

import com.cruisecompany.db.Tables;
import com.cruisecompany.db.dao.AbstractDAO;
import com.cruisecompany.db.dao.CruiseDAO;
import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.db.entity.Cruise;

import java.util.List;

public class CruiseDAOImpl extends AbstractDAO<Cruise> implements CruiseDAO {
    private static final String GET_ALL_CRUISE_STATIONS = "SELECT * FROM " + Tables.CRUISE + " AS c " +
            "JOIN " + Tables.SHIP + " AS s ON s.ship_id = c.ship_id ";

    public CruiseDAOImpl(RowMapper<Cruise> rowMapper) {
        super(rowMapper, Tables.CRUISE);
    }

    @Override
    public long save(Cruise obj) {
        return 0;
    }

    @Override
    public void update(Cruise obj) {

    }

    @Override
    public List<Cruise> getAll() {
        return executeQuery(GET_ALL_CRUISE_STATIONS);
    }
}
