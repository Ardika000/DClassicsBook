package com.example.dclassicsbook.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.dclassicsbook.R;
import com.example.dclassicsbook.data.repository.AuthResult;
import com.example.dclassicsbook.util.AuthValidator;

public class RegisterActivity extends BaseAuthActivity {

    private EditText etName;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private CheckBox cbRememberMe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        cbRememberMe = findViewById(R.id.cbRememberMe);

        bindPasswordToggle(etPassword, findViewById(R.id.ivTogglePassword));
        bindPasswordToggle(etConfirmPassword, findViewById(R.id.ivToggleConfirmPassword));

        findViewById(R.id.btnRegister).setOnClickListener(v -> handleRegister());
        findViewById(R.id.tvGoToLogin).setOnClickListener(v -> goToLogin());
    }

    private void handleRegister() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String confirm = etConfirmPassword.getText().toString();

        resetFieldError(etName, getString(R.string.register_hint_name));
        resetFieldError(etEmail, getString(R.string.auth_hint_email));
        resetFieldError(etPassword, getString(R.string.auth_hint_password));
        resetFieldError(etConfirmPassword, getString(R.string.register_hint_confirm_password));

        boolean valid = true;
        if (name.isEmpty()) {
            setFieldError(etName, "Please input name");
            valid = false;
        }
        String emailError = AuthValidator.validateEmail(email);
        if (emailError != null) {
            setFieldError(etEmail, emailError);
            valid = false;
        }
        String passwordError = AuthValidator.validatePassword(password);
        if (passwordError != null) {
            setFieldError(etPassword, passwordError);
            valid = false;
        }
        String confirmError = AuthValidator.validatePasswordsMatch(password, confirm);
        if (confirmError != null) {
            setFieldError(etConfirmPassword, confirmError);
            valid = false;
        }
        if (!valid) {
            return;
        }

        AuthResult result = authRepository.register(name, email, password);
        if (!result.isSuccess()) {
            showMessage(result.getMessage());
            return;
        }
        goToHome(email, cbRememberMe.isChecked());
    }

    private void goToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
