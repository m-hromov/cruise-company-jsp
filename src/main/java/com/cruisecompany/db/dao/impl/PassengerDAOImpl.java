package com.cruisecompany.db.dao.impl;

import com.cruisecompany.db.dao.AbstractDAO;
import com.cruisecompany.db.dao.PassengerDAO;
import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.db.entity.Passenger;

public class PassengerDAOImpl extends AbstractDAO<Passenger> implements PassengerDAO {
    public PassengerDAOImpl(RowMapper<Passenger> rowMapper, String table) {
        super(rowMapper, table);
    }

    @Override
    public long save(Passenger obj) {
        return 0;
    }

    @Override
    public void update(Passenger obj) {

    }
}
