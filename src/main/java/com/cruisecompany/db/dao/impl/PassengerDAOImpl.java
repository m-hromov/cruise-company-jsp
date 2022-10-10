package com.cruisecompany.db.dao.impl;

import com.cruisecompany.db.Columns;
import com.cruisecompany.db.Tables;
import com.cruisecompany.db.dao.AbstractDAO;
import com.cruisecompany.db.dao.PassengerDAO;
import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.exception.DAOException;

import java.math.BigDecimal;
import java.util.Optional;

public class PassengerDAOImpl extends AbstractDAO<Passenger> implements PassengerDAO {
    private static final String UPDATE_MONEY = "UPDATE " + Tables.PASSENGER + " SET " + Columns.MONEY + " = ? " +
            "WHERE " + Columns.PASSENGER_ID + " = ?";
    private static final String ADD_MONEY = "UPDATE " + Tables.PASSENGER + " SET " + Columns.MONEY + " = " +
            Columns.MONEY + " + ? " + "WHERE " + Columns.PASSENGER_ID + " = ?";
    private static final String GET_BY_USER_ACCOUNT_ID = "SELECT * FROM " + Tables.PASSENGER + " AS p" +
            " JOIN " + Tables.USER_ACCOUNT + " AS ua ON p." + Columns.USER_ACCOUNT_ID + " = ua." + Columns.USER_ACCOUNT_ID +
            " WHERE ua." + Columns.USER_ACCOUNT_ID + " = ?";
    private final static String INSERT = "INSERT INTO " + Tables.PASSENGER + " (" + Columns.FIRST_NAME + "," +
            Columns.LAST_NAME + "," + Columns.PHONE + "," + Columns.EMAIL + "," + Columns.MONEY + "," +
            Columns.USER_ACCOUNT_ID + ") VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_PROFILE = "UPDATE " + Tables.PASSENGER + " SET " + Columns.FIRST_NAME + " = ? ," +
            Columns.LAST_NAME + " = ? ," + Columns.PHONE + " = ? ," + Columns.EMAIL + " = ? " +
            "WHERE " + Columns.PASSENGER_ID + " = ?";
    private static final String UPDATE_DOCUMENT = "UPDATE " + Tables.PASSENGER + " SET " + Columns.DOCUMENT_PATH + " = ? " +
            "WHERE " + Columns.PASSENGER_ID + " = ?";

    public PassengerDAOImpl(RowMapper<Passenger> rowMapper, String table) {
        super(rowMapper, table);
    }

    @Override
    public long save(Passenger passenger) throws DAOException {
        return executeInsert(INSERT, passenger.getFirstName(), passenger.getLastName(),
                passenger.getPhone(), passenger.getEmail(), passenger.getMoney(), passenger.getUserAccount().getId());
    }

    @Override
    public void update(Passenger obj) throws DAOException {

    }

    @Override
    public Optional<Passenger> getByUserAccountId(long id) throws DAOException {
        return executeSingleGetQuery(GET_BY_USER_ACCOUNT_ID, id);
    }

    @Override
    public void updateMoney(long passengerId, BigDecimal money) throws DAOException {
        executeUpdate(UPDATE_MONEY, money, passengerId);
    }

    @Override
    public void updateProfile(Passenger passenger) throws DAOException {

        executeUpdate(UPDATE_PROFILE, passenger.getFirstName(), passenger.getLastName(),
                passenger.getPhone(), passenger.getEmail(), passenger.getId());

    }

    @Override
    public void updateDocument(Passenger passenger) throws DAOException {
        executeUpdate(UPDATE_DOCUMENT, passenger.getDocumentPath(), passenger.getId());
    }

    @Override
    public void addMoney(long passengerId, BigDecimal money) throws DAOException {
        executeUpdate(ADD_MONEY, money, passengerId);
    }
}
