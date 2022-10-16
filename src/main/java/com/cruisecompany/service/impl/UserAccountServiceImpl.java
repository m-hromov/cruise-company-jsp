package com.cruisecompany.service.impl;

import com.cruisecompany.db.DBProvider;
import com.cruisecompany.db.dao.DAOFactory;
import com.cruisecompany.db.dao.PassengerDAO;
import com.cruisecompany.db.dao.UserAccountDAO;
import com.cruisecompany.db.dto.DTOMapper;
import com.cruisecompany.db.dto.UserAccountDTO;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.entity.UserAccount;
import com.cruisecompany.exception.DAOException;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.UserAccountService;
import com.cruisecompany.util.PasswordEncryption;
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
    public Optional<UserAccountDTO> signIn(String login, String password) throws ServiceException {
        Connection connection = dbProvider.getConnection();
        try {
            Optional<UserAccount> optionalUserAccount = userAccountDAO.getUserAccountByLogin(connection, login);
            if (optionalUserAccount.isPresent()) {
                UserAccount userAccount = optionalUserAccount.get();
                if (userAccount.getPassword().equals(PasswordEncryption.passwordToHash(password)))
                    return optionalUserAccount.map(DTOMapper::toUserAccountDTO);
            }
            return Optional.empty();
        } catch (DAOException e) {
            logger.error("Unable to sign in!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }

    @Override
    public void signUp(Passenger passenger) throws ServiceException {
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
