package com.cruisecompany.db.dao;

import com.cruisecompany.entity.Order;
import com.cruisecompany.exception.DAOException;

import java.time.LocalDate;
import java.util.List;

public interface OrderDAO extends DAO<Order> {
    List<Order> getAllPassengerOrders(long id) throws DAOException;

    void updatePaidStatus(long orderId) throws DAOException;

    void block(long orderId) throws DAOException;

    void unblock(long orderId) throws DAOException;

    void confirm(long orderId) throws DAOException;
}
