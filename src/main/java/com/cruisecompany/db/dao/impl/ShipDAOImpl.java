package com.cruisecompany.db.dao.impl;

import com.cruisecompany.db.dao.AbstractDAO;
import com.cruisecompany.db.dao.ShipDAO;
import com.cruisecompany.db.entity.Ship;
import com.cruisecompany.db.dao.mapper.RowMapper;

public class ShipDAOImpl extends AbstractDAO<Ship> implements ShipDAO {
    public ShipDAOImpl(RowMapper<Ship> rowMapper, String table) {
        super(rowMapper, table);
    }

    @Override
    public long save(Ship obj) {
        return 0;
    }

    @Override
    public void update(Ship obj) {

    }
}
