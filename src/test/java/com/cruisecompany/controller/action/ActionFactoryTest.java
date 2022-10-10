package com.cruisecompany.controller.action;

import com.cruisecompany.controller.action.add.*;
import com.cruisecompany.controller.action.authorization.SignInAction;
import com.cruisecompany.controller.action.authorization.SignOutAction;
import com.cruisecompany.controller.action.authorization.SignUpAction;
import com.cruisecompany.controller.action.edit.*;
import com.cruisecompany.controller.action.show.FindCruiseAction;
import com.cruisecompany.controller.action.show.OrdersAction;
import com.cruisecompany.controller.action.show.UserOrdersAction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ActionFactoryTest {

    static Stream<Arguments> actionFactories() {
        return Stream.of(
                arguments("add_cruise", new AddCruiseAction()),
                arguments("add_cruise", new AddCruiseAction()),
                arguments("add_ship", new AddShipAction()),
                arguments("add_station", new AddStationAction()),
                arguments("add_staff", new AddStaffAction()),
                arguments("buy_cruise", new BuyCruiseAction()),
                arguments("block_order", new BlockOrderAction()),
                arguments("edit_cruise", new EditCruiseAction()),
                arguments("edit_profile", new EditProfileAction()),
                arguments("edit_ship", new EditShipAction()),
                arguments("edit_money", new EditMoneyAction()),
                arguments("confirm_order", new ConfirmOrderAction()),
                arguments("find_cruise", new FindCruiseAction()),
                arguments("user_orders", new UserOrdersAction()),
                arguments("sign_in", new SignInAction()),
                arguments("sign_out", new SignOutAction()),
                arguments("sign_up", new SignUpAction()),
                arguments("orders", new OrdersAction()),
                arguments("pay", new PayForCruiseAction())
        );
    }

    @Test
    void testGetInstanceIsTheSame() {
        assertSame(ActionFactory.getInstance(), ActionFactory.getInstance());
    }

    @ParameterizedTest
    @MethodSource("actionFactories")
    void testGettingActionFactories(String name, Action action) {
        ActionFactory actionFactory = ActionFactory.getInstance();
        assertSame(actionFactory.get(name).getClass(), action.getClass());
    }
}