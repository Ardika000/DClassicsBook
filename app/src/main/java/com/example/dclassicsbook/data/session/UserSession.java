package com.example.dclassicsbook.data.session;

/**
 * Process-wide holder for the logged-in user's data — the "global variable"
 * referenced by the spec. The Login page will call {@link #setUsername(String)}
 * on a successful login; the Home page reads it for the greeting.
 *
 * <p>Defaults to a sample name so the Home page still renders correctly before
 * the Login page is built.
 */
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
