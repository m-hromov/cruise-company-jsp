package com.cruisecompany.db.dao.impl;

import com.cruisecompany.db.Columns;
import com.cruisecompany.db.Tables;
import com.cruisecompany.db.dao.AbstractDAO;
import com.cruisecompany.db.dao.UserAccountDAO;
import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.entity.UserAccount;
import com.cruisecompany.exception.DAOException;

import java.util.Optional;

public class UserAccountDAOImpl extends AbstractDAO<UserAccount> implements UserAccountDAO {
    private final static String INSERT = "INSERT INTO " + Tables.USER_ACCOUNT + " (" + Columns.LOGIN + "," +
            Columns.PASSWORD + "," + Columns.ROLE + ") VALUES (?,?,?::role)";
    private final static String GET_BY_LOGIN = "SELECT * FROM " + Tables.USER_ACCOUNT + " WHERE " + Columns.LOGIN + " = ?";
    private static final String UPDATE_PASSWORD = "UPDATE " + Tables.USER_ACCOUNT + " SET " + Columns.PASSWORD + " = ? " +
            "WHERE " + Columns.USER_ACCOUNT_ID + " = ?";

    public UserAccountDAOImpl(RowMapper<UserAccount> rowMapper, String table) {
        super(rowMapper, table);
    }

    @Override
    public long save(UserAccount userAccount) throws DAOException {
        return executeInsert(INSERT, userAccount.getLogin(), userAccount.getPassword(), userAccount.getRole());
    }

    @Override
    public void update(UserAccount obj) throws DAOException {

    }

    @Override
    public Optional<UserAccount> getUserAccountByLogin(String login) throws DAOException {
        return executeSingleGetQuery(GET_BY_LOGIN, login);
    }

    @Override
    public void updatePassword(UserAccount userAccount) throws DAOException {
        executeUpdate(UPDATE_PASSWORD, userAccount.getPassword(), userAccount.getId());
    }
}
