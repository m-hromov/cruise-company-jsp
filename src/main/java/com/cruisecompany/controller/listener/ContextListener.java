package com.cruisecompany.controller.listener;

import com.cruisecompany.service.ServiceFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServiceFactory serviceFactory = new ServiceFactory();
        sce.getServletContext().setAttribute("ServiceFactory",serviceFactory);
    }
}
