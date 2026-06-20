package com.example.dclassicsbook.ui.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dclassicsbook.R;
import com.example.dclassicsbook.data.model.Store;

import java.util.List;

/**
 * Adapter for the horizontal "Our Stores" carousel on the home page.
 */
public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder> {

    /** Click callback so the hosting Activity decides where to navigate. */
    public interface OnStoreClickListener {
        void onStoreClick(Store store);
    }

    private final List<Store> stores;
    private final OnStoreClickListener clickListener;

    public StoreAdapter(List<Store> stores, OnStoreClickListener clickListener) {
        this.stores = stores;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_store_card, parent, false);
        return new StoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {
        holder.bind(stores.get(position), clickListener);
    }

    @Override
    public int getItemCount() {
        return stores.size();
    }

    static class StoreViewHolder extends RecyclerView.ViewHolder {
        private final ImageView image;
        private final TextView  name;

        StoreViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imgStore);
            name  = itemView.findViewById(R.id.tvStoreName);
        }

        void bind(Store store, OnStoreClickListener listener) {
            image.setImageResource(store.getImageResId());
            name.setText(store.getName());
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onStoreClick(store);
                }
            });
        }
    }
}
