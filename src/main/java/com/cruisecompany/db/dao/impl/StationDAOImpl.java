package com.cruisecompany.db.dao.impl;

import com.cruisecompany.db.dao.AbstractDAO;
import com.cruisecompany.db.dao.StationDAO;
import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.db.entity.Station;

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
}
