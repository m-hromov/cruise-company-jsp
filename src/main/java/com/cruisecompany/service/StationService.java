package com.cruisecompany.service;

import com.cruisecompany.entity.Station;
import com.cruisecompany.exception.ServiceException;

import java.util.List;

public interface StationService {
    List<Station> getAll() throws ServiceException;
    void addStation(Station station) throws ServiceException;
}
