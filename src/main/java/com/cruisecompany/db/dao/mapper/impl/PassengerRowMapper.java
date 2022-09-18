package com.cruisecompany.db.dao.mapper.impl;

import com.cruisecompany.db.dao.UserAccountDAO;
import com.cruisecompany.db.dao.impl.UserAccountDAOImpl;
import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.db.dao.mapper.RowMapperFactory;
import com.cruisecompany.db.entity.Passenger;
import com.cruisecompany.db.entity.UserAccount;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.cruisecompany.db.dao.mapper.Tables.USER_ACCOUNT;
import static com.cruisecompany.db.dao.mapper.impl.Columns.*;

public class PassengerRowMapper implements RowMapper<Passenger> {
    @Override
    public Passenger map(ResultSet rs) {
        try {
            RowMapper<UserAccount> userAccountRowMapper = RowMapperFactory.getInstance().getUserAccountRowMapper();
            UserAccountDAO userAccountDAO = new UserAccountDAOImpl(userAccountRowMapper, USER_ACCOUNT);
            UserAccount userAccount = userAccountDAO.get(rs.getLong(ID_USER_ACCOUNT)).orElse(new UserAccount());

            Passenger passenger = new Passenger();
            passenger.setId(rs.getLong(ID))
                    .setFirstName(rs.getString(FIRST_NAME))
                    .setLastName(rs.getString(LAST_NAME))
                    .setPhone(rs.getString(PHONE))
                    .setEmail(rs.getString(EMAIL))
                    .setMoney(rs.getInt(MONEY))
                    .setDocumentPath(rs.getString(DOCUMENT_PATH))
                    .setUserAccount(userAccount);
            return passenger;
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
