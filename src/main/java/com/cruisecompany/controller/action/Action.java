package com.cruisecompany.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
    ActionMethod execute(HttpServletRequest request, HttpServletResponse response);
}
