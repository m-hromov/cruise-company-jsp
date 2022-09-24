package com.cruisecompany.db.dao.impl;

import com.cruisecompany.db.Columns;
import com.cruisecompany.db.Tables;
import com.cruisecompany.db.dao.AbstractDAO;
import com.cruisecompany.db.dao.UserAccountDAO;
import com.cruisecompany.db.entity.UserAccount;
import com.cruisecompany.db.dao.mapper.RowMapper;

import java.util.Optional;

public class UserAccountDAOImpl extends AbstractDAO<UserAccount> implements UserAccountDAO {
    public UserAccountDAOImpl(RowMapper<UserAccount> rowMapper, String table) {
        super(rowMapper, table);
    }

    @Override
    public long save(UserAccount obj) {
        return 0;
    }

    @Override
    public void update(UserAccount obj) {

    }
    private final String GET_BY_LOGIN = "SELECT * FROM "+ Tables.USER_ACCOUNT+" WHERE "+ Columns.LOGIN + " = ?";
    @Override
    public Optional<UserAccount> getUserAccountByLogin(String login) {
        return executeSingleGetQuery(GET_BY_LOGIN,login);
    }
}
