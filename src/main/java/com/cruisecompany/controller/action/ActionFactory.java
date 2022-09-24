package com.cruisecompany.controller.action;

import com.cruisecompany.controller.action.add.AddCruiseAction;
import com.cruisecompany.controller.action.authentication.SignInAction;
import com.cruisecompany.controller.action.authentication.SignOutAction;
import com.cruisecompany.controller.action.authentication.SignUpAction;
import com.cruisecompany.controller.action.edit.EditCruiseAction;
import com.cruisecompany.controller.action.edit.EditProfileAction;
import com.cruisecompany.controller.action.edit.EditShipAction;
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
        actions.put("edit_cruise",new EditCruiseAction());
        actions.put("edit_profile",new EditProfileAction());
        actions.put("edit_ship",new EditShipAction());
        actions.put("find_cruise",new FindCruiseAction());
        actions.put("user_orders",new UserOrdersAction());
        actions.put("sign_in",new SignInAction());
        actions.put("sign_out",new SignOutAction());
        actions.put("sign_up",new SignUpAction());
        actions.put("orders",new OrdersAction());
    }

    public static ActionFactory getInstance() {
        if(INSTANCE==null) INSTANCE = new ActionFactory();
        return INSTANCE;
    }

    public Action get(String name) {
        return Optional.ofNullable(actions.get(name)).orElse(new ErrorAction("Page not found"));
    }
}
