package com.example.dclassicsbook.data.session;

public final class UserSession {

    private static final UserSession INSTANCE = new UserSession();

    private String username = "Joy Rochelle";

    private UserSession() { }

    public static UserSession getInstance() {
        return INSTANCE;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
