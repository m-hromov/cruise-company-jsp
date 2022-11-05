package com.cruisecompany.controller.action;

import com.cruisecompany.controller.action.forward.*;
import com.cruisecompany.controller.action.redirect.*;

import java.util.HashMap;
import java.util.Optional;

public class ActionFactory {
    private final HashMap<String,Action> actions;
    private static ActionFactory INSTANCE;
    private ActionFactory() {
        actions = new HashMap<>();
        actions.put("add_cruise",new LoadAddCruiseAction());
        actions.put("add_ship",new LoadAddShipAction());
        actions.put("add_station",new LoadAddStationAction());
        actions.put("add_staff",new LoadAddStaffAction());
        actions.put("edit_profile",new LoadEditProfileAction());
        actions.put("edit_money",new LoadEditMoneyAction());
        actions.put("find_cruise",new LoadFindCruiseAction());
        actions.put("user_orders",new LoadUserOrdersAction());
        actions.put("sign_in",new LoadSignInAction());
        actions.put("sign_up",new LoadSignUpAction());
        actions.put("orders",new LoadOrdersAction());

        actions.put("do_add_cruise",new AddCruiseAction());
        actions.put("do_add_ship",new AddShipAction());
        actions.put("do_add_staff",new AddStaffAction());
        actions.put("do_edit_profile_info",new EditProfileInfoAction());
        actions.put("do_edit_profile_document",new EditProfileDocumentAction());
        actions.put("do_edit_profile_password",new EditProfilePasswordAction());
        actions.put("do_edit_money",new EditMoneyAction());
        actions.put("do_find_cruise",new AddCruiseAction());
        actions.put("do_sign_in",new SignInAction());
        actions.put("do_sign_up",new SignUpAction());
        actions.put("do_confirm_order",new ConfirmOrderAction());
        actions.put("do_buy_cruise",new BuyCruiseAction());
        actions.put("do_block_order",new BlockOrderAction());
        actions.put("do_edit_cruise",new EditCruiseAction());
        actions.put("do_edit_ship",new EditShipAction());
        actions.put("do_sign_out",new SignOutAction());
        actions.put("do_pay",new PayForCruiseAction());
    }

    public static ActionFactory getInstance() {
        if(INSTANCE==null) INSTANCE = new ActionFactory();
        return INSTANCE;
    }

    public Action get(String name) {
        return Optional.ofNullable(actions.get(name)).orElse(new LoadErrorAction("404"));
    }
}
