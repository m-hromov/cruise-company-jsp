package com.cruisecompany.controller.action.forward;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoadErrorAction implements Action {
    private final String strError;

    public LoadErrorAction(String strError) {
        this.strError = strError;
    }

    @Override
    public ActionMethod execute(HttpServletRequest request, HttpServletResponse response) {
            request.setAttribute("error",strError);
            return new ActionMethod("/WEB-INF/view/error.jsp", Method.REDIRECT);
    }
}
