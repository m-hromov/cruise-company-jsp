package com.cruisecompany.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorAction implements Action{
    private final String strError;

    public ErrorAction(String strError) {
        this.strError = strError;
    }

    @Override
    public ActionMethod execute(HttpServletRequest request, HttpServletResponse response) {
            request.setAttribute("error",strError);
            return new ActionMethod("/WEB-INF/view/error.jsp", Method.REDIRECT);
    }
}
