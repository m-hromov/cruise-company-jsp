package com.cruisecompany.service.impl;

import com.cruisecompany.db.dao.DAOFactory;
import com.cruisecompany.db.dao.StationDAO;
import com.cruisecompany.entity.Station;
import com.cruisecompany.service.StationService;

import java.util.List;

public class StationServiceImpl implements StationService {
    private final StationDAO stationDAO;

    public StationServiceImpl() {
        this.stationDAO = DAOFactory.getInstance().getStationDAO();
    }
    @Override
    public List<Station> getAll() {
        return stationDAO.getAll();
    }

    @Override
    public void addStation(Station station) {
        stationDAO.save(station);
    }
}
