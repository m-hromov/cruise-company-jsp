package com.cruisecompany.db.dao;

import com.cruisecompany.entity.UserAccount;

import java.util.Optional;

public interface UserAccountDAO extends DAO<UserAccount>{
    Optional<UserAccount> getUserAccountByLogin(String login);
}
