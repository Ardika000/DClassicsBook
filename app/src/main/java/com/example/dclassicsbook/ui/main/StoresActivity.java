package com.example.dclassicsbook.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dclassicsbook.R;
import com.example.dclassicsbook.data.repository.StoreRepository;
import com.example.dclassicsbook.ui.auth.LoginActivity;
import com.example.dclassicsbook.ui.main.adapter.StoreListAdapter;
import com.example.dclassicsbook.ui.widget.BottomNavBar;

public class StoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores);

        applyWindowInsets();
        setUpStores();
        setUpBottomNav();
    }

    private void setUpStores() {
        RecyclerView rvStores = findViewById(R.id.rvStores);
        rvStores.setLayoutManager(new LinearLayoutManager(this));
        rvStores.setAdapter(new StoreListAdapter(StoreRepository.getStores(), store -> { }));
    }

    private void setUpBottomNav() {
        BottomNavBar bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setActiveItem(BottomNavBar.STORES);
        bottomNav.setOnItemSelectedListener(index -> {
            switch (index) {
                case BottomNavBar.HOME:
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                    break;
                case BottomNavBar.BOOKS:
                    startActivity(new Intent(this, BooksActivity.class));
                    finish();
                    break;
                case BottomNavBar.LOGOUT:
                    Intent login = new Intent(this, LoginActivity.class);
                    login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(login);
                    finish();
                    break;
                default:
                    break;
            }
        });
    }

    private void applyWindowInsets() {
        View content   = findViewById(R.id.storesContent);
        View bottomNav = findViewById(R.id.bottomNav);
        final int baseTop = content.getPaddingTop();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rootStores), (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            content.setPadding(content.getPaddingLeft(), baseTop + bars.top,
                    content.getPaddingRight(), content.getPaddingBottom());
            bottomNav.setPadding(bottomNav.getPaddingLeft(), bottomNav.getPaddingTop(),
                    bottomNav.getPaddingRight(), bars.bottom);
            return insets;
        });
    }
}
