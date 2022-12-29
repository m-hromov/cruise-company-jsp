package com.cruisecompany.dao.impl;

import com.cruisecompany.dao.db.Columns;
import com.cruisecompany.dao.PassengerDAO;
import com.cruisecompany.dao.db.Tables;
import com.cruisecompany.dao.AbstractDAO;
import com.cruisecompany.dao.mapper.RowMapper;
import com.cruisecompany.dto.PassengerDTO;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.exception.DAOException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Optional;

public class PassengerDAOImpl extends AbstractDAO<Passenger> implements PassengerDAO {
    private static final String SUBTRACT_MONEY = "UPDATE " + Tables.PASSENGER + " SET " + Columns.MONEY + " = " +
            Columns.MONEY + " - ? " + "WHERE " + Columns.PASSENGER_ID + " = ? RETURNING " + Columns.MONEY;
    private static final String ADD_MONEY = "UPDATE " + Tables.PASSENGER + " SET " + Columns.MONEY + " = " +
            Columns.MONEY + " + ? " + "WHERE " + Columns.PASSENGER_ID + " = ? RETURNING " + Columns.MONEY;
    private static final String GET_BY_USER_ACCOUNT_ID = "SELECT * FROM " + Tables.PASSENGER + " AS p" +
            " JOIN " + Tables.USER_ACCOUNT + " AS ua ON p." + Columns.USER_ACCOUNT_ID + " = ua." + Columns.USER_ACCOUNT_ID +
            " WHERE ua." + Columns.USER_ACCOUNT_ID + " = ?";
    private final static String INSERT = "INSERT INTO " + Tables.PASSENGER + " (" + Columns.FIRST_NAME + "," +
            Columns.LAST_NAME + "," + Columns.PHONE + "," + Columns.MONEY + "," +
            Columns.USER_ACCOUNT_ID + ") VALUES (?,?,?,?,?)";
    private static final String UPDATE_PROFILE = "UPDATE " + Tables.PASSENGER + " SET " + Columns.FIRST_NAME + " = ? ," +
            Columns.LAST_NAME + " = ? ," + Columns.PHONE + " = ? " +
            "WHERE " + Columns.PASSENGER_ID + " = ?";
    private static final String UPDATE_DOCUMENT = "UPDATE " + Tables.PASSENGER + " SET " + Columns.DOCUMENT_PATH + " = ? " +
            "WHERE " + Columns.PASSENGER_ID + " = ?";

    public PassengerDAOImpl(RowMapper<Passenger> rowMapper, String table) {
        super(rowMapper, table);
    }

    @Override
    public long save(Connection connection, Passenger passenger) throws DAOException {
        return executeInsert(connection, INSERT, passenger.getFirstName(), passenger.getLastName(),
                passenger.getPhone(), passenger.getMoney(), passenger.getUserAccount().getId());
    }

    @Override
    public void update(Connection connection, Passenger obj) throws DAOException {

    }

    @Override
    public Optional<Passenger> getByUserAccountId(Connection connection, long id) throws DAOException {
        return executeSingleGetQuery(connection, GET_BY_USER_ACCOUNT_ID, id);
    }

    @Override
    public BigDecimal subtractMoney(Connection connection, long passengerId, BigDecimal money) throws DAOException {
        return executeMoneyUpdate(connection, SUBTRACT_MONEY, money, passengerId);
    }

    @Override
    public void updateProfile(Connection connection, PassengerDTO passengerDTO) throws DAOException {
        executeUpdate(connection, UPDATE_PROFILE, passengerDTO.getFirstName(), passengerDTO.getLastName(),
                passengerDTO.getPhone(), passengerDTO.getEmail(), passengerDTO.getPassengerId());

    }

    @Override
    public void updateDocument(Connection connection, PassengerDTO passengerDTO) throws DAOException {
        executeUpdate(connection, UPDATE_DOCUMENT, passengerDTO.getDocumentPath(), passengerDTO.getPassengerId());
    }

    @Override
    public BigDecimal addMoney(Connection connection, long passengerId, BigDecimal money) throws DAOException {
        return executeMoneyUpdate(connection, ADD_MONEY, money, passengerId);
    }
}
