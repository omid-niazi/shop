package ir.bootcamp.oneline.shop.service;

import ir.bootcamp.oneline.shop.model.User;

public class AuthenticationContext {
    private static User loggedInUser;

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void looggedIn(User user) {
        loggedInUser = user;
    }

    public static void logout() {
        loggedInUser = null;
    }

}
