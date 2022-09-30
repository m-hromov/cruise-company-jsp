package com.cruisecompany.db.dao.impl;

import com.cruisecompany.db.Columns;
import com.cruisecompany.db.Tables;
import com.cruisecompany.db.dao.AbstractDAO;
import com.cruisecompany.db.dao.StaffDAO;
import com.cruisecompany.entity.Staff;
import com.cruisecompany.db.dao.mapper.RowMapper;

public class StaffDAOImpl extends AbstractDAO<Staff> implements StaffDAO {
    public StaffDAOImpl(RowMapper<Staff> rowMapper, String table) {
        super(rowMapper, table);
    }
    private static final String INSERT = "INSERT INTO "+ Tables.STAFF + " (" + Columns.FIRST_NAME + ", " +
            Columns.LAST_NAME + "," + Columns.PHONE + "," + Columns.SPECIALITY + "," +
            Columns.SHIP_ID + ") VALUES (?, ?, ?, ?, ?)";
    @Override
    public long save(Staff staff) {
        return executeInsert(INSERT, staff.getFirstName(),
                staff.getLastName(), staff.getPhone(), staff.getSpeciality(), staff.getShip().getId());
    }

    @Override
    public void update(Staff obj) {

    }
}
