package com.cruisecompany.controller.filter;

import com.cruisecompany.dto.PassengerDTO;

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

    public void init(FilterConfig config) {
        urisRestricted = new HashMap<>();
        urisRestricted.put("/cruise/add_cruise", "ADMIN");
        urisRestricted.put("/cruise/add_ship", "ADMIN");
        urisRestricted.put("/cruise/add_staff", "ADMIN");
        urisRestricted.put("/cruise/add_station", "ADMIN");
        urisRestricted.put("/cruise/edit_cruise", "ADMIN");
        urisRestricted.put("/cruise/edit_ship", "ADMIN");
        urisRestricted.put("/cruise/orders", "ADMIN");
        urisRestricted.put("/cruise/find_cruise", "ADMIN||USER||VISITOR");
        urisRestricted.put("/cruise/edit_profile", "USER");
        urisRestricted.put("/cruise/user_orders", "USER");
        urisRestricted.put("/cruise/edit_money", "USER");
        urisRestricted.put("/cruise/sign_in", "VISITOR");
        urisRestricted.put("/cruise/sign_up", "VISITOR");

        urisRestricted.put("/cruise/do_add_cruise", "ADMIN");
        urisRestricted.put("/cruise/do_add_ship", "ADMIN");
        urisRestricted.put("/cruise/do_add_staff", "ADMIN");
        urisRestricted.put("/cruise/do_add_station", "ADMIN");
        urisRestricted.put("/cruise/do_edit_cruise", "ADMIN");
        urisRestricted.put("/cruise/do_edit_ship", "ADMIN");
        urisRestricted.put("/cruise/do_confirm_order", "ADMIN");
        urisRestricted.put("/cruise/do_block_order", "ADMIN");
        urisRestricted.put("/cruise/do_edit_profile_info", "USER");
        urisRestricted.put("/cruise/do_edit_profile_document", "USER");
        urisRestricted.put("/cruise/do_edit_profile_password", "USER");
        urisRestricted.put("/cruise/do_buy_cruise", "USER");
        urisRestricted.put("/cruise/do_edit_money", "USER");
        urisRestricted.put("/cruise/do_pay", "USER");
        urisRestricted.put("/cruise/do_sign_in", "VISITOR");
        urisRestricted.put("/cruise/do_sign_up", "VISITOR");
        urisRestricted.put("/cruise/do_sign_out", "ADMIN||USER||VISITOR");

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
        boolean isStaticResource = requestURI.startsWith("/resources/");
        boolean isSecuredFile = requestURI.startsWith("/secured_files/");
        if (urisCommon.contains(requestURI) || isStaticResource || (isSecuredFile && role.equals("ADMIN"))) {
            chain.doFilter(request, response);
            return;
        } else if (isSecuredFile && role.equals("USER")) {
            PassengerDTO passengerDTO = (PassengerDTO) session.getAttribute("user");
            String docPath = passengerDTO.getDocumentPath();
            boolean allowed = docPath.endsWith(requestURI.substring(requestURI.lastIndexOf("/") + 1));
            if (allowed) {
                chain.doFilter(request, response);
                return;
            }
        }
        request.setAttribute("errorMsg", "Access denied");
        httpServletRequest.getRequestDispatcher("/WEB-INF/view/error.jsp").include(request, response);
    }
}
