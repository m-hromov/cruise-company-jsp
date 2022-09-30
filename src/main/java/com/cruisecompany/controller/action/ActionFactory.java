package com.cruisecompany.controller.action;

import com.cruisecompany.controller.action.add.*;
import com.cruisecompany.controller.action.authorization.SignInAction;
import com.cruisecompany.controller.action.authorization.SignOutAction;
import com.cruisecompany.controller.action.authorization.SignUpAction;
import com.cruisecompany.controller.action.edit.*;
import com.cruisecompany.controller.action.show.FindCruiseAction;
import com.cruisecompany.controller.action.show.OrdersAction;
import com.cruisecompany.controller.action.show.UserOrdersAction;

import java.util.HashMap;
import java.util.Optional;

public class ActionFactory {
    private final HashMap<String,Action> actions;
    private static ActionFactory INSTANCE;
    private ActionFactory() {
        actions = new HashMap<>();
        actions.put("add_cruise",new AddCruiseAction());
        actions.put("add_ship",new AddShipAction());
        actions.put("add_station",new AddStationAction());
        actions.put("add_staff",new AddStaffAction());
        actions.put("buy_cruise",new BuyCruiseAction());
        actions.put("block_order",new BlockOrderAction());
        actions.put("edit_cruise",new EditCruiseAction());
        actions.put("edit_profile",new EditProfileAction());
        actions.put("edit_ship",new EditShipAction());
        actions.put("edit_money",new EditMoneyAction());
        actions.put("confirm_order",new ConfirmOrderAction());
        actions.put("find_cruise",new FindCruiseAction());
        actions.put("user_orders",new UserOrdersAction());
        actions.put("sign_in",new SignInAction());
        actions.put("sign_out",new SignOutAction());
        actions.put("sign_up",new SignUpAction());
        actions.put("orders",new OrdersAction());
        actions.put("pay",new PayForCruiseAction());
    }

    public static ActionFactory getInstance() {
        if(INSTANCE==null) INSTANCE = new ActionFactory();
        return INSTANCE;
    }

    public Action get(String name) {
        return Optional.ofNullable(actions.get(name)).orElse(new ErrorAction("Page not found"));
    }
}
