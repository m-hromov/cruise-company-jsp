package com.cruisecompany.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;

//@WebFilter(urlPatterns = "/*",filterName = "AuthorizationFilter")
public class AuthenticationFilter implements Filter {
    private static HashSet<String> adminURIs;
    private static HashSet<String> userURIs;
    private static HashSet<String> visitorURIs;
    public void init(FilterConfig config) throws ServletException {
        adminURIs = new HashSet<>();
        userURIs = new HashSet<>();
        visitorURIs = new HashSet<>();
        adminURIs.add("/");

    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();

        String role;
        if(session.isNew()){
            role = "visitor";
            session.setAttribute("role",role);
        } else {
            role = (String) session.getAttribute("role");
        }

        String requestURI = httpServletRequest.getRequestURI();
        System.out.println(requestURI);
        chain.doFilter(request, response);
    }
}
