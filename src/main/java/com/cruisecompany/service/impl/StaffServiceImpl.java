package com.cruisecompany.service.impl;

import com.cruisecompany.dao.DAOFactory;
import com.cruisecompany.dao.StaffDAO;
import com.cruisecompany.dao.db.DBProvider;
import com.cruisecompany.entity.Staff;
import com.cruisecompany.exception.DAOException;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.StaffService;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.util.List;

@Log4j2
public class StaffServiceImpl implements StaffService {
    private final DBProvider dbProvider;
    private final StaffDAO staffDAO;

    public StaffServiceImpl(DBProvider dbProvider) {
        this.dbProvider = dbProvider;
        this.staffDAO = DAOFactory.getInstance().getStaffDAO();
    }

    @Override
    public List<Staff> getAll() throws ServiceException {
        Connection connection = dbProvider.getConnection();
        try {
            return staffDAO.getAll(connection);
        } catch (DAOException e) {
            log.error("Unable to get all staff!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }

    @Override
    public void addStaff(Staff staff) throws ServiceException {
        Connection connection = dbProvider.getConnection();
        try {
            staffDAO.save(connection, staff);
            dbProvider.commit(connection);
        } catch (DAOException e) {
            log.error("Unable to add staff!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }
}
