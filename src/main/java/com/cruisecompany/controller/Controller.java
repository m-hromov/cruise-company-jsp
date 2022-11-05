package com.cruisecompany.controller;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionFactory;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/cruise/*", name = "Cruise")
@MultipartConfig
public class Controller extends HttpServlet {

    final static Logger logger = LogManager.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        Action action = ActionFactory.getInstance().get(request.getHttpServletMapping().getMatchValue());
        ActionMethod actionMethod = action.execute(request, response);
        String path = actionMethod.getPath();
        try {
            if (actionMethod.getMethod().equals(Method.REDIRECT)) response.sendRedirect(path);
            else request.getRequestDispatcher(path).forward(request, response);
        } catch (IOException | ServletException e) {
            logger.error("Error while redirecting or forwarding", e);
            throw new RuntimeException(e);
        }
    }
}
