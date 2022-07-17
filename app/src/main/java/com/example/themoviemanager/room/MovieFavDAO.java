package com.example.themoviemanager.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieFavDAO
{
    //Fav listesi için
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertFav(MovieFav movieFav);

    @Delete
    void deleteFav(MovieFav movieFav);

    @Query("select * from MovieFavDB")
    List<MovieFav> getAllMovieFavs();

    //-----------------------------

    //Watchlist için
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertWatchlist(MovieWatchlist movieWatchlist);

    @Delete
    void deleteWatchlist(MovieWatchlist movieWatchlist);

    @Query("select * from MovieWatchlistDB")
    List<MovieWatchlist> getAllMovieWatchlist();
    //-----------------------------
}
