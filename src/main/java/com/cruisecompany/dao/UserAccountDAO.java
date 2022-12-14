package com.cruisecompany.dao;

import com.cruisecompany.entity.UserAccount;
import com.cruisecompany.exception.DAOException;

import java.sql.Connection;
import java.util.Optional;

public interface UserAccountDAO extends DAO<UserAccount> {
    /**
     * Gets UserAccount by email.
     *
     * @param connection Connection, on which a query will be executed
     * @param email      user's email
     * @return Nullable Optional of UserAccount
     * @throws DAOException if something went wrong
     */
    Optional<UserAccount> getUserAccountByLogin(Connection connection, String email) throws DAOException;

    /**
     * Checks if UserAccount with the same email already exists.
     * @param connection Connection, on which a query will be executed
     * @param email user's email
     * @return true if exists, false otherwise
     * @throws DAOException if something went wrong
     */
    boolean checkIfEmailAlreadyExists(Connection connection, String email) throws DAOException;

    /**
     * Updates user's email.
     *
     * @param connection  Connection, on which a query will be executed
     * @param userAccount UserAccount with an ID and a new email.
     * @throws DAOException if something went wrong
     */
    void updateEmail(Connection connection, UserAccount userAccount) throws DAOException;
}
