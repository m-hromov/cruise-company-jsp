package com.cruisecompany.db.dao;

import com.cruisecompany.entity.Cruise;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CruiseDAO extends DAO<Cruise>{

    List<Cruise> getAllFiltered(LocalDate dateFrom, LocalDate dateTo, int durationFrom, int durationTo,
                                int limit, int offset);
    long getCruiseRowAmount(LocalDate dateFrom, LocalDate dateTo, int durationFrom, int durationTo);
    @Override
    long save(Cruise cruise);

    @Override
    void update(Cruise cruise);

    @Override
    Optional<Cruise> get(long id);

    @Override
    List<Cruise> getAll();

    @Override
    void delete(long id);
}
