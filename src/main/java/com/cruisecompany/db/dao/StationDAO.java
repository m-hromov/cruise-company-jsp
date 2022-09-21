package com.cruisecompany.db.dao;

import com.cruisecompany.db.entity.Route;
import com.cruisecompany.db.entity.Station;

import java.util.List;

public interface StationDAO extends DAO<Station>{
    List<Station> getAllStationsByCruiseID(long id);
}
