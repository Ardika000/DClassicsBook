package com.example.dclassicsbook.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.dclassicsbook.R;
import com.example.dclassicsbook.data.repository.AuthRepository;
import com.example.dclassicsbook.data.repository.AuthRepositoryProvider;
import com.example.dclassicsbook.data.session.SessionManager;
import com.example.dclassicsbook.ui.main.MainActivity;

// Shared bits for the login & register screens.
public abstract class BaseAuthActivity extends AppCompatActivity {

    private static final int HINT_COLOR_NORMAL = 0xFF8A7765;

    protected AuthRepository authRepository;
    protected SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authRepository = AuthRepositoryProvider.get(this);
        sessionManager = new SessionManager(this);
    }

    protected void goToHome(String email, boolean rememberMe) {
        sessionManager.startSession(email, rememberMe);
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    protected void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void bindPasswordToggle(EditText field, ImageView toggle) {
        toggle.setOnClickListener(v -> {
            boolean hidden = field.getTransformationMethod() instanceof PasswordTransformationMethod;
            if (hidden) {
                field.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                toggle.setImageResource(R.drawable.ic_eye);
            } else {
                field.setTransformationMethod(PasswordTransformationMethod.getInstance());
                toggle.setImageResource(R.drawable.ic_eye_off);
            }
            if (field.getText() != null) {
                field.setSelection(field.getText().length());
            }
        });
    }

    // Red outline + show the error as placeholder text.
    protected void setFieldError(EditText field, String message) {
        applyBoxBackground((View) field.getParent(), R.drawable.bg_auth_input_error);
        field.setText("");
        field.setHint(message);
        field.setHintTextColor(ContextCompat.getColor(this, R.color.error_red));
    }

    protected void resetFieldError(EditText field, String normalHint) {
        applyBoxBackground((View) field.getParent(), R.drawable.bg_auth_input);
        field.setHint(normalHint);
        field.setHintTextColor(HINT_COLOR_NORMAL);
    }

    // setBackgroundResource clears padding, so put it back.
    private void applyBoxBackground(View box, int backgroundRes) {
        int l = box.getPaddingLeft(), t = box.getPaddingTop(),
                r = box.getPaddingRight(), b = box.getPaddingBottom();
        box.setBackgroundResource(backgroundRes);
        box.setPadding(l, t, r, b);
    }
}
