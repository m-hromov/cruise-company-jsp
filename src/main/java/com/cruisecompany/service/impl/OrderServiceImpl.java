package com.cruisecompany.service.impl;

import com.cruisecompany.db.dao.DAOFactory;
import com.cruisecompany.db.dao.OrderDAO;
import com.cruisecompany.db.dao.PassengerDAO;
import com.cruisecompany.entity.Cruise;
import com.cruisecompany.entity.Order;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.service.OrderService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final PassengerDAO passengerDAO;
    private final OrderDAO orderDAO;

    public OrderServiceImpl() {
        passengerDAO = DAOFactory.getInstance().getPassengerDAO();
        orderDAO = DAOFactory.getInstance().getOrderDAO();
    }

    @Override
    public BigDecimal pay(long orderId) {
        Order order = orderDAO.get(orderId).get();
        BigDecimal price = order.getCruise().getPrice();
        BigDecimal passengerMoney = order.getPassenger().getMoney();
        if (passengerMoney.compareTo(price) < 0) {
            return BigDecimal.valueOf(-1);
        }
        BigDecimal newMoney = passengerMoney.subtract(price);
        passengerDAO.updateMoney(order.getPassenger().getId(), newMoney);
        orderDAO.updatePaidStatus(orderId);
        return newMoney;
    }

    @Override
    public void buy(long passengerId, long cruiseId) {
        Order order = new Order();
        order.setCruise(new Cruise().setId(cruiseId))
                .setPassenger(new Passenger().setId(passengerId));
        orderDAO.save(order);
    }

    @Override
    public List<Order> getAllPassengerOrders(long id) {
        return orderDAO.getAllPassengerOrders(id);
    }

    @Override
    public List<Order> getAllFiltred(LocalDate dateFrom, LocalDate dateTo, int durationFrom, int durationTo, int limit, int offset) {

        return orderDAO.getAllFiltred(dateFrom,dateTo,durationFrom,durationTo,limit,offset);
    }

    @Override
    public void block(long orderId) {
        orderDAO.block(orderId);
    }

    @Override
    public void unblock(long orderId) {
        orderDAO.unblock(orderId);
    }

    @Override
    public void confirm(long orderId) {
        orderDAO.confirm(orderId);
    }
}
