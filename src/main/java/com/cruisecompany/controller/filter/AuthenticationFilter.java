package com.cruisecompany.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

@WebFilter(filterName = "AuthenticationFilter")
public class AuthenticationFilter implements Filter {
    private static HashMap<String, String> urisRestricted;
    private static HashSet<String> urisCommon;

    public void init(FilterConfig config) throws ServletException {
        urisRestricted = new HashMap<>();
        urisRestricted.put("/cruise/add_cruise", "ADMIN");
        urisRestricted.put("/cruise/add_ship", "ADMIN");
        urisRestricted.put("/cruise/add_staff", "ADMIN");
        urisRestricted.put("/cruise/add_station", "ADMIN");
        urisRestricted.put("/cruise/edit_cruise", "ADMIN");
        urisRestricted.put("/cruise/edit_ship", "ADMIN");
        urisRestricted.put("/cruise/confirm_order", "ADMIN");
        urisRestricted.put("/cruise/block_order", "ADMIN");
        urisRestricted.put("/cruise/orders", "ADMIN");
        urisRestricted.put("/cruise/find_cruise", "ADMIN||USER||VISITOR");
        urisRestricted.put("/cruise/sign_out", "ADMIN||USER||VISITOR");
        urisRestricted.put("/cruise/edit_profile", "USER");
        urisRestricted.put("/cruise/user_orders", "USER");
        urisRestricted.put("/cruise/buy_cruise", "USER");
        urisRestricted.put("/cruise/edit_money", "USER");
        urisRestricted.put("/cruise/pay", "USER");
        urisRestricted.put("/cruise/sign_in", "VISITOR");
        urisRestricted.put("/cruise/sign_up", "VISITOR");

        urisCommon = new HashSet<>();
        urisCommon.add("/index.jsp");
        urisCommon.add("/");
        urisCommon.add("");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();

        String role = (String) session.getAttribute("role");

        String requestURI = httpServletRequest.getRequestURI();
        Optional<String> access = Optional.ofNullable(urisRestricted.get(requestURI));
        if (access.isPresent()) {
            if (access.get().contains(role)) {
                chain.doFilter(request, response);
                return;
            }
        }
        boolean isStaticResource = httpServletRequest.getRequestURI().startsWith("/resources/");
        if (urisCommon.contains(requestURI) || isStaticResource) {
            chain.doFilter(request, response);
            return;
        }
        request.setAttribute("errorMsg", "Access denied");
        httpServletRequest.getRequestDispatcher("/WEB-INF/view/error.jsp").include(request, response);
    }
}
