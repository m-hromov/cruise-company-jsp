package com.cruisecompany.controller.action;

import com.cruisecompany.controller.action.forward.*;
import com.cruisecompany.controller.action.redirect.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ActionFactoryTest {
    ActionFactory actionFactory = ActionFactory.getInstance();
    @Test
    void testGetInstance() {
        assertNotNull(actionFactory);
        assertSame(actionFactory,ActionFactory.getInstance());
    }

    @ParameterizedTest
    @MethodSource("actionNames")
    void testGetActions(String name, Action action) {
        assertSame(actionFactory.get(name).getClass(), action.getClass());
    }
    static Stream<Arguments> actionNames(){
        return Stream.of(
                Arguments.arguments("add_cruise",new LoadAddCruiseAction()),
                Arguments.arguments("add_ship",new LoadAddShipAction()),
                Arguments.arguments("add_station",new LoadAddStationAction()),
                Arguments.arguments("add_staff",new LoadAddStaffAction()),
                Arguments.arguments("edit_profile",new LoadEditProfileAction()),
                Arguments.arguments("edit_money",new LoadEditMoneyAction()),
                Arguments.arguments("find_cruise",new LoadFindCruiseAction()),
                Arguments.arguments("user_orders",new LoadUserOrdersAction()),
                Arguments.arguments("sign_in",new LoadSignInAction()),
                Arguments.arguments("sign_up",new LoadSignUpAction()),
                Arguments.arguments("orders",new LoadOrdersAction()),
                Arguments.arguments("do_add_cruise",new AddCruiseAction()),
                Arguments.arguments("do_add_ship",new AddShipAction()),
                Arguments.arguments("do_add_staff",new AddStaffAction()),
                Arguments.arguments("do_edit_profile",new EditProfileAction()),
                Arguments.arguments("do_edit_money",new EditMoneyAction()),
                Arguments.arguments("do_find_cruise",new AddCruiseAction()),
                Arguments.arguments("do_sign_in",new SignInAction()),
                Arguments.arguments("do_sign_up",new SignUpAction()),
                Arguments.arguments("do_confirm_order",new ConfirmOrderAction()),
                Arguments.arguments("do_buy_cruise",new BuyCruiseAction()),
                Arguments.arguments("do_block_order",new BlockOrderAction()),
                Arguments.arguments("do_edit_cruise",new EditCruiseAction()),
                Arguments.arguments("do_edit_ship",new EditShipAction()),
                Arguments.arguments("do_pay",new PayForCruiseAction()),
                Arguments.arguments("do_sign_out",new SignOutAction()));
    }
}