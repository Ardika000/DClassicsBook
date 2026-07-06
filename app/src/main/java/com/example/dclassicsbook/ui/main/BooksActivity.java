package com.example.dclassicsbook.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dclassicsbook.R;
import com.example.dclassicsbook.data.model.Book;
import com.example.dclassicsbook.data.repository.BookRepository;
import com.example.dclassicsbook.ui.detail.BookDetailActivity;
import com.example.dclassicsbook.ui.main.adapter.BookCardAdapter;
import com.example.dclassicsbook.ui.auth.LoginActivity;
import com.example.dclassicsbook.ui.widget.BottomNavBar;

import java.util.ArrayList;
import java.util.List;

public class BooksActivity extends AppCompatActivity {

    private static final int FAVOURITE_WIDTH_DP = 116;
    private static final int GRID_COLUMNS = 3;

    private RecyclerView rvFavourite;
    private RecyclerView rvGrid;
    private TextView tabFiction;
    private TextView tabNonFiction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        applyWindowInsets();

        rvFavourite = findViewById(R.id.rvFavourite);
        rvGrid      = findViewById(R.id.rvBooksGrid);
        tabFiction    = findViewById(R.id.tabFiction);
        tabNonFiction = findViewById(R.id.tabNonFiction);

        rvFavourite.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvGrid.setLayoutManager(new GridLayoutManager(this, GRID_COLUMNS));

        tabFiction.setOnClickListener(v -> selectTab(true));
        tabNonFiction.setOnClickListener(v -> selectTab(false));

        setUpBottomNav();
        setUpFavourite();
        selectTab(true);
    }

    private void setUpFavourite() {
        List<Book> favourite = new ArrayList<>();
        addTop(favourite, BookRepository.getFictionBooks(), 3);
        addTop(favourite, BookRepository.getNonFictionBooks(), 3);
        rvFavourite.setAdapter(new BookCardAdapter(favourite, this::openDetail, FAVOURITE_WIDTH_DP));
    }

    private void addTop(List<Book> target, List<Book> source, int count) {
        for (int i = 0; i < Math.min(count, source.size()); i++) {
            target.add(source.get(i));
        }
    }

    private void selectTab(boolean fiction) {
        styleTabs(fiction);

        List<Book> category = fiction
                ? BookRepository.getFictionBooks()
                : BookRepository.getNonFictionBooks();

        int favCount = Math.min(3, category.size());
        List<Book> grid = category.size() > favCount
                ? new ArrayList<>(category.subList(favCount, category.size()))
                : new ArrayList<>(category);

        rvGrid.setAdapter(new BookCardAdapter(grid, this::openDetail, 0));
    }

    private void styleTabs(boolean fictionActive) {
        tabFiction.setBackgroundResource(fictionActive ? R.drawable.bg_segment_active : 0);
        tabFiction.setTextColor(ContextCompat.getColor(this,
                fictionActive ? R.color.bg_ivory : R.color.brown_dark));
        tabNonFiction.setBackgroundResource(!fictionActive ? R.drawable.bg_segment_active : 0);
        tabNonFiction.setTextColor(ContextCompat.getColor(this,
                !fictionActive ? R.color.bg_ivory : R.color.brown_dark));
    }

    private void openDetail(Book book) {
        Intent intent = new Intent(this, BookDetailActivity.class);
        intent.putExtra("BOOK_DATA", book);
        startActivity(intent);
    }

    private void setUpBottomNav() {
        BottomNavBar bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setActiveItem(BottomNavBar.BOOKS);
        bottomNav.setOnItemSelectedListener(index -> {
            switch (index) {
                case BottomNavBar.HOME:
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                    break;
                case BottomNavBar.LOGOUT:
                    Intent login = new Intent(this, LoginActivity.class);
                    login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(login);
                    finish();
                    break;
                case BottomNavBar.STORES:
                    startActivity(new Intent(this, StoresActivity.class));
                    finish();
                    break;
                default:
                    break;
            }
        });
    }

    private void applyWindowInsets() {
        View content   = findViewById(R.id.booksContent);
        View bottomNav = findViewById(R.id.bottomNav);
        final int baseTop = content.getPaddingTop();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rootBooks), (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            content.setPadding(content.getPaddingLeft(), baseTop + bars.top,
                    content.getPaddingRight(), content.getPaddingBottom());
            bottomNav.setPadding(bottomNav.getPaddingLeft(), bottomNav.getPaddingTop(),
                    bottomNav.getPaddingRight(), bars.bottom);
            return insets;
        });
    }
}
