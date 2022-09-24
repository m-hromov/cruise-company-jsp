package com.cruisecompany.db.dao;

import com.cruisecompany.db.entity.Passenger;

import java.util.Optional;

public interface PassengerDAO extends DAO<Passenger>{
    Optional<Passenger> getByUserAccountId(long id);
}
