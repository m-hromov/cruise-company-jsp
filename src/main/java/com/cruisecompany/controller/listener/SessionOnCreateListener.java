package com.cruisecompany.controller.listener;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

@WebListener
public class SessionOnCreateListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        String role = "VISITOR";
        se.getSession().setAttribute("role",role);
        se.getSession().setMaxInactiveInterval(600);
    }

}
