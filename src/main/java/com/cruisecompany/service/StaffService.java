package com.cruisecompany.service;

import com.cruisecompany.entity.Staff;
import com.cruisecompany.exception.ServiceException;

import java.util.List;

public interface StaffService {
    List<Staff> getAll() throws ServiceException;
    void addStaff(Staff staff) throws ServiceException;
}
