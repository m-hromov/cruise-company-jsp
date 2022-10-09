package com.cruisecompany.db.dao;

import com.cruisecompany.entity.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderDAO extends DAO<Order> {
    List<Order> getAllPassengerOrders(long id);

    List<Order> getAllFiltred(LocalDate dateFrom, LocalDate dateTo, int durationFrom, int durationTo,
                              int limit, int offset);

    void updatePaidStatus(long orderId);

    void block(long orderId);

    void unblock(long orderId);

    void confirm(long orderId);
}
