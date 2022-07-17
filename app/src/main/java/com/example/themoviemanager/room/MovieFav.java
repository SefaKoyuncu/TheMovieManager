package com.example.themoviemanager.room;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "MovieFavDB")
public class MovieFav
{
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String poster_path;

    public MovieFav()
    {

    }

    public MovieFav(int id,String title, String poster_path)
    {
        this.id=id;
        this.title = title;
        this.poster_path = poster_path;
    }
    @Ignore
    public MovieFav(String title, String poster_path) {
        this.title = title;
        this.poster_path = poster_path;
    }

    @Ignore
    public MovieFav(String poster_path) {
        this.poster_path = poster_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
