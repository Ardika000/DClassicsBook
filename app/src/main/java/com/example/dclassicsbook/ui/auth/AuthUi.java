package com.example.dclassicsbook.ui.auth;

import android.text.InputType;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.dclassicsbook.R;

final class AuthUi {

    private AuthUi() { }

    static boolean togglePasswordVisibility(EditText field, ImageView toggle, boolean currentlyVisible) {
        boolean nowVisible = !currentlyVisible;
        if (nowVisible) {
            field.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            toggle.setImageResource(R.drawable.ic_eye);
        } else {
            field.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            toggle.setImageResource(R.drawable.ic_eye_off);
        }
        field.setTypeface(field.getTypeface());
        field.setSelection(field.getText().length());
        return nowVisible;
    }

    static boolean isAlphanumeric(String password) {
        return password.matches("[A-Za-z0-9]+")
                && password.matches(".*[a-z].*")
                && password.matches(".*[A-Z].*")
                && password.matches(".*[0-9].*");
    }
}
