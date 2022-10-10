package com.cruisecompany.db.dao.mapper.impl;

import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.db.dao.mapper.RowMapperFactory;
import com.cruisecompany.entity.Ship;
import com.cruisecompany.entity.Staff;
import com.cruisecompany.exception.DAOException;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.cruisecompany.db.Columns.*;

public class StaffRowMapper implements RowMapper<Staff> {
    @Override
    public Staff map(ResultSet rs) throws DAOException, SQLException {
        Ship ship = RowMapperFactory.getInstance().getShipRowMapper().map(rs);

        Staff staff = new Staff();
        staff.setId(rs.getLong(STAFF_ID))
                .setFirstName(rs.getString(FIRST_NAME))
                .setLastName(LAST_NAME)
                .setSpeciality(SPECIALITY)
                .setShip(ship);
        return staff;
    }
}
