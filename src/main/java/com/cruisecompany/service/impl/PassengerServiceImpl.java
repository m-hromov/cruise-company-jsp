package com.cruisecompany.service.impl;

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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PassengerServiceImpl implements PassengerService {
    final static Logger logger = LogManager.getLogger(PassengerServiceImpl.class);
    private final PassengerDAO passengerDAO;
    private final OrderDAO orderDAO;

    public PassengerServiceImpl() {
        passengerDAO = DAOFactory.getInstance().getPassengerDAO();
        orderDAO = DAOFactory.getInstance().getOrderDAO();
    }

    @Override
    public List<PassengerOrderDTO> getAllPassengerOrderDTOList() throws ServiceException {
        try {
            List<Order> orderList = orderDAO.getAll();
            return orderList.stream().map(DTOMapper::toPassengerOrderDTO).collect(Collectors.toList());
        } catch (DAOException e) {
            logger.error("Unable to get all PassengerOrderDTOList!");
            throw new ServiceException(e.getMessage(),e);
        }
    }

    @Override
    public Passenger getPassengerByAccountId(long id) throws ServiceException {
        try {
            Optional<Passenger> optional = passengerDAO.getByUserAccountId(id);
            return optional.orElseGet(Passenger::new);
        } catch (DAOException e) {
            logger.error("Unable to get passenger by account id!");
            throw new ServiceException(e.getMessage(),e);
        }
    }

    @Override
    public void addMoney(long passengerId,BigDecimal money) throws ServiceException {
        try {
            passengerDAO.addMoney(passengerId,money);
        } catch (DAOException e) {
            logger.error("Unable to add money on account!");
            throw new ServiceException(e.getMessage(),e);
        }
    }

    @Override
    public void updateProfile(Passenger passenger) throws ServiceException {
        try {
            passengerDAO.updateProfile(passenger);
        } catch (DAOException e) {
            logger.error("Unable to get all CruiseShowDTO!");
            throw new ServiceException(e.getMessage(),e);
        }
    }

    @Override
    public void updateDocument(Passenger passenger) throws ServiceException {
        try {
            passengerDAO.updateDocument(passenger);
        } catch (DAOException e) {
            logger.error("Unable to update document!");
            throw new ServiceException(e.getMessage(),e);
        }
    }


}
