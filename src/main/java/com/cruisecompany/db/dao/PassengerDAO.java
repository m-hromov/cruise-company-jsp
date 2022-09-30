package com.cruisecompany.db.dao;

import com.cruisecompany.entity.Passenger;

import java.math.BigDecimal;
import java.util.Optional;

public interface PassengerDAO extends DAO<Passenger>{
    Optional<Passenger> getByUserAccountId(long id);
    void updateMoney(long passengerId,BigDecimal money);
    void addMoney(long passengerId,BigDecimal money);
}
