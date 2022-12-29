package com.cruisecompany.service.impl;

import com.cruisecompany.dao.db.DBProvider;
import com.cruisecompany.dao.DAOFactory;
import com.cruisecompany.dao.TicketDAO;
import com.cruisecompany.dao.PassengerDAO;
import com.cruisecompany.dao.UserAccountDAO;
import com.cruisecompany.dto.PassengerDTO;
import com.cruisecompany.dto.PassengerOrderDTO;
import com.cruisecompany.dto.mapper.DTOMapper;
import com.cruisecompany.entity.Ticket;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.entity.UserAccount;
import com.cruisecompany.exception.DAOException;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.exception.ValidationException;
import com.cruisecompany.service.PassengerService;
import com.cruisecompany.util.files.FileHelper;
import com.cruisecompany.util.files.FileType;
import com.cruisecompany.util.validator.Validators;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
public class PassengerServiceImpl implements PassengerService {
    private final DBProvider dbProvider;
    private final PassengerDAO passengerDAO;
    private final UserAccountDAO userAccountDAO;
    private final TicketDAO ticketDAO;

    public PassengerServiceImpl(DBProvider dbProvider) {
        this.dbProvider = dbProvider;
        passengerDAO = DAOFactory.getInstance().getPassengerDAO();
        userAccountDAO = DAOFactory.getInstance().getUserAccountDAO();
        ticketDAO = DAOFactory.getInstance().getTicketDAO();
    }

    @Override
    public List<PassengerOrderDTO> getAllPassengerOrderDTOList() throws ServiceException {
        Connection connection = dbProvider.getConnection();
        try {
            List<Ticket> ticketList = ticketDAO.getAll(connection);
            return ticketList.stream().map(DTOMapper::toPassengerOrderDTO).collect(Collectors.toList());
        } catch (DAOException e) {
            log.error("Unable to get all PassengerOrderDTOList!");
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
            return optional.orElseGet(()->Passenger.builder().build());
        } catch (DAOException e) {
            log.error("Unable to get passenger by account id!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }

    @Override
    public void addMoney(PassengerDTO passengerDTO, BigDecimal money) throws ServiceException {
        Connection connection = dbProvider.getConnection();
        try {
            BigDecimal newMoney = passengerDAO.addMoney(connection, passengerDTO.getPassengerId(), money);
            passengerDTO.setMoney(newMoney);
            dbProvider.commit(connection);
        } catch (DAOException e) {
            log.error("Unable to add money on account!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }

    @Override
    public void updateProfile(PassengerDTO passengerDTO) throws ServiceException {
        try {
            Validators.validatePassengerProfile(passengerDTO);
        } catch (ValidationException e) {
            log.error("Unable to validate profile!");
            throw new ServiceException(e.getMessage(), e);
        }
        Connection connection = dbProvider.getConnection();
        try {
            passengerDAO.updateProfile(connection, passengerDTO);

            UserAccount userAccount = UserAccount.builder()
                    .id(passengerDTO.getUserAccountId())
                    .email(passengerDTO.getEmail())
                    .build();
            userAccountDAO.updateEmail(connection, userAccount);
            dbProvider.commit(connection);
        } catch (DAOException e) {
            log.error("Unable to get all CruiseShowDTO!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }

    @Override
    public void updateDocument(PassengerDTO passengerDTO, Part photoPart, String requestRealPath) throws ServiceException {
        try {
            Validators.validatePhoto(photoPart);
        } catch (ValidationException e) {
            log.error("Unable to validate photo!");
            throw new ServiceException(e.getMessage(), e);
        }

        Connection connection = dbProvider.getConnection();
        try {
            String relativePath = "secured_files" + File.separator + "documents";
            String uploadPath = requestRealPath + relativePath;
            String filename = FileHelper.writeRecord(photoPart, uploadPath,
                    FileType.PASSENGER_DOCUMENT, passengerDTO.getPassengerId());
            passengerDTO.setDocumentPath(relativePath + File.separator + filename);
            passengerDAO.updateDocument(connection, passengerDTO);
            dbProvider.commit(connection);
        } catch (DAOException | IOException e) {
            log.error("Unable to update document!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }
}
