package com.cruisecompany.controller.action.add;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.service.OrderService;
import com.cruisecompany.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

public class PayForCruiseAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        long orderId = Long.parseLong(request.getParameter("order_id"));
        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        BigDecimal paid = orderService.pay(orderId);
        try {
            if(paid.longValue()<0) {
                session.setAttribute("lowMoney",true);
                response.sendRedirect("/cruise/user_orders");
                return;
            }
            Passenger passenger = (Passenger) session.getAttribute("user");
            passenger.setMoney(paid);
            response.sendRedirect("/cruise/user_orders");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
