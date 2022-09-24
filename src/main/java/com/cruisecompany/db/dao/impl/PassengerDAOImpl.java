package com.cruisecompany.db.dao.impl;

import com.cruisecompany.db.Columns;
import com.cruisecompany.db.Tables;
import com.cruisecompany.db.dao.AbstractDAO;
import com.cruisecompany.db.dao.PassengerDAO;
import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.db.entity.Passenger;

import java.util.Optional;

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
    private final String GET_BY_USER_ACCOUNT_ID = "SELECT * FROM "+ Tables.PASSENGER + " WHERE "+ Columns.USER_ACCOUNT_ID + " = ?";
    @Override
    public Optional<Passenger> getByUserAccountId(long id) {
        return executeSingleGetQuery(GET_BY_USER_ACCOUNT_ID,id);
    }
}
