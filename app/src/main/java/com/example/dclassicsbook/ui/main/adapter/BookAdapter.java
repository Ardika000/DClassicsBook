package com.example.dclassicsbook.ui.main.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dclassicsbook.R;
import com.example.dclassicsbook.data.model.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    public interface OnBookClickListener {
        void onBookClick(Book book);
    }

    private final List<Book> books;
    private final OnBookClickListener clickListener;

    public BookAdapter(List<Book> books, OnBookClickListener clickListener) {
        this.books = books;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book_card, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        holder.bind(books.get(position), clickListener);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    static class BookViewHolder extends RecyclerView.ViewHolder {
        private final ImageView cover;
        private final TextView  title;
        private final TextView  author;
        private final TextView  genres;

        BookViewHolder(@NonNull View itemView) {
            super(itemView);
            cover  = itemView.findViewById(R.id.imgCardCover);
            title  = itemView.findViewById(R.id.tvCardTitle);
            author = itemView.findViewById(R.id.tvCardAuthor);
            genres = itemView.findViewById(R.id.tvCardGenres);
        }

        void bind(Book book, OnBookClickListener listener) {
            cover.setImageResource(book.getCoverResId());
            title.setText(book.getTitle());
            author.setText(book.getAuthor() + "   |   " + book.getYear());
            genres.setText(TextUtils.join(", ", book.getGenres()));
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onBookClick(book);
                }
            });
        }
    }
}
