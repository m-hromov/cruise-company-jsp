package com.cruisecompany.db.dao.impl;

import com.cruisecompany.db.Columns;
import com.cruisecompany.db.Tables;
import com.cruisecompany.db.dao.AbstractDAO;
import com.cruisecompany.db.dao.UserAccountDAO;
import com.cruisecompany.entity.UserAccount;
import com.cruisecompany.db.dao.mapper.RowMapper;

import java.util.Optional;

public class UserAccountDAOImpl extends AbstractDAO<UserAccount> implements UserAccountDAO {
    public UserAccountDAOImpl(RowMapper<UserAccount> rowMapper, String table) {
        super(rowMapper, table);
    }

    private final static String INSERT = "INSERT INTO " + Tables.USER_ACCOUNT + " (" + Columns.LOGIN + "," +
            Columns.PASSWORD + "," + Columns.ROLE + ") VALUES (?,?,?::role)";
    @Override
    public long save(UserAccount userAccount) {
        return executeInsert(INSERT,userAccount.getLogin(),userAccount.getPassword(),userAccount.getRole());
    }

    @Override
    public void update(UserAccount obj) {

    }
    private final static String GET_BY_LOGIN = "SELECT * FROM "+ Tables.USER_ACCOUNT+" WHERE "+ Columns.LOGIN + " = ?";
    @Override
    public Optional<UserAccount> getUserAccountByLogin(String login) {
        return executeSingleGetQuery(GET_BY_LOGIN,login);
    }
}
