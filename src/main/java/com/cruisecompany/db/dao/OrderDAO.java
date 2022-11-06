package com.cruisecompany.db.dao;

import com.cruisecompany.entity.Ticket;
import com.cruisecompany.exception.DAOException;

import java.sql.Connection;
import java.util.List;

public interface OrderDAO extends DAO<Ticket> {
    /**
     * Gets a list of passenger's orders
     *
     * @param connection Connection, on which a query will be executed
     * @param id         ID of a passenger
     * @return List of Orders of a certain passenger
     * @throws DAOException if something went wrong
     */
    List<Ticket> getAllPassengerOrders(Connection connection, long id) throws DAOException;

    /**
     * Sets a paid status as true on a certain order.
     *
     * @param connection Connection, on which a query will be executed
     * @param orderId    ID of the order
     * @throws DAOException if something went wrong
     */
    void updatePaidStatus(Connection connection, long orderId) throws DAOException;

    /**
     * Blocks a certain order.
     *
     * @param connection Connection, on which a query will be executed
     * @param orderId    ID of the order
     * @throws DAOException if something went wrong
     */
    void block(Connection connection, long orderId) throws DAOException;

    /**
     * Unblocks a certain order.
     *
     * @param connection Connection, on which a query will be executed
     * @param orderId    ID of the order
     * @throws DAOException if something went wrong
     */
    void unblock(Connection connection, long orderId) throws DAOException;

    /**
     * Confirms a certain order.
     *
     * @param connection Connection, on which a query will be executed
     * @param orderId    ID of the order
     * @throws DAOException if something went wrong
     */
    void confirm(Connection connection, long orderId) throws DAOException;
}
