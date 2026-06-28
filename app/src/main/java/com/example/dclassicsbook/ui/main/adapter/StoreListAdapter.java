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
 * Adapter for the vertical store list on the Stores page (spec module 6).
 * Each row shows the store photo, brand, branch name and address.
 */
public class StoreListAdapter extends RecyclerView.Adapter<StoreListAdapter.VH> {

    /** Click callback so the hosting Activity decides what happens on tap. */
    public interface OnStoreClickListener {
        void onStoreClick(Store store);
    }

    private final List<Store> stores;
    private final OnStoreClickListener clickListener;

    public StoreListAdapter(List<Store> stores, OnStoreClickListener clickListener) {
        this.stores = stores;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_store_list, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bind(stores.get(position), clickListener);
    }

    @Override
    public int getItemCount() {
        return stores.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        private final ImageView image;
        private final TextView  name;
        private final TextView  address;

        VH(@NonNull View itemView) {
            super(itemView);
            image   = itemView.findViewById(R.id.imgStore);
            name    = itemView.findViewById(R.id.tvStoreName);
            address = itemView.findViewById(R.id.tvStoreAddress);
        }

        void bind(Store store, OnStoreClickListener listener) {
            image.setImageResource(store.getImageResId());
            name.setText(store.getName());
            address.setText(store.getAddress());
            itemView.setOnClickListener(v -> {
                if (listener != null) listener.onStoreClick(store);
            });
        }
    }
}
