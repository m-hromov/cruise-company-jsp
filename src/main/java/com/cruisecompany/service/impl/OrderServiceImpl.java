package com.cruisecompany.service.impl;

import com.cruisecompany.db.DBProvider;
import com.cruisecompany.db.dao.DAOFactory;
import com.cruisecompany.db.dao.OrderDAO;
import com.cruisecompany.db.dao.PassengerDAO;
import com.cruisecompany.dto.PassengerDTO;
import com.cruisecompany.entity.Cruise;
import com.cruisecompany.entity.Order;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.exception.DAOException;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    final static Logger logger = LogManager.getLogger(OrderServiceImpl.class);
    private final DBProvider dbProvider;
    private final PassengerDAO passengerDAO;
    private final OrderDAO orderDAO;

    public OrderServiceImpl(DBProvider dbProvider) {
        this.dbProvider = dbProvider;
        passengerDAO = DAOFactory.getInstance().getPassengerDAO();
        orderDAO = DAOFactory.getInstance().getOrderDAO();
    }

    @Override
    public BigDecimal pay(long orderId) throws ServiceException {
        Connection connection = dbProvider.getConnection();
        try {
            Order order = orderDAO.get(connection, orderId).get();
            BigDecimal newMoney = passengerDAO.subtractMoney(connection,
                    order.getPassenger().getId(),
                    order.getCruise().getPrice());
            if (newMoney.compareTo(BigDecimal.valueOf(0)) < 0) {
                dbProvider.rollback(connection);
                throw new ServiceException("Not enough money!");
            }
            orderDAO.updatePaidStatus(connection, orderId);
            dbProvider.commit(connection);
            return newMoney;
        } catch (DAOException e) {
            dbProvider.rollback(connection);
            logger.error("Unable to pay for cruise!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }

    @Override
    public long buy(PassengerDTO passengerDTO, long cruiseId) throws ServiceException {
        Connection connection = dbProvider.getConnection();
        try {
            Order order = new Order();
            order.setCruise(new Cruise().setId(cruiseId))
                    .setPassenger(new Passenger().setId(passengerDTO.getPassengerId()));
            long orderId = orderDAO.save(connection, order);
            dbProvider.commit(connection);
            return orderId;
        } catch (DAOException e) {
            logger.error("Unable to buy cruise!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }

    @Override
    public List<Order> getAllPassengerOrders(long id) throws ServiceException {
        Connection connection = dbProvider.getConnection();
        try {
            return orderDAO.getAllPassengerOrders(connection, id);
        } catch (DAOException e) {
            logger.error("Unable to get all passengerOrders!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }

    @Override
    public void block(long orderId) throws ServiceException {
        Connection connection = dbProvider.getConnection();
        try {
            orderDAO.block(connection, orderId);
            dbProvider.commit(connection);
        } catch (DAOException e) {
            logger.error("Unable to block an order!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }

    @Override
    public void unblock(long orderId) throws ServiceException {
        Connection connection = dbProvider.getConnection();
        try {
            orderDAO.unblock(connection, orderId);
            dbProvider.commit(connection);
        } catch (DAOException e) {
            logger.error("Unable to unblock an order!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }

    @Override
    public void confirm(long orderId) throws ServiceException {
        Connection connection = dbProvider.getConnection();
        try {
            orderDAO.confirm(connection, orderId);
            dbProvider.commit(connection);
        } catch (DAOException e) {
            logger.error("Unable to confirm an order!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }
}
