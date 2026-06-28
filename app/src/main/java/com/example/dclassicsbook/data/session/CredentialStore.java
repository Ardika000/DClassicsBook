package com.example.dclassicsbook.data.session;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple in-memory credential store (a process-wide "global variable") used to
 * link Register → Login: Register saves the username/password here, Login checks
 * against it.
 *
 * <p>NOTE: this lives in memory only, so accounts reset when the app process is
 * killed. That's the simplest option that satisfies the within-session
 * register-then-login flow. Swap the map for SharedPreferences if you need the
 * accounts to survive an app restart.
 */
public final class CredentialStore {

    private static final CredentialStore INSTANCE = new CredentialStore();

    /** username -> password */
    private final Map<String, String> users = new HashMap<>();

    private CredentialStore() { }

    public static CredentialStore getInstance() {
        return INSTANCE;
    }

    /** @return true if the username is already registered. */
    public boolean exists(String username) {
        return users.containsKey(username);
    }

    /** Saves a new account. Call {@link #exists(String)} first to avoid overwriting. */
    public void register(String username, String password) {
        users.put(username, password);
    }

    /** @return true if the username exists and the password matches. */
    public boolean isValid(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }
}
