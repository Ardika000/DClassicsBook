package com.example.dclassicsbook.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.dclassicsbook.R;
import com.example.dclassicsbook.data.repository.BookRepository;
import com.example.dclassicsbook.data.repository.StoreRepository;
import com.example.dclassicsbook.data.session.UserSession;
import com.example.dclassicsbook.ui.auth.LoginActivity;
import com.example.dclassicsbook.ui.detail.BookDetailActivity;
import com.example.dclassicsbook.ui.main.adapter.BookAdapter;
import com.example.dclassicsbook.ui.main.adapter.StoreAdapter;
import com.example.dclassicsbook.ui.widget.BottomNavBar;

import java.util.List;

/**
 * Home page (Figma: UX_DClassicalBook) — greeting, search bar, animated store
 * carousel, "Featured Books" list and a reusable bottom navigation bar.
 */
public class MainActivity extends AppCompatActivity {

    private ImageView[] dots = new ImageView[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        applyWindowInsets();
        setUpGreeting();
        setUpStoreCarousel();
        setUpFeaturedBooks();
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

    /** "Hello, [Username]" — username comes from the global session. */
    private void setUpGreeting() {
        TextView tvUserName = findViewById(R.id.tvUserName);
        tvUserName.setText(UserSession.getInstance().getUsername() + "!");
    }

    private void setUpStoreCarousel() {
        List<com.example.dclassicsbook.data.model.Store> stores = StoreRepository.getStores();
        final int count = stores.size();

        ViewPager2 pager = findViewById(R.id.vpStores);
        pager.setAdapter(new StoreAdapter(stores, store -> {
            // No Store detail screen yet — hook navigation here when available.
        }));

        // Slide + scale + fade animation on every page change.
        CompositePageTransformer transformer = new CompositePageTransformer();
        transformer.addTransformer(new MarginPageTransformer(dp(12)));
        transformer.addTransformer((page, position) -> {
            float closeness = 1f - Math.min(Math.abs(position), 1f);
            page.setScaleY(0.86f + closeness * 0.14f);
            page.setAlpha(0.4f + closeness * 0.6f);
        });
        pager.setPageTransformer(transformer);

        buildDots(count);
        updateControls(0, count);

        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                updateControls(position, count);
            }
        });

        ImageButton btnPrev = findViewById(R.id.btnStorePrev);
        ImageButton btnNext = findViewById(R.id.btnStoreNext);
        btnPrev.setOnClickListener(v -> {
            int current = pager.getCurrentItem();
            if (current > 0) pager.setCurrentItem(current - 1, true); // true = animate
        });
        btnNext.setOnClickListener(v -> {
            int current = pager.getCurrentItem();
            if (current < count - 1) pager.setCurrentItem(current + 1, true);
        });
    }

    private void buildDots(int count) {
        LinearLayout container = findViewById(R.id.storeDots);
        container.removeAllViews();
        dots = new ImageView[count];
        for (int i = 0; i < count; i++) {
            ImageView dot = new ImageView(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(dp(4), 0, dp(4), 0);
            dot.setLayoutParams(lp);
            dots[i] = dot;
            container.addView(dot);
        }
    }

    /** Highlights the active dot and dims the arrow that can't move further. */
    private void updateControls(int position, int count) {
        for (int i = 0; i < dots.length; i++) {
            dots[i].setImageResource(i == position ? R.drawable.dot_active : R.drawable.dot_inactive);
        }
        ImageButton btnPrev = findViewById(R.id.btnStorePrev);
        ImageButton btnNext = findViewById(R.id.btnStoreNext);
        btnPrev.setEnabled(position > 0);
        btnPrev.setAlpha(position > 0 ? 1f : 0.35f);
        btnNext.setEnabled(position < count - 1);
        btnNext.setAlpha(position < count - 1 ? 1f : 0.35f);
    }

    private void setUpFeaturedBooks() {
        RecyclerView rvBooks = findViewById(R.id.rvBooks);
        rvBooks.setLayoutManager(new LinearLayoutManager(this));
        rvBooks.setAdapter(new BookAdapter(BookRepository.getFeaturedBooks(), book -> {
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
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
            // BOOKS / STORES screens are not built yet — they stay on Home for now.
        });
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }
}
