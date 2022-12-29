package com.cruisecompany.dao.mapper.impl;

import com.cruisecompany.dao.mapper.RowMapper;
import com.cruisecompany.dao.mapper.RowMapperFactory;
import com.cruisecompany.entity.Ship;
import com.cruisecompany.entity.Staff;
import com.cruisecompany.exception.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.cruisecompany.dao.db.Columns.*;

public class StaffRowMapper implements RowMapper<Staff> {
    @Override
    public Staff map(Connection connection, ResultSet rs) throws DAOException, SQLException {
        Ship ship = RowMapperFactory.getInstance().getShipRowMapper().map(connection, rs);

        return Staff.builder()
                .id(rs.getLong(STAFF_ID))
                .firstName(rs.getString(FIRST_NAME))
                .lastName(LAST_NAME)
                .speciality(SPECIALITY)
                .ship(ship)
                .build();

    }
}
