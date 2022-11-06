package com.cruisecompany.db.dao.mapper.impl;

import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.db.dao.mapper.RowMapperFactory;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.entity.UserAccount;
import com.cruisecompany.exception.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.cruisecompany.db.Columns.*;

public class PassengerRowMapper implements RowMapper<Passenger> {
    @Override
    public Passenger map(Connection connection, ResultSet rs) throws SQLException, DAOException {
        UserAccount userAccount = RowMapperFactory.getInstance().getUserAccountRowMapper().map(connection, rs);

        Passenger passenger = new Passenger();
        passenger.setId(rs.getLong(PASSENGER_ID))
                .setFirstName(rs.getString(FIRST_NAME))
                .setLastName(rs.getString(LAST_NAME))
                .setPhone(rs.getString(PHONE))
                .setMoney(rs.getBigDecimal(MONEY))
                .setDocumentPath(rs.getString(DOCUMENT_PATH))
                .setUserAccount(userAccount);
        return passenger;
    }
}
