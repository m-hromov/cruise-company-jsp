package com.cruisecompany.service.impl;

import com.cruisecompany.db.DBProvider;
import com.cruisecompany.db.dao.DAOFactory;
import com.cruisecompany.db.dao.OrderDAO;
import com.cruisecompany.db.dao.PassengerDAO;
import com.cruisecompany.db.dto.DTOMapper;
import com.cruisecompany.db.dto.PassengerOrderDTO;
import com.cruisecompany.entity.Order;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.exception.DAOException;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.PassengerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PassengerServiceImpl implements PassengerService {
    final static Logger logger = LogManager.getLogger(PassengerServiceImpl.class);
    private final DBProvider dbProvider;
    private final PassengerDAO passengerDAO;
    private final OrderDAO orderDAO;

    public PassengerServiceImpl(DBProvider dbProvider) {
        this.dbProvider = dbProvider;
        passengerDAO = DAOFactory.getInstance().getPassengerDAO();
        orderDAO = DAOFactory.getInstance().getOrderDAO();
    }

    @Override
    public List<PassengerOrderDTO> getAllPassengerOrderDTOList() throws ServiceException {
        Connection connection = dbProvider.getConnection();
        try {
            List<Order> orderList = orderDAO.getAll(connection);
            return orderList.stream().map(DTOMapper::toPassengerOrderDTO).collect(Collectors.toList());
        } catch (DAOException e) {
            logger.error("Unable to get all PassengerOrderDTOList!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }

    @Override
    public Passenger getPassengerByAccountId(long id) throws ServiceException {
        Connection connection = dbProvider.getConnection();
        try {
            Optional<Passenger> optional = passengerDAO.getByUserAccountId(connection, id);
            return optional.orElseGet(Passenger::new);
        } catch (DAOException e) {
            logger.error("Unable to get passenger by account id!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }

    @Override
    public void addMoney(long passengerId, BigDecimal money) throws ServiceException {
        Connection connection = dbProvider.getConnection();
        try {
            passengerDAO.addMoney(connection, passengerId, money);
            dbProvider.commit(connection);
        } catch (DAOException e) {
            logger.error("Unable to add money on account!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }

    @Override
    public void updateProfile(Passenger passenger) throws ServiceException {
        Connection connection = dbProvider.getConnection();
        try {
            passengerDAO.updateProfile(connection, passenger);
            dbProvider.commit(connection);
        } catch (DAOException e) {
            logger.error("Unable to get all CruiseShowDTO!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }

    @Override
    public void updateDocument(Passenger passenger) throws ServiceException {
        Connection connection = dbProvider.getConnection();
        try {
            passengerDAO.updateDocument(connection, passenger);
            dbProvider.commit(connection);
        } catch (DAOException e) {
            logger.error("Unable to update document!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }
}
