package com.cruisecompany.service;

import com.cruisecompany.db.dao.mapper.RowMapperFactory;
import com.cruisecompany.db.dao.mapper.impl.*;
import com.cruisecompany.service.impl.CruiseServiceImpl;

public class ServiceFactory {
    private static ServiceFactory INSTANCE;
    CruiseService cruiseService;
    private ServiceFactory() {
        cruiseService = new CruiseServiceImpl();
    }

    public static ServiceFactory getInstance() {
        if (INSTANCE == null) INSTANCE = new ServiceFactory();
        return INSTANCE;
    }

    public CruiseService getCruiseService() {
        return cruiseService;
    }
}
