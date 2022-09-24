package com.cruisecompany.controller.action.edit;

import com.cruisecompany.controller.action.Action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditProfileAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/edit_profile.jsp");
        try {
            rd.forward(request,response);
        } catch (ServletException e) {
            System.out.println(e);

        } catch (IOException e) {
            System.out.println("fail2");
        }
    }
}
