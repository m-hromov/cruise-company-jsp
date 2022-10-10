package com.cruisecompany.controller.action.add;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.OrderService;
import com.cruisecompany.service.ServiceFactory;
import com.cruisecompany.service.impl.PassengerServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuyCruiseAction implements Action {
    @Override
    public ActionMethod execute(HttpServletRequest request, HttpServletResponse response) {
        OrderService orderService = ServiceFactory.getInstance().getOrderService();
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
