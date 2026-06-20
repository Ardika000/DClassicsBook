package com.example.dclassicsbook.data.repository;

import com.example.dclassicsbook.R;
import com.example.dclassicsbook.data.model.Store;

import java.util.ArrayList;
import java.util.List;

/**
 * In-memory source of the bookstores shown in the home "Our Stores" carousel.
 *
 * All entries currently share a placeholder thumbnail ({@code bg_store_image}).
 * Replace {@code R.drawable.bg_store_image} with real photos once available.
 */
public class StoreRepository {

    public static List<Store> getStores() {
        List<Store> list = new ArrayList<>();
        list.add(new Store(1, "Forest Serenity", R.drawable.bg_store_image));
        list.add(new Store(2, "Ocean Voyage",    R.drawable.bg_store_image));
        list.add(new Store(3, "Old Athenaeum",   R.drawable.bg_store_image));
        list.add(new Store(4, "Vintage Corner",  R.drawable.bg_store_image));
        list.add(new Store(5, "Garden Reads",    R.drawable.bg_store_image));
        return list;
    }
}
