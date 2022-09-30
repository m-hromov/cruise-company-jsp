package com.cruisecompany.service;

import com.cruisecompany.db.dao.mapper.RowMapperFactory;
import com.cruisecompany.db.dao.mapper.impl.*;
import com.cruisecompany.service.impl.*;

public class ServiceFactory {
    private static ServiceFactory INSTANCE;
    CruiseService cruiseService;
    ShipService shipService;
    StationService stationService;
    StaffService staffService;
    PassengerService passengerService;
    UserAccountService userAccountService;
    OrderService orderService;
    private ServiceFactory() {
        cruiseService = new CruiseServiceImpl();
        passengerService = new PassengerServiceImpl();
        userAccountService = new UserAccountServiceImpl();
        orderService = new OrderServiceImpl();
        shipService = new ShipServiceImpl();
        stationService = new StationServiceImpl();
        staffService = new StaffServiceImpl();
    }

    public static ServiceFactory getInstance() {
        if (INSTANCE == null) INSTANCE = new ServiceFactory();
        return INSTANCE;
    }

    public CruiseService getCruiseService() {
        return cruiseService;
    }

    public PassengerService getPassengerService() {
        return passengerService;
    }

    public UserAccountService getUserAccountService() {
        return userAccountService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public ShipService getShipService() {
        return shipService;
    }

    public StationService getStationService() {
        return stationService;
    }

    public StaffService getStaffService() {
        return staffService;
    }
}
