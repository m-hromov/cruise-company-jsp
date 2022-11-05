package com.cruisecompany.controller.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionOnCreateListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        String role = "VISITOR";
        se.getSession().setAttribute("role",role);
        se.getSession().setMaxInactiveInterval(600);
    }

}
