package com.cruisecompany.controller.action.edit;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.service.OrderService;
import com.cruisecompany.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConfirmOrderAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        long orderId = Long.parseLong(request.getParameter("order_id"));
        orderService.confirm(orderId);
        try {
            response.sendRedirect("/cruise/orders");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
