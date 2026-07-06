package com.example.dclassicsbook.data.session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String PREFS_NAME = "session";
    private static final String KEY_LOGGED_IN = "logged_in";
    private static final String KEY_REMEMBER = "remember_me";
    private static final String KEY_USER_EMAIL = "user_email";

    private final SharedPreferences prefs;

    public SessionManager(Context context) {
        this.prefs = context.getApplicationContext()
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void startSession(String email, boolean rememberMe) {
        prefs.edit()
                .putBoolean(KEY_LOGGED_IN, true)
                .putBoolean(KEY_REMEMBER, rememberMe)
                .putString(KEY_USER_EMAIL, email)
                .apply();
    }

    public void logout() {
        prefs.edit().putBoolean(KEY_LOGGED_IN, false).apply();
    }

    public boolean isLoggedIn() {
        return prefs.getBoolean(KEY_LOGGED_IN, false);
    }

    public boolean isRememberMe() {
        return prefs.getBoolean(KEY_REMEMBER, false);
    }

    public String getUserEmail() {
        return prefs.getString(KEY_USER_EMAIL, "");
    }

    // Skip login next launch only if the user ticked "remember me".
    public boolean shouldAutoLogin() {
        return isLoggedIn() && isRememberMe();
    }
}
