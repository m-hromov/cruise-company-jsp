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
        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response){
        Action action = ActionFactory.getInstance().get(request.getHttpServletMapping().getMatchValue());
        ActionMethod actionMethod = action.execute(request, response);
        String path = actionMethod.getPath();
        if(actionMethod.getMethod() == Method.FORWARD){
            try {
                request.getRequestDispatcher(path).forward(request,response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                response.sendRedirect(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
