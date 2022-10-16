package com.cruisecompany.db.dao;

import com.cruisecompany.entity.Station;
import com.cruisecompany.exception.DAOException;

import java.sql.Connection;
import java.util.List;

public interface StationDAO extends DAO<Station> {
    List<Station> getAllStationsByCruiseID(Connection connection, long id) throws DAOException;
}
