package com.cruisecompany.db.dao;

import com.cruisecompany.entity.Passenger;
import com.cruisecompany.exception.DAOException;

import java.math.BigDecimal;
import java.util.Optional;

public interface PassengerDAO extends DAO<Passenger> {
    Optional<Passenger> getByUserAccountId(long id) throws DAOException;

    void updateMoney(long passengerId, BigDecimal money) throws DAOException;

    void updateProfile(Passenger passenger) throws DAOException;

    void updateDocument(Passenger passenger) throws DAOException;

    void addMoney(long passengerId, BigDecimal money) throws DAOException;
}
