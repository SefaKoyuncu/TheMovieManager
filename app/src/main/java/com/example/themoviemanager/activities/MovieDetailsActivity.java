package com.example.themoviemanager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import com.example.themoviemanager.adapters.MovieFavAdapter;
import com.example.themoviemanager.classes.Movies;
import com.example.themoviemanager.R;
import com.example.themoviemanager.databinding.ActivityMovieDetailsBinding;
import com.example.themoviemanager.room.AppDatabase;
import com.example.themoviemanager.room.MovieFav;
import com.example.themoviemanager.room.MovieWatchlist;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity
{
    private MovieFavAdapter movieFavandListAdapter;
    private ActivityMovieDetailsBinding binding;
    private AppDatabase appDatabase;
    private int film_id;
    private MovieFav movieFav;
    private Movies movies;
    private MovieWatchlist movieWatchlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(MovieDetailsActivity.this, R.layout.activity_movie_details);

        movies=(Movies) getIntent().getSerializableExtra("instance");

        binding.textViewTitle.setText(movies.getOriginal_title());
        binding.textViewDate.setText(movies.getRelease_date());
        binding.textViewVote.setText(String.valueOf(movies.getVote_average()));
        binding.textViewOverview.setText(movies.getOverview());
        Picasso.get().load("https://image.tmdb.org/t/p/original"+movies.getPoster_path()).into(binding.imageView);
        appDatabase = AppDatabase.getInstance(MovieDetailsActivity.this);

        MovieFav fav=new MovieFav();
        fav.setTitle(movies.getOriginal_title());
        fav.setPoster_path(movies.getPoster_path());
        fav.setFilm_id(movies.getFilm_id());

        MovieWatchlist watch=new MovieWatchlist();
        watch.setTitle(movies.getOriginal_title());
        watch.setPoster_path(movies.getPoster_path());
        watch.setFilm_id(movies.getFilm_id());

        film_id=movies.getFilm_id();

        movieFav=appDatabase.movieDAO().getMovieFavById(film_id);
        movieWatchlist=appDatabase.movieDAO().getMovieListById(film_id);

        isMovieInFavorites();
        isMovieInWatchlist();

        binding.imageViewfav.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (movieFav==null)
                {
                    binding.imageViewfav.setImageResource(R.drawable.favorite_added);
                    appDatabase.movieDAO().insertFav(fav);
                }
                else
                {
                    binding.imageViewfav.setImageResource(R.drawable.favorite);
                    appDatabase.movieDAO().deleteFav(movieFav);
                }
            }
        });

        binding.imageViewlist.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (movieWatchlist==null)
                {
                    binding.imageViewlist.setImageResource(R.drawable.list_added);
                    appDatabase.movieDAO().insertWatchlist(watch);
                }
                else
                {
                    binding.imageViewlist.setImageResource(R.drawable.list);
                    appDatabase.movieDAO().deleteWatchlist(movieWatchlist);
                }
            }
        });
    }

    public void isMovieInFavorites()
    {

        if (movieFav == null){
            binding.imageViewfav.setImageResource(R.drawable.favorite);
        }
        else
        {
            binding.imageViewfav.setImageResource(R.drawable.favorite_added);
        }

    }

    public void isMovieInWatchlist()
    {

        if (movieWatchlist == null){
            binding.imageViewlist.setImageResource(R.drawable.list);
        }
        else
        {
            binding.imageViewlist.setImageResource(R.drawable.list_added);
        }

    }
}