package com.cruisecompany.db.dao;

import com.cruisecompany.entity.Cruise;
import com.cruisecompany.exception.DAOException;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CruiseDAO extends DAO<Cruise> {

    List<Cruise> getAllFiltered(Connection connection, LocalDate dateFrom, LocalDate dateTo, int durationFrom, int durationTo,
                                int limit, int offset) throws DAOException;

    long getCruiseRowAmount(Connection connection, LocalDate dateFrom, LocalDate dateTo, int durationFrom, int durationTo) throws DAOException;

    @Override
    long save(Connection connection, Cruise cruise) throws DAOException;

    @Override
    void update(Connection connection, Cruise cruise) throws DAOException;

    @Override
    Optional<Cruise> get(Connection connection, long id) throws DAOException;

    @Override
    List<Cruise> getAll(Connection connection ) throws DAOException;

    @Override
    void delete(Connection connection, long id) throws DAOException;
}
