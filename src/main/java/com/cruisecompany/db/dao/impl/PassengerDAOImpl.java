package com.cruisecompany.db.dao.impl;

import com.cruisecompany.db.Columns;
import com.cruisecompany.db.Tables;
import com.cruisecompany.db.dao.AbstractDAO;
import com.cruisecompany.db.dao.PassengerDAO;
import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.entity.Passenger;

import java.math.BigDecimal;
import java.util.Optional;

public class PassengerDAOImpl extends AbstractDAO<Passenger> implements PassengerDAO {
    private static final String UPDATE_MONEY = "UPDATE " + Tables.PASSENGER + " SET " + Columns.MONEY + " = ? " +
            "WHERE " + Columns.PASSENGER_ID + " = ?";
    private static final String ADD_MONEY = "UPDATE " + Tables.PASSENGER + " SET " + Columns.MONEY + " = " +
            Columns.MONEY + " + ? " + "WHERE " + Columns.PASSENGER_ID + " = ?";
    private final String GET_BY_USER_ACCOUNT_ID = "SELECT * FROM " + Tables.PASSENGER + " WHERE " + Columns.USER_ACCOUNT_ID + " = ?";

    public PassengerDAOImpl(RowMapper<Passenger> rowMapper, String table) {
        super(rowMapper, table);
    }
    private final static String INSERT = "INSERT INTO " + Tables.PASSENGER + " ("+Columns.FIRST_NAME+"," +
            Columns.LAST_NAME+","+Columns.PHONE+","+Columns.EMAIL+","+Columns.MONEY+"," +
            Columns.USER_ACCOUNT_ID+") VALUES (?,?,?,?,?,?)";
    @Override
    public long save(Passenger passenger) {
        return executeInsert(INSERT,passenger.getFirstName(), passenger.getLastName(),
                passenger.getPhone(),passenger.getEmail(),passenger.getMoney(), passenger.getUserAccount().getId());
    }

    @Override
    public void update(Passenger obj) {

    }

    @Override
    public Optional<Passenger> getByUserAccountId(long id) {
        return executeSingleGetQuery(GET_BY_USER_ACCOUNT_ID, id);
    }

    @Override
    public void updateMoney(long passengerId, BigDecimal money) {
        executeUpdate(UPDATE_MONEY, money, passengerId);
    }

    @Override
    public void addMoney(long passengerId, BigDecimal money) {
        executeUpdate(ADD_MONEY, money,passengerId);
    }
}
