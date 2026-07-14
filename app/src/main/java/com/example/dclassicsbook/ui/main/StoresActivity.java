package com.example.dclassicsbook.ui.main;

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
import com.example.dclassicsbook.ui.common.LogoutDialog;
import com.example.dclassicsbook.ui.common.TabTransition;
import com.example.dclassicsbook.ui.main.adapter.StoreListAdapter;
import com.example.dclassicsbook.ui.widget.BottomNavBar;

public class StoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        TabTransition.apply(this);
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
                    TabTransition.switchTo(this, MainActivity.class);
                    break;
                case BottomNavBar.BOOKS:
                    TabTransition.switchTo(this, BooksActivity.class);
                    break;
                case BottomNavBar.LOGOUT:
                    LogoutDialog.show(this);
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
