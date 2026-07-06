package com.example.dclassicsbook.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private boolean  passwordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        applyWindowInsets();

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        ImageView toggle = findViewById(R.id.ivTogglePassword);
        toggle.setOnClickListener(v -> passwordVisible =
                AuthUi.togglePasswordVisibility(etPassword, toggle, passwordVisible));

        findViewById(R.id.btnLogin).setOnClickListener(v -> attemptLogin());
        findViewById(R.id.tvGoRegister).setOnClickListener(v ->
                startActivity(new Intent(this, RegisterActivity.class)));
        findViewById(R.id.tvForgot).setOnClickListener(v ->
                Toast.makeText(this, "Password reset is not available yet", Toast.LENGTH_SHORT).show());
    }

    private void attemptLogin() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString();

        if (TextUtils.isEmpty(username)) {
            etUsername.setError(getString(R.string.err_username_required));
            etUsername.requestFocus();
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
        if (!CredentialStore.getInstance().exists(username)) {
            etUsername.setError(getString(R.string.err_username_not_found));
            etUsername.requestFocus();
            return;
        }
        if (!CredentialStore.getInstance().isValid(username, password)) {
            etPassword.setError(getString(R.string.err_password_incorrect));
            etPassword.requestFocus();
            return;
        }

        UserSession.getInstance().setUsername(username);
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void applyWindowInsets() {
        View content = findViewById(R.id.loginContent);
        final int baseTop    = content.getPaddingTop();
        final int baseBottom = content.getPaddingBottom();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rootLogin), (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            content.setPadding(content.getPaddingLeft(), baseTop + bars.top,
                    content.getPaddingRight(), baseBottom + bars.bottom);
            return insets;
        });
    }
}
