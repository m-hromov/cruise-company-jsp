package com.cruisecompany.controller;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.FindCruiseAction;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/cruise/*")
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Action action = getAction(request);
        action.execute(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/cruise/find_cruise");
    }

    protected Action getAction(HttpServletRequest request){
        System.out.println(request.getHttpServletMapping().getMatchValue());
        return new FindCruiseAction();
    }
}
