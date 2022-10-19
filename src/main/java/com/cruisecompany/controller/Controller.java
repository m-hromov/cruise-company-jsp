package com.cruisecompany.controller;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionFactory;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/cruise/*", name = "Cruise")
@MultipartConfig
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = processRequest(request,response);
        request.getRequestDispatcher(path).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = processRequest(request,response);
        response.sendRedirect(path);
    }

    protected String processRequest(HttpServletRequest request, HttpServletResponse response){
        Action action = ActionFactory.getInstance().get(request.getHttpServletMapping().getMatchValue());
        ActionMethod actionMethod = action.execute(request, response);
        return actionMethod.getPath();
    }
}
