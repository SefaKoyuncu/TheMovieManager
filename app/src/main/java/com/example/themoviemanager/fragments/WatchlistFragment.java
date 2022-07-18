package com.example.themoviemanager.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.themoviemanager.R;
import com.example.themoviemanager.adapters.MovieFavAdapter;
import com.example.themoviemanager.adapters.MovieListAdapter;
//import com.example.themoviemanager.databinding.FragmentWatchlistBinding;
import com.example.themoviemanager.room.AppDatabase;
import com.example.themoviemanager.room.MovieWatchlist;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class WatchlistFragment extends Fragment
{
    private ArrayList<MovieWatchlist> moviesArrayList=new ArrayList<>();
    private MovieListAdapter movieListAdapter;
    private AppDatabase appDatabase;
    //private FragmentWatchlistBinding binding;
    private LottieAnimationView animationViewWatchlist;
    private TextView textViewWatchlist;
    private RecyclerView rv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        //binding = DataBindingUtil.inflate(inflater, R.layout.fragment_watchlist,container, false);
        //View view = binding.getRoot();

        View view= inflater.inflate(R.layout.fragment_watchlist, container, false);

        animationViewWatchlist=view.findViewById(R.id.animationViewWatchlist);
        textViewWatchlist=view.findViewById(R.id.textViewWatchlist);
        rv=view.findViewById(R.id.rv);


        appDatabase = AppDatabase.getInstance(getContext());
        List<MovieWatchlist> movies = appDatabase.movieDAO().getAllMovieWatchlist();
        moviesArrayList.addAll(movies);

        if (!moviesArrayList.isEmpty())
        {
            animationViewWatchlist.setVisibility(View.INVISIBLE);
            textViewWatchlist.setVisibility(View.INVISIBLE);
            rv.setVisibility(View.VISIBLE);

            rv.setHasFixedSize(true);
            movieListAdapter =new MovieListAdapter(getContext(),moviesArrayList);
            rv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
            rv.setAdapter(movieListAdapter);
        }
        else
        {
            animationViewWatchlist.setVisibility(View.VISIBLE);
            textViewWatchlist.setVisibility(View.VISIBLE);
            rv.setVisibility(View.INVISIBLE);
        }


        return view;
    }
}