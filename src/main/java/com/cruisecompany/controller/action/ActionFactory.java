package com.cruisecompany.controller.action;

import java.util.HashMap;
import java.util.Optional;

public class ActionFactory {
    private final HashMap<String,Action> actions;
    private static ActionFactory INSTANCE;
    private ActionFactory() {
        actions = new HashMap<>();
        actions.put("sign_in",new SignInAction());
        actions.put("sign_up",new SignUpAction());
        actions.put("sign_out",new SignOutAction());
        actions.put("find_cruise",new FindCruiseAction());
    }

    public static ActionFactory getInstance() {
        if(INSTANCE==null) INSTANCE = new ActionFactory();
        return INSTANCE;
    }

    public Action get(String name) {
        return Optional.ofNullable(actions.get(name)).orElse(new ErrorAction("Page not found"));
    }
}
