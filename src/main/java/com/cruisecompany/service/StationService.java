package com.cruisecompany.service;

import com.cruisecompany.entity.Station;
import com.cruisecompany.exception.ServiceException;

import java.util.List;

public interface StationService {
    /**
     * Gets all stations.
     *
     * @return list of stations or empty list if no stations were found.
     * @throws ServiceException if something went wrong
     */
    List<Station> getAll() throws ServiceException;
    /**
     * Adds a new station.
     *
     * @param station station to be inserted
     * @throws ServiceException if something went wrong
     */
    void addStation(Station station) throws ServiceException;
}
