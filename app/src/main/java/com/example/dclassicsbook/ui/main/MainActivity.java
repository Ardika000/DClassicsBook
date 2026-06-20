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
import com.example.dclassicsbook.data.repository.BookRepository;
import com.example.dclassicsbook.data.repository.StoreRepository;
import com.example.dclassicsbook.ui.detail.BookDetailActivity;
import com.example.dclassicsbook.ui.main.adapter.BookAdapter;
import com.example.dclassicsbook.ui.main.adapter.StoreAdapter;
import com.example.dclassicsbook.ui.widget.BottomNavBar;

/**
 * Home page — greeting header, search bar, "Our Stores" carousel,
 * "Timeless Classic Books" list and a reusable bottom navigation bar.
 * Built to match the Figma design (UX_DClassicalBook).
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        applyWindowInsets();
        setUpStores();
        setUpBooks();
        setUpBottomNav();
    }

    /**
     * targetSdk 35 forces edge-to-edge, so we pad the scrolling content below
     * the status bar and the nav bar above the gesture/navigation bar.
     */
    private void applyWindowInsets() {
        View content   = findViewById(R.id.homeContent);
        View bottomNav = findViewById(R.id.bottomNav);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rootHome), (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            content.setPadding(content.getPaddingLeft(), bars.top + dp(24),
                    content.getPaddingRight(), content.getPaddingBottom());
            bottomNav.setPadding(bottomNav.getPaddingLeft(), bottomNav.getPaddingTop(),
                    bottomNav.getPaddingRight(), bars.bottom);
            return insets;
        });
    }

    private void setUpStores() {
        RecyclerView rvStores = findViewById(R.id.rvStores);
        rvStores.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvStores.setAdapter(new StoreAdapter(StoreRepository.getStores(), store -> {
            // No Store detail screen yet — hook navigation here when available.
        }));
    }

    private void setUpBooks() {
        RecyclerView rvBooks = findViewById(R.id.rvBooks);
        rvBooks.setLayoutManager(new LinearLayoutManager(this));
        rvBooks.setAdapter(new BookAdapter(BookRepository.getBooks(), book -> {
            Intent intent = new Intent(MainActivity.this, BookDetailActivity.class);
            intent.putExtra("BOOK_DATA", book);
            startActivity(intent);
        }));
    }

    private void setUpBottomNav() {
        BottomNavBar bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setActiveItem(BottomNavBar.HOME);
        bottomNav.setOnItemSelectedListener(index -> {
            if (index == BottomNavBar.LOGOUT) {
                finish();
            }
            // BOOKS / STORES screens are not built yet — they stay on Home for now.
        });
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }
}
