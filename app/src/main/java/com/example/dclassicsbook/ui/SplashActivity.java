package com.example.dclassicsbook.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dclassicsbook.R;
import com.example.dclassicsbook.data.repository.AuthRepository;
import com.example.dclassicsbook.data.repository.AuthRepositoryProvider;
import com.example.dclassicsbook.data.session.SessionManager;
import com.example.dclassicsbook.ui.auth.LoginActivity;
import com.example.dclassicsbook.ui.auth.RegisterActivity;
import com.example.dclassicsbook.ui.main.MainActivity;

// Picks the first screen to show on launch.
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        AuthRepository authRepository = AuthRepositoryProvider.get(this);
        SessionManager sessionManager = new SessionManager(this);

        Intent next;
        if (!authRepository.isRegistered()) {
            next = new Intent(this, RegisterActivity.class);
        } else if (sessionManager.shouldAutoLogin()) {
            next = new Intent(this, MainActivity.class);
        } else {
            next = new Intent(this, LoginActivity.class);
        }
        startActivity(next);
        finish();
    }
}
