package com.cruisecompany.service.impl;

import com.cruisecompany.db.DBProvider;
import com.cruisecompany.db.dao.DAOFactory;
import com.cruisecompany.db.dao.StaffDAO;
import com.cruisecompany.entity.Staff;
import com.cruisecompany.exception.DAOException;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.StaffService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class StaffServiceImpl implements StaffService {
    final static Logger logger = LogManager.getLogger(StaffServiceImpl.class);
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
            logger.error("Unable to get all staff!");
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
            logger.error("Unable to add staff!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }
}
