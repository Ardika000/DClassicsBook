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
        list.add(new Store(1, "Forest Serenity", R.drawable.store_forest_serenity));
        list.add(new Store(2, "Ocean Voyage",    R.drawable.store_ocean_voyage));
        list.add(new Store(3, "Cosmic Nexus",   R.drawable.store_cosmic_nexus));
        list.add(new Store(4, "Sunset Bloom",  R.drawable.store_sunset_bloom));
        list.add(new Store(5, "Noble Heights",    R.drawable.store_noble_heights));
        list.add(new Store(6, "Urban Pulse",    R.drawable.store_urban_pulse));
        return list;
    }
}
