package com.example.dclassicsbook.data.model;

import java.io.Serializable;
import java.util.List;

/**
 * Data model class representing a Book entity.
 * Properties sesuai field yang ada di Figma Book Detail Page:
 *   id, title, author, year, genres, description, coverResId
 */
public class Book implements Serializable {

    private int    id;
    private String title;
    private String author;
    private String year;          // tahun terbit, contoh: "1813"
    private List<String> genres;  // list of genres
    private String description;   // sinopsis buku
    private int    coverResId;    // R.drawable.xxx resource ID cover buku

    // ─── Constructor ──────────────────────────────────────────────────────────

    public Book(int id, String title, String author, String year,
                List<String> genres, String description, int coverResId) {
        this.id          = id;
        this.title       = title;
        this.author      = author;
        this.year        = year;
        this.genres      = genres;
        this.description = description;
        this.coverResId  = coverResId;
    }

    // ─── Getters ──────────────────────────────────────────────────────────────

    public int    getId()           { return id; }
    public String getTitle()        { return title; }
    public String getAuthor()       { return author; }
    public String getYear()         { return year; }
    public List<String> getGenres() { return genres; }
    public String getDescription()  { return description; }
    public int    getCoverResId()   { return coverResId; }

    // ─── Setters ──────────────────────────────────────────────────────────────

    public void setId(int id)                     { this.id = id; }
    public void setTitle(String title)            { this.title = title; }
    public void setAuthor(String author)          { this.author = author; }
    public void setYear(String year)              { this.year = year; }
    public void setGenres(List<String> genres)    { this.genres = genres; }
    public void setDescription(String description){ this.description = description; }
    public void setCoverResId(int resId)          { this.coverResId = resId; }
}
