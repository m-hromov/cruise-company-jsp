package com.cruisecompany.service.impl;

import com.cruisecompany.db.dao.DAOFactory;
import com.cruisecompany.db.dao.StaffDAO;
import com.cruisecompany.entity.Staff;
import com.cruisecompany.service.StaffService;

import java.util.List;

public class StaffServiceImpl implements StaffService {
    private final StaffDAO staffDAO;

    public StaffServiceImpl() {
        this.staffDAO = DAOFactory.getInstance().getStaffDAO();
    }
    @Override
    public List<Staff> getAll() {
        return staffDAO.getAll();
    }

    @Override
    public void addStaff(Staff staff) {
        staffDAO.save(staff);
    }
}
