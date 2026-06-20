package com.example.dclassicsbook.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dclassicsbook.R;
import com.example.dclassicsbook.data.model.Book;
import com.example.dclassicsbook.data.repository.BookRepository;
import com.example.dclassicsbook.ui.detail.BookDetailActivity;

import java.util.List;

/**
 * MainActivity — Halaman utama daftar buku klasik.
 * Menampilkan 22 buku klasik dari BookRepository sesuai desain Figma.
 */
public class MainActivity extends AppCompatActivity {

    private LinearLayout booksContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        booksContainer = findViewById(R.id.booksContainer);

        List<Book> books = BookRepository.getBooks();
        populateBooks(books);
    }

    private void populateBooks(List<Book> books) {
        LinearLayout booksContainer = findViewById(R.id.booksContainer);
        booksContainer.removeAllViews();

        LayoutInflater inflater = LayoutInflater.from(this);
        for (Book book : books) {
            View bookCardView = inflater.inflate(R.layout.item_book_card, booksContainer, false);

            ImageView imgCardCover = bookCardView.findViewById(R.id.imgCardCover);
            TextView tvCardTitle   = bookCardView.findViewById(R.id.tvCardTitle);
            TextView tvCardAuthor  = bookCardView.findViewById(R.id.tvCardAuthor);

            tvCardTitle.setText(book.getTitle());
            tvCardAuthor.setText(book.getAuthor());
            imgCardCover.setImageResource(book.getCoverResId());

            bookCardView.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, BookDetailActivity.class);
                intent.putExtra("BOOK_DATA", book);
                startActivity(intent);
            });

            booksContainer.addView(bookCardView);
        }
    }
}