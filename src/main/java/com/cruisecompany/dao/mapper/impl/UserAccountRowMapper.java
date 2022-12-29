package com.cruisecompany.dao.mapper.impl;

import com.cruisecompany.dao.mapper.RowMapper;
import com.cruisecompany.entity.UserAccount;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.cruisecompany.dao.db.Columns.*;

public class UserAccountRowMapper implements RowMapper<UserAccount> {
    @Override
    public UserAccount map(Connection connection, ResultSet rs) throws SQLException {
        return UserAccount.builder()
                .id(rs.getLong(USER_ACCOUNT_ID))
                .email(rs.getString(EMAIL))
                .password(rs.getString(PASSWORD))
                .passwordSalt(rs.getString(PASSWORD_SALT))
                .role(rs.getString(ROLE))
                .build();
    }
}
