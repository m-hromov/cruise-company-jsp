package com.cruisecompany.dao.impl;

import com.cruisecompany.dao.db.Columns;
import com.cruisecompany.dao.db.Tables;
import com.cruisecompany.dao.AbstractDAO;
import com.cruisecompany.dao.StaffDAO;
import com.cruisecompany.dao.mapper.RowMapper;
import com.cruisecompany.entity.Staff;
import com.cruisecompany.exception.DAOException;

import java.sql.Connection;

public class StaffDAOImpl extends AbstractDAO<Staff> implements StaffDAO {
    private static final String INSERT = "INSERT INTO " + Tables.STAFF + " (" + Columns.FIRST_NAME + ", " +
            Columns.LAST_NAME + "," + Columns.PHONE + "," + Columns.SPECIALITY + "," +
            Columns.SHIP_ID + ") VALUES (?, ?, ?, ?, ?)";

    public StaffDAOImpl(RowMapper<Staff> rowMapper, String table) {
        super(rowMapper, table);
    }

    @Override
    public long save(Connection connection, Staff staff) throws DAOException {
        return executeInsert(connection, INSERT, staff.getFirstName(),
                staff.getLastName(), staff.getPhone(), staff.getSpeciality(), staff.getShip().getId());
    }

    @Override
    public void update(Connection connection, Staff obj) throws DAOException {

    }
}
