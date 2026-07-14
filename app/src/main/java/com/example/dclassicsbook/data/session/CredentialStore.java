package com.example.dclassicsbook.data.session;

import java.util.HashMap;
import java.util.Map;

public final class CredentialStore {

    private static final CredentialStore INSTANCE = new CredentialStore();

    /** Demo account, always available so the app can be logged into without registering first. */
    private static final String DEMO_USERNAME = "User";
    private static final String DEMO_PASSWORD = "User123";

    private final Map<String, String> users = new HashMap<>();

    private CredentialStore() {
        users.put(DEMO_USERNAME, DEMO_PASSWORD);
    }

    public static CredentialStore getInstance() {
        return INSTANCE;
    }

    public boolean exists(String username) {
        return users.containsKey(username);
    }

    public void register(String username, String password) {
        users.put(username, password);
    }

    public boolean isValid(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }
}
