package com.cruisecompany.service.impl;

import com.cruisecompany.db.DBProvider;
import com.cruisecompany.db.dao.DAOFactory;
import com.cruisecompany.db.dao.PassengerDAO;
import com.cruisecompany.db.dao.UserAccountDAO;
import com.cruisecompany.dto.PassengerDTO;
import com.cruisecompany.dto.mapper.DTOMapper;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.entity.UserAccount;
import com.cruisecompany.exception.AuthorizationException;
import com.cruisecompany.exception.DAOException;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.exception.ValidationException;
import com.cruisecompany.service.UserAccountService;
import com.cruisecompany.util.password.PasswordEncryption;
import com.cruisecompany.util.validator.Validators;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.Optional;

public class UserAccountServiceImpl implements UserAccountService {
    final static Logger logger = LogManager.getLogger(UserAccountServiceImpl.class);
    private final DBProvider dbProvider;
    private final UserAccountDAO userAccountDAO;
    private final PassengerDAO passengerDAO;

    public UserAccountServiceImpl(DBProvider dbProvider) {
        this.dbProvider = dbProvider;
        userAccountDAO = DAOFactory.getInstance().getUserAccountDAO();
        passengerDAO = DAOFactory.getInstance().getPassengerDAO();
    }

    @Override
    public Optional<PassengerDTO> signIn(String email, String password) throws ServiceException, AuthorizationException {
        Connection connection = dbProvider.getConnection();
        try {
            Optional<UserAccount> optionalUserAccount = userAccountDAO.getUserAccountByLogin(connection, email);
            if (optionalUserAccount.isPresent()) {
                UserAccount userAccount = optionalUserAccount.get();
                if (userAccount.getPassword().equals(PasswordEncryption.passwordToHash(password))) {
                    Optional<Passenger> optionalPassenger = passengerDAO.getByUserAccountId(connection, userAccount.getId());
                    return optionalPassenger.map(DTOMapper::toPassengerDTO);
                }
                return Optional.empty();
            }
            throw new AuthorizationException("Wrong password or email!");
        } catch (DAOException e) {
            logger.error("Unable to sign in!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }

    @Override
    public void signUp(Passenger passenger) throws ServiceException {
        try {
            Validators.validatePassenger(passenger);
        } catch (ValidationException e) {
            logger.error("Passenger is not valid!");
            throw new ServiceException(e.getMessage(), e);
        }
        Connection connection = dbProvider.getConnection();
        try {
            UserAccount userAccount = passenger.getUserAccount();
            long userAccountId = userAccountDAO.save(connection, userAccount);
            userAccount.setId(userAccountId);
            long passengerId = passengerDAO.save(connection, passenger);
            passenger.setId(passengerId);
            dbProvider.commit(connection);
        } catch (DAOException e) {
            dbProvider.rollback(connection);
            logger.error("Unable to sign up!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }

    @Override
    public void updatePassword(long userId, String oldPassword, String newPassword) throws ServiceException {
        try {
            Validators.validatePassword(newPassword);
        } catch (ValidationException e) {
            logger.error("New password is not valid!");
            throw new ServiceException(e.getMessage(), e);
        }

        Connection connection = dbProvider.getConnection();
        try {
            Optional<UserAccount> optional = userAccountDAO.get(connection, userId);
            if (optional.isEmpty()) throw new ServiceException();
            UserAccount userAccount = optional.get();
            String confirmOldPassword = userAccount.getPassword();
            if (!oldPassword.equals(confirmOldPassword)) throw new ServiceException();
            userAccount.setPassword(newPassword);
            userAccountDAO.updatePassword(connection, userAccount);
            dbProvider.commit(connection);
        } catch (DAOException e) {
            logger.error("Unable to update password!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }

}
