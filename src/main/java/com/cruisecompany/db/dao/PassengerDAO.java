package com.cruisecompany.db.dao;

import com.cruisecompany.entity.Passenger;
import com.cruisecompany.exception.DAOException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Optional;

public interface PassengerDAO extends DAO<Passenger> {
    Optional<Passenger> getByUserAccountId(Connection connection, long id) throws DAOException;

    void updateMoney(Connection connection, long passengerId, BigDecimal money) throws DAOException;

    void updateProfile(Connection connection, Passenger passenger) throws DAOException;

    void updateDocument(Connection connection, Passenger passenger) throws DAOException;

    void addMoney(Connection connection, long passengerId, BigDecimal money) throws DAOException;
}
