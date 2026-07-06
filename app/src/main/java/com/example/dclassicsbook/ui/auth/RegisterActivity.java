package com.example.dclassicsbook.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dclassicsbook.R;
import com.example.dclassicsbook.data.session.CredentialStore;
import com.example.dclassicsbook.data.session.UserSession;
import com.example.dclassicsbook.ui.main.MainActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etConfirm;
    private boolean  passwordVisible = false;
    private boolean  confirmVisible  = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        applyWindowInsets();

        etUsername = findViewById(R.id.etUsername);
        etEmail    = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirm  = findViewById(R.id.etConfirmPassword);

        ImageView togglePass = findViewById(R.id.ivTogglePassword);
        togglePass.setOnClickListener(v -> passwordVisible =
                AuthUi.togglePasswordVisibility(etPassword, togglePass, passwordVisible));

        ImageView toggleConfirm = findViewById(R.id.ivToggleConfirm);
        toggleConfirm.setOnClickListener(v -> confirmVisible =
                AuthUi.togglePasswordVisibility(etConfirm, toggleConfirm, confirmVisible));

        findViewById(R.id.btnSignUp).setOnClickListener(v -> attemptRegister());
        findViewById(R.id.tvGoLogin).setOnClickListener(v -> goToLogin());
    }

    private void attemptRegister() {
        String username = etUsername.getText().toString().trim();
        String email    = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString();
        String confirm  = etConfirm.getText().toString();

        if (TextUtils.isEmpty(username)) {
            etUsername.setError(getString(R.string.err_username_required));
            etUsername.requestFocus();
            return;
        }
        if (CredentialStore.getInstance().exists(username)) {
            etUsername.setError(getString(R.string.err_username_taken));
            etUsername.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(email)) {
            etEmail.setError(getString(R.string.err_email_required));
            etEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError(getString(R.string.err_email_invalid));
            etEmail.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            etPassword.setError(getString(R.string.err_password_required));
            etPassword.requestFocus();
            return;
        }
        if (!AuthUi.isAlphanumeric(password)) {
            etPassword.setError(getString(R.string.err_password_alnum));
            etPassword.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(confirm)) {
            etConfirm.setError(getString(R.string.err_confirm_required));
            etConfirm.requestFocus();
            return;
        }
        if (!password.equals(confirm)) {
            etConfirm.setError(getString(R.string.err_password_mismatch));
            etConfirm.requestFocus();
            return;
        }

        CredentialStore.getInstance().register(username, password);
        UserSession.getInstance().setUsername(username);
        Toast.makeText(this, R.string.register_success, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void goToLogin() {
        finish();
    }

    private void applyWindowInsets() {
        View content = findViewById(R.id.registerContent);
        final int baseTop    = content.getPaddingTop();
        final int baseBottom = content.getPaddingBottom();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rootRegister), (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            content.setPadding(content.getPaddingLeft(), baseTop + bars.top,
                    content.getPaddingRight(), baseBottom + bars.bottom);
            return insets;
        });
    }
}
