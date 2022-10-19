package com.cruisecompany.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class ServiceFactoryTest {
    ServiceFactory serviceFactory = new ServiceFactory(null);


    @Test
    void testGetCruiseService() {
        assertNotNull(serviceFactory.getCruiseService());
        assertSame(serviceFactory.getCruiseService(), serviceFactory.getCruiseService());
    }

    @Test
    void testGetPassengerService() {
        assertNotNull(serviceFactory.getPassengerService());
        assertSame(serviceFactory.getPassengerService(), serviceFactory.getPassengerService());
    }

    @Test
    void testGetUserAccountService() {
        assertNotNull(serviceFactory.getUserAccountService());
        assertSame(serviceFactory.getUserAccountService(), serviceFactory.getUserAccountService());
    }

    @Test
    void testGetOrderService() {
        assertNotNull(serviceFactory.getOrderService());
        assertSame(serviceFactory.getOrderService(), serviceFactory.getOrderService());
    }

    @Test
    void testGetShipService() {
        assertNotNull(serviceFactory.getShipService());
        assertSame(serviceFactory.getShipService(), serviceFactory.getShipService());
    }

    @Test
    void testGetStationService() {
        assertNotNull(serviceFactory.getStationService());
        assertSame(serviceFactory.getStationService(), serviceFactory.getStationService());
    }

    @Test
    void testGetStaffService() {
        assertNotNull(serviceFactory.getStaffService());
        assertSame(serviceFactory.getStaffService(), serviceFactory.getStaffService());
    }
}