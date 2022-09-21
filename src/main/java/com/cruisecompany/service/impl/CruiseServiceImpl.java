package com.cruisecompany.service.impl;

import com.cruisecompany.db.dao.CruiseDAO;
import com.cruisecompany.db.dao.DAOFactory;
import com.cruisecompany.db.dao.RouteDAO;
import com.cruisecompany.db.dto.CruiseShowDTO;
import com.cruisecompany.db.dto.DTOMapper;
import com.cruisecompany.db.entity.Cruise;
import com.cruisecompany.db.entity.Route;
import com.cruisecompany.service.CruiseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CruiseServiceImpl implements CruiseService {
    private final CruiseDAO cruiseDAO;
    private final RouteDAO routeDAO;

    public CruiseServiceImpl() {
        this.cruiseDAO = DAOFactory.getInstance().getCruiseDAO();
        this.routeDAO = DAOFactory.getInstance().getRouteDAO();
    }

    public List<CruiseShowDTO> findAll() {
        return null;
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
}
