package com.example.dclassicsbook.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.dclassicsbook.R;
import com.example.dclassicsbook.data.repository.AuthResult;
import com.example.dclassicsbook.util.AuthValidator;

public class LoginActivity extends BaseAuthActivity {

    private EditText etEmail;
    private EditText etPassword;
    private CheckBox cbRememberMe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        cbRememberMe = findViewById(R.id.cbRememberMe);

        bindPasswordToggle(etPassword, findViewById(R.id.ivTogglePassword));

        findViewById(R.id.btnLogin).setOnClickListener(v -> handleLogin());
        findViewById(R.id.tvGoToRegister).setOnClickListener(v -> goToRegister());
        findViewById(R.id.tvForgotPassword).setOnClickListener(
                v -> showMessage("Forgot password is not available yet."));
    }

    private void handleLogin() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        resetFieldError(etEmail, getString(R.string.auth_hint_email));
        resetFieldError(etPassword, getString(R.string.auth_hint_password));

        boolean valid = true;
        String emailError = AuthValidator.validateEmail(email);
        if (emailError != null) {
            setFieldError(etEmail, emailError);
            valid = false;
        }
        if (password.isEmpty()) {
            setFieldError(etPassword, "Please input password");
            valid = false;
        }
        if (!valid) {
            return;
        }

        AuthResult result = authRepository.login(email, password);
        if (!result.isSuccess()) {
            showMessage(result.getMessage());
            return;
        }
        goToHome(email, cbRememberMe.isChecked());
    }

    private void goToRegister() {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }
}
