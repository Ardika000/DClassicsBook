package com.example.dclassicsbook.data.model;

import java.io.Serializable;

/**
 * Data model representing a bookstore shown in the "Our Stores" home carousel
 * and the Stores page list.
 */
public class Store implements Serializable {

    private final int    id;
    private final String name;     // store branch name, e.g. "Forest Serenity"
    private final String address;  // branch address shown on the Stores page
    private final int    imageResId; // R.drawable.xxx store photo

    public Store(int id, String name, String address, int imageResId) {
        this.id         = id;
        this.name       = name;
        this.address    = address;
        this.imageResId = imageResId;
    }

    public int    getId()         { return id; }
    public String getName()       { return name; }
    public String getAddress()    { return address; }
    public int    getImageResId() { return imageResId; }
}
