package com.example.dclassicsbook.data.session;

public final class UserSession {

    private static final UserSession INSTANCE = new UserSession();

    private String username = "Joy Rochelle";
    private String email    = "joy.rochelle@dclassics.com";

    private UserSession() { }

    public static UserSession getInstance() {
        return INSTANCE;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    /** Records who is signed in; both values are shown in the profile popup. */
    public void setAccount(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
