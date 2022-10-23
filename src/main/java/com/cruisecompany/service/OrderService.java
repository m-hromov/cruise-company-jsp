package com.cruisecompany.service;

import com.cruisecompany.entity.Order;
import com.cruisecompany.exception.DAOException;
import com.cruisecompany.exception.ServiceException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    /**
     * Gets a list of passenger's orders
     * @param id ID of a passenger
     * @return List of Orders of a certain passenger
     * @throws ServiceException if something went wrong
     */
    List<Order> getAllPassengerOrders(long id) throws ServiceException;
    /**
     * Sets a paid status as true on a certain order and subtracts passenger's money.
     * @param orderId ID of the order
     * @throws ServiceException if something went wrong
     */
    BigDecimal pay(long orderId) throws ServiceException;

    /**
     * Creates a new order.
     * @param passengerId passenger's ID
     * @param cruiseId cruise's ID
     * @throws ServiceException if the passenger has already bought it or something went wrong.
     */
    void buy(long passengerId, long cruiseId) throws ServiceException;
    /**
     * Blocks a certain order.
     * @param orderId ID of the order
     * @throws ServiceException if something went wrong
     */
    void block(long orderId) throws ServiceException;
    /**
     * Unblocks a certain order.
     * @param orderId ID of the order
     * @throws ServiceException if something went wrong
     */
    void unblock(long orderId) throws ServiceException;
    /**
     * Confirms a certain order.
     * @param orderId ID of the order
     * @throws ServiceException if something went wrong
     */
    void confirm(long orderId) throws ServiceException;
}
