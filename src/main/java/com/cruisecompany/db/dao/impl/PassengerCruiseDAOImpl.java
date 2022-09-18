package com.cruisecompany.db.dao.impl;

import com.cruisecompany.db.dao.AbstractDAO;
import com.cruisecompany.db.dao.PassengerCruiseDAO;
import com.cruisecompany.db.entity.PassengerCruise;
import com.cruisecompany.db.dao.mapper.RowMapper;

public class PassengerCruiseDAOImpl extends AbstractDAO<PassengerCruise> implements PassengerCruiseDAO {
    public PassengerCruiseDAOImpl(RowMapper<PassengerCruise> rowMapper, String table) {
        super(rowMapper, table);
    }

    @Override
    public long save(PassengerCruise obj) {
        return 0;
    }

    @Override
    public void update(PassengerCruise obj) {

    }
}
