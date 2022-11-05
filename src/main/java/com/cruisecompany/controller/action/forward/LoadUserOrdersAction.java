package com.cruisecompany.controller.action.forward;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.dto.PassengerDTO;
import com.cruisecompany.entity.Order;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.OrderService;
import com.cruisecompany.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;

public class LoadUserOrdersAction implements Action {
    @Override
    public ActionMethod execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ServiceFactory serviceFactory = (ServiceFactory) request.getServletContext()
                    .getAttribute("ServiceFactory");
            PassengerDTO passengerDTO = (PassengerDTO) request.getSession().getAttribute("user");
            OrderService orderService = serviceFactory.getOrderService();
            List<Order> orderList = orderService.getAllPassengerOrders(passengerDTO.getPassengerId());
            LocalDate currentDate = LocalDate.now();
            request.setAttribute("currentDate",currentDate);
            request.setAttribute("orderList",orderList);
            return new ActionMethod("/WEB-INF/view/user_orders.jsp", Method.FORWARD);
        } catch (ServiceException e) {
            request.getSession().setAttribute("error", 500);
            request.getSession().setAttribute("errorMsg", "Something went wrong!");
            return new ActionMethod("/WEB-INF/view/error.jsp", Method.FORWARD);
        }
    }
}
