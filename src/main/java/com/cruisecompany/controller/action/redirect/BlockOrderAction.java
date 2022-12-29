package com.cruisecompany.controller.action.redirect;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.TicketService;
import com.cruisecompany.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockOrderAction implements Action {
    @Override
    public ActionMethod execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ServiceFactory serviceFactory = (ServiceFactory) request.getServletContext()
                    .getAttribute("ServiceFactory");
            TicketService ticketService = serviceFactory.getOrderService();

            boolean block = Boolean.parseBoolean(request.getParameter("block"));
            long orderId = Long.parseLong(request.getParameter("order_id"));
            if(block) {
                ticketService.block(orderId);
            } else {
                ticketService.unblock(orderId);
            }
            return new ActionMethod("/cruise/orders", Method.REDIRECT);
        } catch (ServiceException e) {
            request.getSession().setAttribute("error", 500);
            request.getSession().setAttribute("errorMsg", "Something went wrong!");
            return new ActionMethod("/cruise/error", Method.REDIRECT);
        }
    }
}
