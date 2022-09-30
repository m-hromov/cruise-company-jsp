package com.cruisecompany.db.dao;

import com.cruisecompany.entity.Order;

import java.util.List;

public interface OrderDAO extends DAO<Order> {
    List<Order> getAllPassengerOrders(long id);
    void updatePaidStatus(long orderId);
    void block(long orderId);
    void unblock(long orderId);
    void confirm(long orderId);
}
