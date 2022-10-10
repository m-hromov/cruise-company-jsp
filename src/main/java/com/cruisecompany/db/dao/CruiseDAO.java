package com.cruisecompany.db.dao;

import com.cruisecompany.entity.Cruise;
import com.cruisecompany.exception.DAOException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CruiseDAO extends DAO<Cruise> {

    List<Cruise> getAllFiltered(LocalDate dateFrom, LocalDate dateTo, int durationFrom, int durationTo,
                                int limit, int offset) throws DAOException;

    long getCruiseRowAmount(LocalDate dateFrom, LocalDate dateTo, int durationFrom, int durationTo) throws DAOException;

    @Override
    long save(Cruise cruise) throws DAOException;

    @Override
    void update(Cruise cruise) throws DAOException;

    @Override
    Optional<Cruise> get(long id) throws DAOException;

    @Override
    List<Cruise> getAll() throws DAOException;

    @Override
    void delete(long id) throws DAOException;
}
