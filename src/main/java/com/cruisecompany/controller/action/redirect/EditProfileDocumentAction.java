package com.cruisecompany.controller.action.redirect;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.PassengerService;
import com.cruisecompany.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;

public class EditProfileDocumentAction implements Action {
    final static Logger logger = LogManager.getLogger(EditProfileDocumentAction.class);

    @Override
    public ActionMethod execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = (ServiceFactory) request.getServletContext()
                .getAttribute("ServiceFactory");

        PassengerService passengerService = serviceFactory.getPassengerService();
        HttpSession session = request.getSession();

        Passenger passenger = (Passenger) session.getAttribute("user");

        try {
            String requestRealPath = request.getServletContext().getRealPath("");
            Part part = request.getPart("document");
            passengerService.updateDocument(passenger, part, requestRealPath);
        } catch (IOException | ServletException e) {
            logger.error("Unable to save an image!");
            request.getSession().setAttribute("error", 500);
            request.getSession().setAttribute("errorMsg", "Unable to save an image!");
            return new ActionMethod("/cruise/error", Method.REDIRECT);
        } catch (ServiceException e) {
            request.getSession().setAttribute("error", 500);
            request.getSession().setAttribute("errorMsg", "Something went wrong!");
            return new ActionMethod("/cruise/error", Method.REDIRECT);
        }
        return new ActionMethod("/cruise/edit_profile", Method.REDIRECT);
    }
}
