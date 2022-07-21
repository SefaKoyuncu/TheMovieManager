package com.example.themoviemanager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.example.themoviemanager.adapters.MovieFavAdapter;
import com.example.themoviemanager.classes.Movies;
import com.example.themoviemanager.R;
import com.example.themoviemanager.databinding.ActivityMovieDetailsBinding;
import com.example.themoviemanager.databinding.ActivityNavigationBinding;
import com.example.themoviemanager.fragments.FavoritesFragment;
import com.example.themoviemanager.fragments.SearchFragment;
import com.example.themoviemanager.fragments.WatchlistFragment;
import com.example.themoviemanager.room.AppDatabase;
import com.example.themoviemanager.room.MovieFav;
import com.example.themoviemanager.room.MovieWatchlist;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity
{
    private ActivityMovieDetailsBinding binding;
    private AppDatabase appDatabase;
    private int film_id;
    private MovieFav movieFav,fav;
    private Movies movies;
    private MovieWatchlist movieWatchlist,watch;
    private String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(MovieDetailsActivity.this, R.layout.activity_movie_details);

        movies=(Movies) getIntent().getSerializableExtra("instance");
        flag=getIntent().getStringExtra("flag");

        binding.textViewTitle.setText(movies.getOriginal_title());
        binding.textViewDate.setText(movies.getRelease_date());
        binding.textViewVote.setText(String.valueOf(movies.getVote_average()));
        binding.textViewOverview.setText(movies.getOverview());
        Picasso.get().load("https://image.tmdb.org/t/p/original"+movies.getPoster_path()).into(binding.imageView);
        appDatabase = AppDatabase.getInstance(MovieDetailsActivity.this);

        switch (flag)
        {
            case "search":
                Log.e("flag", flag);
                binding.textViewBackText.setText("Search");
                break;

            case "fav":
                Log.e("flag", flag);
                binding.textViewBackText.setText("Favorites");
                break;

            case "list":
                Log.e("flag", flag);
                binding.textViewBackText.setText("Watchlist");
                break;
        }

        binding.cLBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onBackPressed();
            }
        });

        film_id=movies.getFilm_id();

        movieFav=appDatabase.movieDAO().getMovieFavById(film_id);
        movieWatchlist=appDatabase.movieDAO().getMovieListById(film_id);

        fav=isMovieInFavorites();
        watch=isMovieInWatchlist();

        binding.imageViewfav.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                movieFav=appDatabase.movieDAO().getMovieFavById(film_id);

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
                movieWatchlist=appDatabase.movieDAO().getMovieListById(film_id);

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

    public MovieFav isMovieInFavorites()
    {

        if (movieFav == null){
            binding.imageViewfav.setImageResource(R.drawable.favorite);
            fav=new MovieFav();
            fav.setFilm_id(movies.getFilm_id());
            fav.setTitle(movies.getOriginal_title());
            fav.setOverview(movies.getOverview());
            fav.setPoster_path(movies.getPoster_path());
            fav.setRelease_date(movies.getRelease_date());
            fav.setVote_average(movies.getVote_average());

            return fav;

        }
        else
        {
            binding.imageViewfav.setImageResource(R.drawable.favorite_added);
            return movieFav;
        }

    }

    public MovieWatchlist isMovieInWatchlist()
    {

        if (movieWatchlist == null){
            binding.imageViewlist.setImageResource(R.drawable.list);
            watch=new MovieWatchlist();
            watch.setFilm_id(movies.getFilm_id());
            watch.setTitle(movies.getOriginal_title());
            watch.setOverview(movies.getOverview());
            watch.setPoster_path(movies.getPoster_path());
            watch.setRelease_date(movies.getRelease_date());
            watch.setVote_average(movies.getVote_average());

            return watch;
        }
        else
        {
            binding.imageViewlist.setImageResource(R.drawable.list_added);
            return movieWatchlist;
        }

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
    }
}