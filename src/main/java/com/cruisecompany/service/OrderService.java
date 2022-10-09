package com.cruisecompany.service;

import com.cruisecompany.entity.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    List<Order> getAllPassengerOrders(long id);
    List<Order> getAllFiltred(LocalDate dateFrom, LocalDate dateTo, int durationFrom, int durationTo,
                              int limit, int offset);
    BigDecimal pay(long orderId);
    void buy(long passengerId, long cruiseId);
    void block(long orderId);
    void unblock(long orderId);
    void confirm(long orderId);
}
