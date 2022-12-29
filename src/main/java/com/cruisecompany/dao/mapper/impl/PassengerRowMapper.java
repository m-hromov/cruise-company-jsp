package com.cruisecompany.dao.mapper.impl;

import com.cruisecompany.dao.mapper.RowMapper;
import com.cruisecompany.dao.mapper.RowMapperFactory;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.entity.UserAccount;
import com.cruisecompany.exception.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.cruisecompany.dao.db.Columns.*;

public class PassengerRowMapper implements RowMapper<Passenger> {
    @Override
    public Passenger map(Connection connection, ResultSet rs) throws SQLException, DAOException {
        UserAccount userAccount = RowMapperFactory.getInstance().getUserAccountRowMapper().map(connection, rs);

        return Passenger.builder()
                .id(rs.getLong(PASSENGER_ID))
                .firstName(rs.getString(FIRST_NAME))
                .lastName(rs.getString(LAST_NAME))
                .phone(rs.getString(PHONE))
                .money(rs.getBigDecimal(MONEY))
                .documentPath(rs.getString(DOCUMENT_PATH))
                .userAccount(userAccount)
                .build();
    }
}
