package com.example.themoviemanager.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MovieFav.class, MovieWatchlist.class}, exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase
{
    public static final String DATABASE_NAME = "MovieDB";
    private static AppDatabase instance;
    public abstract MovieDAO movieDAO();

    public static synchronized AppDatabase getInstance(Context context){
        if (instance == null)
        {
            instance =  Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }






}
