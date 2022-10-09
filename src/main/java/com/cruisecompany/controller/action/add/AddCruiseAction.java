package com.cruisecompany.controller.action.add;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.entity.Cruise;
import com.cruisecompany.entity.Ship;
import com.cruisecompany.entity.Station;
import com.cruisecompany.service.CruiseService;
import com.cruisecompany.service.ServiceFactory;
import com.cruisecompany.service.ShipService;
import com.cruisecompany.service.StationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AddCruiseAction implements Action {
    @Override
    public ActionMethod execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameterMap().isEmpty()) {
            ShipService shipService = ServiceFactory.getInstance().getShipService();
            StationService stationService = ServiceFactory.getInstance().getStationService();
            request.setAttribute("listShip", shipService.getAll());
            request.setAttribute("listStation", stationService.getAll());
            return new ActionMethod("/WEB-INF/view/add_cruise.jsp", Method.FORWARD);
        }

        long shipId = Long.parseLong(request.getParameter("ship"));
        LocalTime timeDeparture = LocalTime.parse(request.getParameter("time_departure"));
        LocalDate dateDeparture = LocalDate.parse(request.getParameter("date_departure"));
        LocalDate dateArrival = LocalDate.parse(request.getParameter("date_arrival"));
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        String description = request.getParameter("description");
        String[] stations = request.getParameterValues("stations");
        List<Station> stationList = new ArrayList<>();
        for (String station : stations) {
            stationList.add(new Station().setId(Long.parseLong(station)));
        }
        Cruise cruise = new Cruise();
        cruise.setTimeDeparture(timeDeparture)
                .setDateDeparture(dateDeparture)
                .setDateArrival(dateArrival)
                .setDescription(description)
                .setPrice(price)
                .setShip(new Ship().setId(shipId))
                .setStationList(stationList);

        CruiseService cruiseService = ServiceFactory.getInstance().getCruiseService();
        cruiseService.addCruise(cruise);
        return new ActionMethod("/cruise/add_cruise", Method.REDIRECT);
    }
}
