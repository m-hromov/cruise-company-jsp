package com.cruisecompany.db.dao.impl;

import com.cruisecompany.db.dao.AbstractDAO;
import com.cruisecompany.db.dao.StaffDAO;
import com.cruisecompany.db.entity.Staff;
import com.cruisecompany.db.dao.mapper.RowMapper;

public class StaffDAOImpl extends AbstractDAO<Staff> implements StaffDAO {
    public StaffDAOImpl(RowMapper<Staff> rowMapper, String table) {
        super(rowMapper, table);
    }

    @Override
    public long save(Staff obj) {
        return 0;
    }

    @Override
    public void update(Staff obj) {

    }
}
