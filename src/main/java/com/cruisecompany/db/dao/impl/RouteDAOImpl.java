package com.cruisecompany.db.dao.impl;

import com.cruisecompany.db.dao.AbstractDAO;
import com.cruisecompany.db.dao.RouteDAO;
import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.db.entity.Route;

import java.util.List;

public class RouteDAOImpl extends AbstractDAO<Route> implements RouteDAO {



    public RouteDAOImpl(RowMapper<Route> rowMapper, String table) {
        super(rowMapper, table);
    }

    @Override
    public long save(Route obj) {
        return 0;
    }

    @Override
    public void update(Route obj) {

    }



}
