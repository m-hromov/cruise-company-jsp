package com.cruisecompany.controller.action;

import com.cruisecompany.db.dao.CruiseDAO;
import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.db.dao.mapper.RowMapperFactory;
import com.cruisecompany.db.dto.CruiseShowDTO;
import com.cruisecompany.db.entity.Cruise;
import com.cruisecompany.db.dao.impl.CruiseDAOImpl;
import com.cruisecompany.service.CruiseService;
import com.cruisecompany.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.cruisecompany.db.Tables.CRUISE;

public class FindCruiseAction implements Action{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        CruiseService cruiseService = ServiceFactory.getInstance().getCruiseService();
        List<CruiseShowDTO> cruiseList = cruiseService.getAllCruiseShowDTO();
        request.setAttribute("listCruise",cruiseList);
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
