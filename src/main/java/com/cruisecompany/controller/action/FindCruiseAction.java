package com.cruisecompany.controller.action;

import com.cruisecompany.db.dao.CruiseDAO;
import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.db.dao.mapper.RowMapperFactory;
import com.cruisecompany.db.entity.Cruise;
import com.cruisecompany.db.dao.impl.CruiseDAOImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.cruisecompany.db.dao.mapper.Tables.CRUISE;
import static com.cruisecompany.db.dao.mapper.impl.Columns.ID_SHIP;

public class FindCruiseAction implements Action{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        RowMapper<Cruise> cruiseRowMapper = RowMapperFactory.getInstance().getCruiseRowMapper();
        CruiseDAO cruiseDAO = new CruiseDAOImpl(cruiseRowMapper, CRUISE);
        List<Cruise> list = cruiseDAO.getAll();
        request.setAttribute("listCruise",list);
        RequestDispatcher rd = request.getRequestDispatcher("/find_cruise.jsp");
        try {
            rd.forward(request,response);
        } catch (ServletException e) {
                System.out.println(e);

        } catch (IOException e) {
            System.out.println("fail2");
        }
    }
}
