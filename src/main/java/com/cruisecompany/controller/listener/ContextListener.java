package com.cruisecompany.controller.listener;

import com.cruisecompany.service.ServiceFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServiceFactory serviceFactory = new ServiceFactory();
        sce.getServletContext().setAttribute("ServiceFactory",serviceFactory);
    }
}
