package com.cruisecompany.service.impl;

import com.cruisecompany.db.dao.CruiseDAO;
import com.cruisecompany.db.dao.DAOFactory;
import com.cruisecompany.db.dao.OrderDAO;
import com.cruisecompany.db.dao.RouteDAO;
import com.cruisecompany.db.dto.CruiseShowDTO;
import com.cruisecompany.db.dto.DTOMapper;
import com.cruisecompany.entity.Cruise;
import com.cruisecompany.entity.Route;
import com.cruisecompany.entity.Station;
import com.cruisecompany.service.CruiseService;

import java.util.List;
import java.util.stream.Collectors;

public class CruiseServiceImpl implements CruiseService {
    private final CruiseDAO cruiseDAO;
    private final RouteDAO routeDAO;
    private final OrderDAO orderDAO;

    public CruiseServiceImpl() {
        this.cruiseDAO = DAOFactory.getInstance().getCruiseDAO();
        this.routeDAO = DAOFactory.getInstance().getRouteDAO();
        this.orderDAO = DAOFactory.getInstance().getOrderDAO();
    }

    @Override
    public List<CruiseShowDTO> getAllCruiseShowDTO() {
        List<Cruise> cruiseList = cruiseDAO.getAll();
        return cruiseList.stream().map(DTOMapper::toCruiseShowDTO).collect(Collectors.toList());
    }

    @Override
    public List<CruiseShowDTO> getAllCruiseShowDTOBySearch(String str) {
        return null;
    }

    @Override
    public void addCruise(Cruise cruise) {
        long cruiseId = cruiseDAO.save(cruise);
        cruise.setId(cruiseId);
        List<Station> stationList = cruise.getStationList();
        for (int i = 0; i < stationList.size(); i++){
            Route route = new Route();
            route.setCruise(cruise)
                    .setStation(stationList.get(i))
                    .setOrderNumber(i);
            routeDAO.save(route);
        }
    }
}
