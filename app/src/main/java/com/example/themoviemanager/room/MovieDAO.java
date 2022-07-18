package com.example.themoviemanager.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDAO
{
    //Fav listesi için
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertFav(MovieFav movieFav);

    @Delete
    void deleteFav(MovieFav movieFav);

    @Query("select * from MovieFavDB")
    List<MovieFav> getAllMovieFavs();

    @Query("select * from MovieFavDB where film_id==:id")
    MovieFav getMovieFavById(int id);

    //-----------------------------

    //Watchlist için
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertWatchlist(MovieWatchlist movieWatchlist);

    @Delete
    void deleteWatchlist(MovieWatchlist movieWatchlist);

    @Query("select * from MovieWatchlistDB")
    List<MovieWatchlist> getAllMovieWatchlist();

    @Query("select * from MovieWatchlistDB where film_id==:id")
    MovieWatchlist getMovieListById(int id);
    //-----------------------------
}
