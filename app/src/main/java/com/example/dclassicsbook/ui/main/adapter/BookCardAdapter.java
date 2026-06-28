package com.example.dclassicsbook.ui.main.adapter;

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

public class BookCardAdapter extends RecyclerView.Adapter<BookCardAdapter.VH> {

    private final List<Book> books;
    private final BookAdapter.OnBookClickListener clickListener;
    private final int fixedWidthDp;

    public BookCardAdapter(List<Book> books, BookAdapter.OnBookClickListener clickListener,
                           int fixedWidthDp) {
        this.books = books;
        this.clickListener = clickListener;
        this.fixedWidthDp = fixedWidthDp;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book_vert, parent, false);
        if (fixedWidthDp > 0) {
            ViewGroup.LayoutParams lp = view.getLayoutParams();
            lp.width = Math.round(fixedWidthDp * parent.getResources().getDisplayMetrics().density);
            view.setLayoutParams(lp);
        }
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bind(books.get(position), clickListener);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        private final ImageView cover;
        private final TextView  title;
        private final TextView  author;

        VH(@NonNull View itemView) {
            super(itemView);
            cover  = itemView.findViewById(R.id.imgCover);
            title  = itemView.findViewById(R.id.tvTitle);
            author = itemView.findViewById(R.id.tvAuthor);
        }

        void bind(Book book, BookAdapter.OnBookClickListener listener) {
            cover.setImageResource(book.getCoverResId());
            title.setText(book.getTitle());
            author.setText(book.getAuthor());
            itemView.setOnClickListener(v -> {
                if (listener != null) listener.onBookClick(book);
            });
        }
    }
}
