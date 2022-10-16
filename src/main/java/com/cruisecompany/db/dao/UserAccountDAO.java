package com.cruisecompany.db.dao;

import com.cruisecompany.entity.UserAccount;
import com.cruisecompany.exception.DAOException;

import java.sql.Connection;
import java.util.Optional;

public interface UserAccountDAO extends DAO<UserAccount> {
    Optional<UserAccount> getUserAccountByLogin(Connection connection, String login) throws DAOException;

    void updatePassword(Connection connection, UserAccount userAccount) throws DAOException;
}
