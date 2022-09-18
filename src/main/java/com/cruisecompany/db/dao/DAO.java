package com.cruisecompany.db.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    long save(T obj);

    void update(T obj);

    Optional<T> get(long id);

    List<T> getAll();

    void delete(long id);
}
