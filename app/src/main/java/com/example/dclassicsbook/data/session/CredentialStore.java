package com.example.dclassicsbook.data.session;

import java.util.HashMap;
import java.util.Map;

public final class CredentialStore {

    private static final CredentialStore INSTANCE = new CredentialStore();

    private final Map<String, String> users = new HashMap<>();

    private CredentialStore() { }

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
