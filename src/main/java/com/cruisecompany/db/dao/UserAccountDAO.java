package com.cruisecompany.db.dao;

import com.cruisecompany.entity.UserAccount;
import com.cruisecompany.exception.DAOException;

import java.util.Optional;

public interface UserAccountDAO extends DAO<UserAccount> {
    Optional<UserAccount> getUserAccountByLogin(String login) throws DAOException;

    void updatePassword(UserAccount userAccount) throws DAOException;
}
