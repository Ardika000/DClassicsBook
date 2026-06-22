package com.example.dclassicsbook.data.model;

import java.io.Serializable;

/**
 * Data model representing a bookstore shown in the "Our Stores" carousel
 * on the home page.
 *
 * NOTE: {@link #imageResId} currently points to a placeholder. To use real
 * store photos, drop the images into res/drawable and update
 * {@code StoreRepository} accordingly.
 */
public class Store implements Serializable {

    private final int    id;
    private final String name;
    private final int    imageResId; // R.drawable.xxx store thumbnail

    public Store(int id, String name, int imageResId) {
        this.id         = id;
        this.name       = name;
        this.imageResId = imageResId;
    }

    public int    getId()         { return id; }
    public String getName()       { return name; }
    public int    getImageResId() { return imageResId; }
}
