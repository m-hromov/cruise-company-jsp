package com.cruisecompany.controller.action.show;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.db.dto.CruiseShowDTO;
import com.cruisecompany.db.entity.Passenger;
import com.cruisecompany.service.CruiseService;
import com.cruisecompany.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserOrdersAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Passenger passenger = (Passenger) request.getSession().getAttribute("user");
        CruiseService cruiseService = ServiceFactory.getInstance().getCruiseService();
        List<CruiseShowDTO> cruiseList = cruiseService.getAllPassengerCruiseShowDTO(passenger.getId());

        request.setAttribute("listCruise",cruiseList);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/user_orders.jsp");
        try {
            rd.forward(request,response);
        } catch (ServletException e) {
                System.out.println(e);

        } catch (IOException e) {
            System.out.println("fail2");
        }
    }
}
