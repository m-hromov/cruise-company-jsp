package com.cruisecompany.dao;

import com.cruisecompany.exception.DAOException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    /**
     * This method executes INSERT of object specified in parameters and returns generated ID.
     *
     * @param connection Connection, on which a query will be executed
     * @param obj        object to be inserted
     * @return generated ID
     * @throws DAOException if something went wrong
     */
    long save(Connection connection, T obj) throws DAOException;

    /**
     * This method executes UPDATE of object specified in parameters.
     *
     * @param connection Connection, on which a query will be executed
     * @param obj        object to be updated
     * @throws DAOException if something went wrong
     */
    void update(Connection connection, T obj) throws DAOException;

    /**
     * This method executes SELECT of object by specified ID.
     *
     * @param connection Connection, on which a query will be executed
     * @param id         ID of the object
     * @return Optional.ofNullable
     * @throws DAOException if something went wrong
     */
    Optional<T> get(Connection connection, long id) throws DAOException;

    /**
     * This method executes SELECT of all objects.
     *
     * @param connection Connection, on which a query will be executed
     * @return list of objects or empty list if no objects were found.
     * @throws DAOException if something went wrong
     */
    List<T> getAll(Connection connection) throws DAOException;

    /**
     * This method executes DELETE of object by specified ID.
     *
     * @param connection Connection, on which a query will be executed
     * @param id         ID of the object
     * @throws DAOException if something went wrong
     */
    void delete(Connection connection, long id) throws DAOException;
}
