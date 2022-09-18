package com.cruisecompany.db.dao.impl;

import com.cruisecompany.db.dao.AbstractDAO;
import com.cruisecompany.db.dao.CruiseDAO;
import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.db.entity.Cruise;
import com.cruisecompany.db.dao.mapper.RowMapperFactory;
import com.cruisecompany.db.entity.Ship;

public class CruiseDAOImpl extends AbstractDAO<Cruise> implements CruiseDAO {
    public CruiseDAOImpl(RowMapper<Cruise> rowMapper, String table) {
        super(rowMapper, table);
    }
    @Override
    public long save(Cruise obj) {
        return 0;
    }

    @Override
    public void update(Cruise obj) {

    }
}
