package com.cruisecompany.controller.action.redirect;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.dto.PassengerDTO;
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
        try {
            PassengerDTO passengerDTO = (PassengerDTO) session.getAttribute("user");
            String requestRealPath = request.getSession().getServletContext().getRealPath("");
            Part part = request.getPart("document");
            passengerService.updateDocument(passengerDTO, part, requestRealPath);
            return new ActionMethod("/cruise/edit_profile", Method.REDIRECT);
        } catch (IOException | ServletException e) {
            logger.error("Unable to save an image!");
            session.setAttribute("error", 500);
            session.setAttribute("errorMsg", "Unable to save an image!");
            return new ActionMethod("/cruise/error", Method.REDIRECT);
        } catch (ServiceException e) {
            session.setAttribute("error", 500);
            session.setAttribute("errorMsg", "Something went wrong!");
            return new ActionMethod("/cruise/error", Method.REDIRECT);
        }
    }
}
