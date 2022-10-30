package com.cruisecompany.controller.action.redirect;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.entity.Station;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.ServiceFactory;
import com.cruisecompany.service.StationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddStationAction implements Action {
    @Override
    public ActionMethod execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ServiceFactory serviceFactory = (ServiceFactory) request.getServletContext()
                    .getAttribute("ServiceFactory");
            StationService stationService = serviceFactory.getStationService();

            Station station = mapStation(request);
            stationService.addStation(station);
            return new ActionMethod("/cruise/add_station", Method.REDIRECT);
        } catch (ServiceException e) {
            request.getSession().setAttribute("error", 500);
            request.getSession().setAttribute("errorMsg", "Something went wrong!");
            return new ActionMethod("/cruise/error", Method.REDIRECT);
        }
    }

    private Station mapStation(HttpServletRequest request) {
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        return new Station()
                .setCity(city)
                .setCountry(country);
    }
}
