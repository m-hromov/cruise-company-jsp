package com.cruisecompany.db.dao;

import com.cruisecompany.entity.Cruise;

import java.util.List;
import java.util.Optional;

public interface CruiseDAO extends DAO<Cruise>{
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
