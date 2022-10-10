package com.cruisecompany.db.dao;

import com.cruisecompany.exception.DAOException;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    long save(T obj) throws DAOException;

    void update(T obj) throws DAOException;

    Optional<T> get(long id) throws DAOException;

    List<T> getAll() throws DAOException;

    void delete(long id) throws DAOException;
}
