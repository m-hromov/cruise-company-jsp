package com.cruisecompany.controller.action.edit;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.service.OrderService;
import com.cruisecompany.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BlockOrderAction implements Action {
    @Override
    public ActionMethod execute(HttpServletRequest request, HttpServletResponse response) {
        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        boolean block = Boolean.parseBoolean(request.getParameter("block"));
        long orderId = Long.parseLong(request.getParameter("order_id"));
        if(block) {
            orderService.block(orderId);
        } else {
            orderService.unblock(orderId);
        }
        return new ActionMethod("/cruise/orders", Method.REDIRECT);
    }
}
