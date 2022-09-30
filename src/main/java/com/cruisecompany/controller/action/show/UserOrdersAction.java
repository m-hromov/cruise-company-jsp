package com.cruisecompany.controller.action.show;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.entity.Order;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.service.OrderService;
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
        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        List<Order> orderList = orderService.getAllPassengerOrders(passenger.getId());

        request.setAttribute("orderList",orderList);
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
