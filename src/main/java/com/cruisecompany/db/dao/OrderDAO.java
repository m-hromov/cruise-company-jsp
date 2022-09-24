package com.cruisecompany.db.dao;

import com.cruisecompany.db.entity.Order;

import java.util.List;

public interface OrderDAO extends DAO<Order> {
    List<Order> getAllPassengerOrders(long id);
}
