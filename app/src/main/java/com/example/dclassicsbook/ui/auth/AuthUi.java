package com.example.dclassicsbook.ui.auth;

import android.text.InputType;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.dclassicsbook.R;

/**
 * Small shared helpers for the auth screens (Login / Register) so the
 * password-toggle and username logic isn't duplicated.
 */
final class AuthUi {

    private AuthUi() { }

    /**
     * Flips a password field between masked and visible, swaps the eye icon and
     * keeps the cursor at the end.
     *
     * @return the new "visible" state.
     */
    static boolean togglePasswordVisibility(EditText field, ImageView toggle, boolean currentlyVisible) {
        boolean nowVisible = !currentlyVisible;
        if (nowVisible) {
            field.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            toggle.setImageResource(R.drawable.ic_eye);
        } else {
            field.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            toggle.setImageResource(R.drawable.ic_eye_off);
        }
        field.setTypeface(field.getTypeface()); // keep the same font after type change
        field.setSelection(field.getText().length());
        return nowVisible;
    }

    static boolean isAlphanumeric(String password) {
        return password.matches("[A-Za-z0-9]+")   // only letters & digits
                && password.matches(".*[a-z].*") // has a lowercase
                && password.matches(".*[A-Z].*") // has a uppercase
                && password.matches(".*[0-9].*");   // has a digit
    }
}
