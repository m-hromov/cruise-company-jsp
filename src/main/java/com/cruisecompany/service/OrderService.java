package com.cruisecompany.service;

import com.cruisecompany.entity.Order;
import com.cruisecompany.exception.ServiceException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    List<Order> getAllPassengerOrders(long id) throws ServiceException;
    BigDecimal pay(long orderId) throws ServiceException;
    void buy(long passengerId, long cruiseId) throws ServiceException;
    void block(long orderId) throws ServiceException;
    void unblock(long orderId) throws ServiceException;
    void confirm(long orderId) throws ServiceException;
}
