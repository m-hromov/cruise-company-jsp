package com.cruisecompany.db.dao;

import com.cruisecompany.entity.Order;
import com.cruisecompany.exception.DAOException;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public interface OrderDAO extends DAO<Order> {
    List<Order> getAllPassengerOrders(Connection connection, long id) throws DAOException;

    void updatePaidStatus(Connection connection, long orderId) throws DAOException;

    void block(Connection connection, long orderId) throws DAOException;

    void unblock(Connection connection, long orderId) throws DAOException;

    void confirm(Connection connection, long orderId) throws DAOException;
}
