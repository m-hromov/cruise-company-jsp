package com.cruisecompany.controller.action.show;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.entity.Order;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.service.OrderService;
import com.cruisecompany.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class UserOrdersAction implements Action {
    @Override
    public ActionMethod execute(HttpServletRequest request, HttpServletResponse response) {
        Passenger passenger = (Passenger) request.getSession().getAttribute("user");
        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        List<Order> orderList = orderService.getAllPassengerOrders(passenger.getId());
        LocalDate currentDate = LocalDate.now();
        request.setAttribute("currentDate",currentDate);
        request.setAttribute("orderList",orderList);
        return new ActionMethod("/WEB-INF/view/user_orders.jsp", Method.FORWARD);
    }
}
