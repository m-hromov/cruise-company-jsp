package com.cruisecompany.db.dao.impl;

import com.cruisecompany.db.Columns;
import com.cruisecompany.db.Tables;
import com.cruisecompany.db.dao.AbstractDAO;
import com.cruisecompany.db.dao.UserAccountDAO;
import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.entity.UserAccount;
import com.cruisecompany.exception.DAOException;

import java.sql.Connection;
import java.util.Optional;

public class UserAccountDAOImpl extends AbstractDAO<UserAccount> implements UserAccountDAO {
    private final static String INSERT = "INSERT INTO " + Tables.USER_ACCOUNT + " (" +
            Columns.EMAIL + "," +
            Columns.PASSWORD + "," +
            Columns.PASSWORD_SALT + "," +
            Columns.ROLE + ") " +
            "VALUES (?,?,?,?::role)";
    private final static String GET_BY_EMAIL = "SELECT * FROM " + Tables.USER_ACCOUNT +
            " WHERE " + Columns.EMAIL + " = ?";
    private final static String CHECK_EMAIL_EXISTS = "SELECT COUNT(" + Columns.USER_ACCOUNT_ID + ") " +
            "FROM " + Tables.USER_ACCOUNT +
            " WHERE " + Columns.EMAIL + " = ?";
    private static final String UPDATE_EMAIL = "UPDATE " + Tables.USER_ACCOUNT + " SET " +
            Columns.EMAIL + " = ?, " +
            "WHERE " + Columns.USER_ACCOUNT_ID + " = ?";
    private static final String UPDATE = "UPDATE " + Tables.USER_ACCOUNT + " SET " +
            Columns.EMAIL + " = ?, " +
            Columns.PASSWORD + " = ?, " +
            Columns.PASSWORD_SALT + " = ?, " +
            "WHERE " + Columns.USER_ACCOUNT_ID + " = ?";

    public UserAccountDAOImpl(RowMapper<UserAccount> rowMapper, String table) {
        super(rowMapper, table);
    }

    @Override
    public long save(Connection connection, UserAccount userAccount) throws DAOException {
        return executeInsert(connection, INSERT, userAccount.getEmail(), userAccount.getPassword(),
                userAccount.getPasswordSalt(), userAccount.getRole());
    }

    @Override
    public void update(Connection connection, UserAccount userAccount) throws DAOException {
        executeUpdate(connection, UPDATE, userAccount.getEmail(), userAccount.getPassword(),
                userAccount.getPasswordSalt(), userAccount.getId());
    }

    @Override
    public Optional<UserAccount> getUserAccountByLogin(Connection connection, String email) throws DAOException {
        return executeSingleGetQuery(connection, GET_BY_EMAIL, email);
    }

    @Override
    public boolean checkIfEmailAlreadyExists(Connection connection, String email) throws DAOException {
        return executeSingleGetLongQuery(connection, CHECK_EMAIL_EXISTS, email) > 0;
    }

    @Override
    public void updateEmail(Connection connection, UserAccount userAccount) throws DAOException {
        executeUpdate(connection, UPDATE_EMAIL, userAccount.getEmail(), userAccount.getId());
    }
}
