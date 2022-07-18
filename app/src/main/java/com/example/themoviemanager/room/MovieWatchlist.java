package com.example.themoviemanager.room;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "MovieWatchlistDB")
public class MovieWatchlist extends Object
{
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int film_id;

    private String title;

    private String poster_path;

    public MovieWatchlist() {
    }

    public MovieWatchlist(int id, int film_id, String title, String poster_path)
    {
        this.film_id = film_id;
        this.id = id;
        this.title = title;
        this.poster_path = poster_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
}
