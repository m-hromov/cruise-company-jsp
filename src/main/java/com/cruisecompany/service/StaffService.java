package com.cruisecompany.service;

import com.cruisecompany.entity.Staff;
import com.cruisecompany.exception.ServiceException;

import java.util.List;

public interface StaffService {
    /**
     * Gets all staff.
     *
     * @return list of staff or empty list if no staff were found.
     * @throws ServiceException if something went wrong
     */
    List<Staff> getAll() throws ServiceException;
    /**
     * Adds a new employee.
     *
     * @param staff employee to be inserted
     * @throws ServiceException if something went wrong
     */
    void addStaff(Staff staff) throws ServiceException;
}
