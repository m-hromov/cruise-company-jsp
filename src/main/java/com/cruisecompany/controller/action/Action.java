package com.cruisecompany.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
    /**
     * It is executed by controller, depending on action name.
     * @param request HttpServletRequest passed by controller
     * @param response HttpServletResponse passed by controller
     * @return ActionMethod that contains Method type and a path
     */
    ActionMethod execute(HttpServletRequest request, HttpServletResponse response);
}
