package com.example.dclassicsbook.util;

import android.util.Patterns;

// Each check returns an error message, or null when the value is OK.
public final class AuthValidator {

    public static final int MIN_PASSWORD_LENGTH = 8;

    private AuthValidator() { }

    public static String validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return "Please input email";
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()) {
            return "Email is not valid";
        }
        return null;
    }

    public static String validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            return "Please input password";
        }
        boolean longEnough = password.length() >= MIN_PASSWORD_LENGTH;
        boolean hasLetter = password.matches(".*[a-zA-Z].*");
        boolean hasDigit = password.matches(".*[0-9].*");
        if (!longEnough || !hasLetter || !hasDigit) {
            return "Password not strong enough";
        }
        return null;
    }

    public static String validatePasswordsMatch(String password, String confirmPassword) {
        if (confirmPassword == null || confirmPassword.isEmpty()) {
            return "Please input confirmation password";
        }
        if (!confirmPassword.equals(password)) {
            return "Confirmation password does not match";
        }
        return null;
    }
}
