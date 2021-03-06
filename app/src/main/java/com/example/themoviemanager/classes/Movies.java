package com.example.themoviemanager.classes;

import java.io.Serializable;

public class Movies implements Serializable
{
    private int film_id;
    private String original_title;
    private String original_language;
    private String overview;
    private String poster_path;
    private String release_date;
    private double vote_average;

    public Movies()
    {
    }

    public Movies(int film_id,String original_title, String original_language, String overview, String poster_path, String release_date, double vote_average)
    {
        this.film_id = film_id;
        this.original_title = original_title;
        this.original_language = original_language;
        this.overview = overview;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.vote_average = vote_average;
    }

    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
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
