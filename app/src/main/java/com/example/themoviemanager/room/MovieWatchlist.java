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

    private String overview;

    private String poster_path;

    private String release_date;

    private double vote_average;

    public MovieWatchlist() {
    }

    public MovieWatchlist(int id, int film_id, String title, String overview, String poster_path, String release_date, double vote_average)
    {
        this.id = id;
        this.film_id = film_id;
        this.title = title;
        this.overview = overview;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.vote_average = vote_average;
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

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }
}
