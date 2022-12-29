package com.cruisecompany.service;

import com.cruisecompany.dao.db.DBProvider;
import com.cruisecompany.service.impl.*;

public class ServiceFactory {
    private CruiseService cruiseService;
    private ShipService shipService;
    private StationService stationService;
    private StaffService staffService;
    private PassengerService passengerService;
    private UserAccountService userAccountService;
    private TicketService ticketService;

    public ServiceFactory() {
        DBProvider dbProvider = new DBProvider();
        initServices(dbProvider);
    }

    public ServiceFactory(DBProvider dbProvider) {
        initServices(dbProvider);
    }
    private void initServices(DBProvider dbProvider) {
        cruiseService = new CruiseServiceImpl(dbProvider);
        passengerService = new PassengerServiceImpl(dbProvider);
        userAccountService = new UserAccountServiceImpl(dbProvider);
        ticketService = new TicketServiceImpl(dbProvider);
        shipService = new ShipServiceImpl(dbProvider);
        stationService = new StationServiceImpl(dbProvider);
        staffService = new StaffServiceImpl(dbProvider);
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

    public TicketService getOrderService() {
        return ticketService;
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
