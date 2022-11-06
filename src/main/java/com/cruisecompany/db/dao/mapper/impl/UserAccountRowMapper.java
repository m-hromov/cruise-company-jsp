package com.cruisecompany.db.dao.mapper.impl;

import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.entity.UserAccount;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.cruisecompany.db.Columns.*;

public class UserAccountRowMapper implements RowMapper<UserAccount> {
    @Override
    public UserAccount map(Connection connection, ResultSet rs) throws SQLException {
        UserAccount userAccount = new UserAccount();
        userAccount.setId(rs.getLong(USER_ACCOUNT_ID))
                .setEmail(rs.getString(EMAIL))
                .setPassword(rs.getString(PASSWORD))
                .setPasswordSalt(rs.getString(PASSWORD_SALT))
                .setRole(rs.getString(ROLE));
        return userAccount;
    }
}
