package com.cruisecompany.dao;

import com.cruisecompany.entity.Station;
import com.cruisecompany.exception.DAOException;

import java.sql.Connection;
import java.util.List;

public interface StationDAO extends DAO<Station> {
    /**
     * Gets list of stations by specified cruise ID.
     * @param connection Connection, on which a query will be executed
     * @param cruiseId ID of the cruise
     * @return List of Stations with specified cruise ID
     * @throws DAOException if something went wrong
     */
    List<Station> getAllStationsByCruiseID(Connection connection, long cruiseId) throws DAOException;
}
