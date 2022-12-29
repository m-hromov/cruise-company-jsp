package com.cruisecompany.dao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class DAOFactoryTest {
    DAOFactory daoFactory = DAOFactory.getInstance();
    @Test
    void testGetInstance() {
        assertNotNull(daoFactory);
        assertSame(daoFactory,DAOFactory.getInstance());
    }

    @Test
    void testGetCruiseDAO() {
        assertNotNull(daoFactory.getCruiseDAO());
        assertSame(daoFactory.getCruiseDAO(), daoFactory.getCruiseDAO());
    }

    @Test
    void testGetPassengerDAO() {
        assertNotNull(daoFactory.getPassengerDAO());
        assertSame(daoFactory.getPassengerDAO(), daoFactory.getPassengerDAO());
    }

    @Test
    void testGetRouteDAO() {
        assertNotNull(daoFactory.getRouteDAO());
        assertSame(daoFactory.getRouteDAO(), daoFactory.getRouteDAO());
    }

    @Test
    void testGetShipDAO() {
        assertNotNull(daoFactory.getShipDAO());
        assertSame(daoFactory.getShipDAO(), daoFactory.getShipDAO());
    }

    @Test
    void testGetStaffDAO() {
        assertNotNull(daoFactory.getStaffDAO());
        assertSame(daoFactory.getStaffDAO(), daoFactory.getStaffDAO());
    }

    @Test
    void testGetStationDAO() {
        assertNotNull(daoFactory.getStationDAO());
        assertSame(daoFactory.getStationDAO(), daoFactory.getStationDAO());
    }

    @Test
    void testGetUserAccountDAO() {
        assertNotNull(daoFactory.getUserAccountDAO());
        assertSame(daoFactory.getUserAccountDAO(), daoFactory.getUserAccountDAO());
    }

    @Test
    void testGetOrderDAO() {
        assertNotNull(daoFactory.getTicketDAO());
        assertSame(daoFactory.getTicketDAO(), daoFactory.getTicketDAO());
    }
}