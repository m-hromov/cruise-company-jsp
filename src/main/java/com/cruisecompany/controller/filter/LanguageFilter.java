package com.cruisecompany.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.Optional;

@WebFilter(filterName = "LanguageFilter")
public class LanguageFilter implements Filter {
    private static final Locale EN_US = new Locale("en","US");
    private static final Locale UA = new Locale("ua");
    private static final Locale DEFAULT = EN_US;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession(false);
        String language = (String) session.getAttribute("lang");
        if(language==null) {
            Locale locale = request.getLocale();
            Optional<String> country = Optional.ofNullable(locale.getLanguage());
            country.ifPresentOrElse(s -> {
                if (s.equals("en"))
                    session.setAttribute("lang", EN_US.getLanguage());
                if (s.equals("ua"))
                    session.setAttribute("lang", UA.getLanguage());
            }, () -> session.setAttribute("lang", DEFAULT));
        }

        String selectedLang = request.getParameter("lang");
        if(selectedLang!=null) {
            session.setAttribute("lang", selectedLang);
        }
        chain.doFilter(request, response);
    }
}
