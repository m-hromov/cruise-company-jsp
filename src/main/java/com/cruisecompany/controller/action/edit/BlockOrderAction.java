package com.cruisecompany.controller.action.edit;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.service.OrderService;
import com.cruisecompany.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BlockOrderAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        boolean block = Boolean.parseBoolean(request.getParameter("block"));
        long orderId = Long.parseLong(request.getParameter("order_id"));
        if(block) {
            orderService.block(orderId);
        } else {
            orderService.unblock(orderId);
        }
        try {
            response.sendRedirect("/cruise/orders");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
