package com.cruisecompany.controller.action.redirect;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.OrderService;
import com.cruisecompany.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuyCruiseAction implements Action {
    @Override
    public ActionMethod execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = (ServiceFactory) request.getServletContext()
                .getAttribute("ServiceFactory");
        OrderService orderService = serviceFactory.getOrderService();
        Passenger passenger = (Passenger) request.getSession().getAttribute("user");
        long cruiseId = Long.parseLong(request.getParameter("cruise_id"));
        try {
            orderService.buy(passenger.getId(), cruiseId);
        } catch (ServiceException e) {
            request.getSession().setAttribute("error",500);
            request.getSession().setAttribute("error_msg","Unable to buy a ticket!");
            return new ActionMethod("/cruise/edit_profile", Method.REDIRECT);
        }
        return new ActionMethod("/cruise/find_cruise", Method.REDIRECT);
    }
}
