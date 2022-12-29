package com.cruisecompany.dao;

import com.cruisecompany.entity.Cruise;
import com.cruisecompany.exception.DAOException;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public interface CruiseDAO extends DAO<Cruise> {
    /**
     * Gets all rows that match the parameters.
     * @param connection Connection, on which a query will be executed
     * @param dateFrom a start point of a date gap
     * @param dateTo an end point of a date gap
     * @param durationFrom a start point of a duration gap
     * @param durationTo an end point of a duration gap
     * @param limit a max amount of rows that will be returned
     * @param offset an offset
     * @return List of Cruise that matches the parameters
     * @throws DAOException if something went wrong
     */
    List<Cruise> getAllFiltered(Connection connection, LocalDate dateFrom, LocalDate dateTo, int durationFrom, int durationTo,
                                int limit, int offset) throws DAOException;

    /**
     * Gets an amount of rows that match the parameters
     * @param connection Connection, on which a query will be executed
     * @param dateFrom a start point of a date gap
     * @param dateTo an end point of a date gap
     * @param durationFrom a start point of a duration gap
     * @param durationTo an end point of a duration gap
     * @return the amount of rows
     * @throws DAOException if something went wrong
     */
    long getCruiseRowAmount(Connection connection, LocalDate dateFrom, LocalDate dateTo, int durationFrom, int durationTo) throws DAOException;

}
