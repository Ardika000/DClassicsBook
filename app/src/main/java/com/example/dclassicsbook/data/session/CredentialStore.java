package com.example.dclassicsbook.data.session;

import java.util.HashMap;
import java.util.Map;

public final class CredentialStore {

    private static final CredentialStore INSTANCE = new CredentialStore();

    /** Demo account, always available so the app can be logged into without registering first. */
    private static final String DEMO_USERNAME = "User";
    private static final String DEMO_PASSWORD = "User123";
    private static final String DEMO_EMAIL    = "user@dclassics.com";

    private final Map<String, Account> users = new HashMap<>();

    private CredentialStore() {
        users.put(DEMO_USERNAME, new Account(DEMO_PASSWORD, DEMO_EMAIL));
    }

    public static CredentialStore getInstance() {
        return INSTANCE;
    }

    public boolean exists(String username) {
        return users.containsKey(username);
    }

    public void register(String username, String password, String email) {
        users.put(username, new Account(password, email));
    }

    public boolean isValid(String username, String password) {
        Account account = users.get(username);
        return account != null && account.password.equals(password);
    }

    /** Email of a registered user, or an empty string when the user is unknown. */
    public String getEmail(String username) {
        Account account = users.get(username);
        return account == null ? "" : account.email;
    }

    private static final class Account {
        final String password;
        final String email;

        Account(String password, String email) {
            this.password = password;
            this.email = email;
        }
    }
}
