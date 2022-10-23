package com.cruisecompany.db.dao;

import com.cruisecompany.entity.UserAccount;
import com.cruisecompany.exception.DAOException;

import java.sql.Connection;
import java.util.Optional;

public interface UserAccountDAO extends DAO<UserAccount> {
    /**
     *Gets UserAccount by email.
     * @param connection Connection, on which a query will be executed
     * @param email user's email
     * @return Nullable Optional of UserAccount
     * @throws DAOException if something went wrong
     */
    Optional<UserAccount> getUserAccountByLogin(Connection connection, String email) throws DAOException;

    /**
     * Updates user's password.
     * @param connection Connection, on which a query will be executed
     * @param userAccount UserAccount with an ID and a new password.
     * @throws DAOException if something went wrong
     */
    void updatePassword(Connection connection, UserAccount userAccount) throws DAOException;
}
