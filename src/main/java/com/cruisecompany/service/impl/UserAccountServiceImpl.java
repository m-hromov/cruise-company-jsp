package com.cruisecompany.service.impl;

import com.cruisecompany.dao.DAOFactory;
import com.cruisecompany.dao.PassengerDAO;
import com.cruisecompany.dao.UserAccountDAO;
import com.cruisecompany.dao.db.DBProvider;
import com.cruisecompany.dto.PassengerDTO;
import com.cruisecompany.dto.mapper.DTOMapper;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.entity.UserAccount;
import com.cruisecompany.exception.*;
import com.cruisecompany.service.UserAccountService;
import com.cruisecompany.util.password.PasswordEncryption;
import com.cruisecompany.util.validator.Validators;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.util.Optional;

@Log4j2
public class UserAccountServiceImpl implements UserAccountService {
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
                String oldPassword = userAccount.getPassword();
                String salt = userAccount.getPasswordSalt();
                boolean passwordEquals = PasswordEncryption.comparePasswords(oldPassword, password, salt);

                if (!passwordEquals) throw new AuthorizationException("Wrong password or email!");
                Optional<Passenger> optionalPassenger = passengerDAO.getByUserAccountId(connection, userAccount.getId());
                return optionalPassenger.map(DTOMapper::toPassengerDTO);
            }
            throw new AuthorizationException("Wrong password or email!");
        } catch (DAOException | EncryptionException e) {
            log.error("Unable to sign in!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }

    @Override
    public void signUp(Passenger passenger) throws ServiceException, EmailAlreadyExistsException {
        try {
            Validators.validatePassenger(passenger);
        } catch (ValidationException e) {
            log.error("Passenger is not valid!");
            throw new ServiceException(e.getMessage(), e);
        }
        Connection connection = dbProvider.getConnection();
        try {
            UserAccount userAccount = passenger.getUserAccount();

            boolean emailExists = userAccountDAO.checkIfEmailAlreadyExists(connection, userAccount.getEmail());
            if (emailExists) throw new EmailAlreadyExistsException("Email already exists!");

            String unencryptedPassword = userAccount.getPassword();
            String salt = PasswordEncryption.generateSalt();
            String encryptedPassword = PasswordEncryption.hashPassword(unencryptedPassword, salt);
            userAccount.setPassword(encryptedPassword);
            userAccount.setPasswordSalt(salt);
            long userAccountId = userAccountDAO.save(connection, userAccount);
            userAccount.setId(userAccountId);
            long passengerId = passengerDAO.save(connection, passenger);
            passenger.setId(passengerId);
            dbProvider.commit(connection);
        } catch (DAOException | EncryptionException e) {
            dbProvider.rollback(connection);
            log.error("Unable to sign up!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }

    @Override
    public void updatePassword(long userId, String oldEnteredPassword, String newPassword) throws ServiceException, WrongPasswordException {
        try {
            Validators.validatePassword(newPassword);
        } catch (ValidationException e) {
            log.error("New password is not valid!");
            throw new ServiceException(e.getMessage(), e);
        }

        Connection connection = dbProvider.getConnection();
        try {
            Optional<UserAccount> optional = userAccountDAO.get(connection, userId);
            if (optional.isEmpty()) throw new ServiceException();
            UserAccount userAccount = optional.get();
            String oldPassword = userAccount.getPassword();
            String salt = userAccount.getPasswordSalt();
            boolean passwordEquals = PasswordEncryption.comparePasswords(oldPassword, oldEnteredPassword, salt);
            if (!passwordEquals) throw new WrongPasswordException("Password doesn't match!");

            String newSalt = PasswordEncryption.generateSalt();
            String newEncryptedPassword = PasswordEncryption.hashPassword(newPassword, newSalt);
            userAccount.setPassword(newEncryptedPassword);
            userAccount.setPasswordSalt(newSalt);
            userAccountDAO.update(connection, userAccount);
            dbProvider.commit(connection);
        } catch (DAOException | EncryptionException e) {
            log.error("Unable to update password!");
            throw new ServiceException(e.getMessage(), e);
        } finally {
            dbProvider.close(connection);
        }
    }

}
