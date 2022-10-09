package com.cruisecompany.controller.action.add;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.entity.Station;
import com.cruisecompany.service.ServiceFactory;
import com.cruisecompany.service.StationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddStationAction implements Action {
    @Override
    public ActionMethod execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameterMap().isEmpty()) {
            return new ActionMethod("/WEB-INF/view/add_station.jsp", Method.FORWARD);
        }
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        Station station = new Station();
        station.setCity(city)
                .setCountry(country);

        StationService stationService = ServiceFactory.getInstance().getStationService();
        stationService.addStation(station);

        return new ActionMethod("/cruise/add_station", Method.REDIRECT);
    }
}
