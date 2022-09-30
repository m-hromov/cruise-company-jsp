package com.cruisecompany.controller.action.add;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.service.OrderService;
import com.cruisecompany.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BuyCruiseAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        Passenger passenger = (Passenger) request.getSession().getAttribute("user");
        long cruiseId = Long.parseLong(request.getParameter("cruise_id"));
        orderService.buy(passenger.getId(),cruiseId);
        try {
            response.sendRedirect("/cruise/find_cruise");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
