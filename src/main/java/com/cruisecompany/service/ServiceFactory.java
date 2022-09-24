package com.cruisecompany.service;

import com.cruisecompany.db.dao.mapper.RowMapperFactory;
import com.cruisecompany.db.dao.mapper.impl.*;
import com.cruisecompany.service.impl.CruiseServiceImpl;
import com.cruisecompany.service.impl.PassengerServiceImpl;
import com.cruisecompany.service.impl.UserAccountServiceImpl;

public class ServiceFactory {
    private static ServiceFactory INSTANCE;
    CruiseService cruiseService;
    PassengerService passengerService;
    UserAccountService userAccountService;
    private ServiceFactory() {
        cruiseService = new CruiseServiceImpl();
        passengerService = new PassengerServiceImpl();
        userAccountService = new UserAccountServiceImpl();
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
}
