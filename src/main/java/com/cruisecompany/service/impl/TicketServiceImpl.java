package com.cruisecompany.service.impl;

import com.cruisecompany.dao.DAOFactory;
import com.cruisecompany.dao.PassengerDAO;
import com.cruisecompany.dao.TicketDAO;
import com.cruisecompany.dao.db.DBProvider;
import com.cruisecompany.dto.PassengerDTO;
import com.cruisecompany.entity.Cruise;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.entity.Ticket;
import com.cruisecompany.exception.DAOException;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.TicketService;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

@Log4j2
public class TicketServiceImpl implements TicketService {
    private final DBProvider dbProvider;
    private final PassengerDAO passengerDAO;
    private final TicketDAO ticketDAO;

    public TicketServiceImpl(DBProvider dbProvider) {
        this.dbProvider = dbProvider;
        passengerDAO = DAOFactory.getInstance().getPassengerDAO();
        ticketDAO = DAOFactory.getInstance().getTicketDAO();
    }

    @Override
    public BigDecimal pay(long orderId) throws ServiceException {
        Connection connection = dbProvider.getConnection();
        try {
            Ticket ticket = ticketDAO.get(connection, orderId).get();
            BigDecimal newMoney = passengerDAO.subtractMoney(connection,
                    ticket.getPassenger().getId(),
                    ticket.getCruise().getPrice());
            if (newMoney.compareTo(BigDecimal.valueOf(0)) < 0) {
                dbProvider.rollback(connection);
                throw new ServiceException("Not enough money!");
            }
            ticketDAO.updatePaidStatus(connection, orderId);
            dbProvider.commit(connection);
            return newMoney;
        } catch (DAOException e) {
            dbProvider.rollback(connection);
            log.error("Unable to pay for cruise!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }

    @Override
    public long buy(PassengerDTO passengerDTO, long cruiseId) throws ServiceException {
        Connection connection = dbProvider.getConnection();
        try {
            Ticket ticket = Ticket.builder()
                    .cruise(Cruise.builder()
                            .id(cruiseId)
                            .build())
                    .passenger(Passenger.builder()
                            .id(passengerDTO.getPassengerId())
                            .build())
                    .build();
            long orderId = ticketDAO.save(connection, ticket);
            dbProvider.commit(connection);
            return orderId;
        } catch (DAOException e) {
            log.error("Unable to buy cruise!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }

    @Override
    public List<Ticket> getAllPassengerOrders(long id) throws ServiceException {
        Connection connection = dbProvider.getConnection();
        try {
            return ticketDAO.getAllPassengerOrders(connection, id);
        } catch (DAOException e) {
            log.error("Unable to get all passengerOrders!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }

    @Override
    public void block(long orderId) throws ServiceException {
        Connection connection = dbProvider.getConnection();
        try {
            ticketDAO.block(connection, orderId);
            dbProvider.commit(connection);
        } catch (DAOException e) {
            log.error("Unable to block an order!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }

    @Override
    public void unblock(long orderId) throws ServiceException {
        Connection connection = dbProvider.getConnection();
        try {
            ticketDAO.unblock(connection, orderId);
            dbProvider.commit(connection);
        } catch (DAOException e) {
            log.error("Unable to unblock an order!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }

    @Override
    public void confirm(long orderId) throws ServiceException {
        Connection connection = dbProvider.getConnection();
        try {
            ticketDAO.confirm(connection, orderId);
            dbProvider.commit(connection);
        } catch (DAOException e) {
            log.error("Unable to confirm an order!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }
}
