package com.example.themoviemanager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.example.themoviemanager.adapters.MovieFavandListAdapter;
import com.example.themoviemanager.classes.Movies;
import com.example.themoviemanager.R;
import com.example.themoviemanager.databinding.ActivityMovieDetailsBinding;
import com.example.themoviemanager.room.AppDatabase;
import com.example.themoviemanager.room.MovieFav;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity
{
    private MovieFavandListAdapter movieFavandListAdapter;
    private ActivityMovieDetailsBinding binding;
    private AppDatabase appDatabase;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(MovieDetailsActivity.this, R.layout.activity_movie_details);

        Movies movies=(Movies) getIntent().getSerializableExtra("instance");

        binding.textViewTitle.setText(movies.getOriginal_title());
        binding.textViewDate.setText(movies.getRelease_date());
        binding.textViewVote.setText(String.valueOf(movies.getVote_average()));
        binding.textViewOverview.setText(movies.getOverview());
        Picasso.get().load("https://image.tmdb.org/t/p/original"+movies.getPoster_path()).into(binding.imageView);
        binding.buttonFav.setText("Favorilere Ekle");

        appDatabase = AppDatabase.getInstance(MovieDetailsActivity.this);

        MovieFav fav=new MovieFav();
        fav.setTitle(movies.getOriginal_title());
        fav.setPoster_path(movies.getPoster_path());

        binding.imageViewfav.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {
                appDatabase.movieFavDAO().insertFav(fav);
            }
        });




    }
}