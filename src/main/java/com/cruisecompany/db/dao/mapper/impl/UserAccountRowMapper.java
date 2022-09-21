package com.cruisecompany.db.dao.mapper.impl;

import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.db.entity.UserAccount;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.cruisecompany.db.Columns.*;

public class UserAccountRowMapper implements RowMapper<UserAccount> {
    @Override
    public UserAccount map(ResultSet rs) {
        try {
            UserAccount userAccount = new UserAccount();
            userAccount.setId(rs.getLong(USER_ACCOUNT_ID))
                    .setLogin(rs.getString(LOGIN))
                    .setPassword(rs.getString(PASSWORD))
                    .setRole(rs.getString(ROLE));
            return userAccount;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
