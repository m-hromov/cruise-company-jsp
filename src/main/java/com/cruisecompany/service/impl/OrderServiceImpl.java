package com.cruisecompany.service.impl;

import com.cruisecompany.db.dao.DAOFactory;
import com.cruisecompany.db.dao.OrderDAO;
import com.cruisecompany.db.dao.PassengerDAO;
import com.cruisecompany.entity.Cruise;
import com.cruisecompany.entity.Order;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.exception.DAOException;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    final static Logger logger = LogManager.getLogger(OrderServiceImpl.class);
    private final PassengerDAO passengerDAO;
    private final OrderDAO orderDAO;

    public OrderServiceImpl() {
        passengerDAO = DAOFactory.getInstance().getPassengerDAO();
        orderDAO = DAOFactory.getInstance().getOrderDAO();
    }

    @Override
    public BigDecimal pay(long orderId) throws ServiceException {
        try {
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
        } catch (DAOException e) {
            logger.error("Unable to pay for cruise!");
            throw new ServiceException(e.getMessage(),e);
        }
    }

    @Override
    public void buy(long passengerId, long cruiseId) throws ServiceException {
        try {
            Order order = new Order();
            order.setCruise(new Cruise().setId(cruiseId))
                    .setPassenger(new Passenger().setId(passengerId));
            orderDAO.save(order);
        } catch (DAOException e) {
            logger.error("Unable to buy cruise!");
            throw new ServiceException(e.getMessage(),e);
        }
    }

    @Override
    public List<Order> getAllPassengerOrders(long id) throws ServiceException {
        try {
            return orderDAO.getAllPassengerOrders(id);
        } catch (DAOException e) {
            logger.error("Unable to get all passengerOrders!");
            throw new ServiceException(e.getMessage(),e);
        }
    }

    @Override
    public void block(long orderId) throws ServiceException {
        try {
            orderDAO.block(orderId);
        } catch (DAOException e) {
            logger.error("Unable to block an order!");
            throw new ServiceException(e.getMessage(),e);
        }
    }

    @Override
    public void unblock(long orderId) throws ServiceException {
        try {
            orderDAO.unblock(orderId);
        } catch (DAOException e) {
            logger.error("Unable to unblock an order!");
            throw new ServiceException(e.getMessage(),e);
        }
    }

    @Override
    public void confirm(long orderId) throws ServiceException {
        try {
            orderDAO.confirm(orderId);
        } catch (DAOException e) {
            logger.error("Unable to confirm an order!");
            throw new ServiceException(e.getMessage(),e);
        }
    }
}
