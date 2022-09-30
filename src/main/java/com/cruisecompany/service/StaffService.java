package com.cruisecompany.service;

import com.cruisecompany.entity.Staff;

import java.util.List;

public interface StaffService {
    List<Staff> getAll();
    void addStaff(Staff staff);
}
