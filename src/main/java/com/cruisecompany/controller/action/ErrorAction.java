package com.cruisecompany.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorAction implements Action{
    private String strError;

    public ErrorAction(String strError) {
        this.strError = strError;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("error",strError);
            response.sendRedirect("/error.jsp");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
