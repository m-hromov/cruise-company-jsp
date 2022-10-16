package com.cruisecompany.db.dao;

import com.cruisecompany.exception.DAOException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    long save(Connection connection, T obj) throws DAOException;

    void update(Connection connection, T obj) throws DAOException;

    Optional<T> get(Connection connection, long id) throws DAOException;

    List<T> getAll(Connection connection) throws DAOException;

    void delete(Connection connection, long id) throws DAOException;
}
