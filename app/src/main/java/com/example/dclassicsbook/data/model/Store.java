package com.example.dclassicsbook.data.model;

import java.io.Serializable;

public class Store implements Serializable {

    private final int    id;
    private final String name;
    private final String address;
    private final int    imageResId;

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
