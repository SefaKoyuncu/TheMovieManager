package com.example.themoviemanager.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.themoviemanager.R;
import com.example.themoviemanager.adapters.MovieFavandListAdapter;
import com.example.themoviemanager.databinding.FragmentFavoritesBinding;
import com.example.themoviemanager.room.AppDatabase;
import com.example.themoviemanager.room.MovieFav;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    private ArrayList<MovieFav> moviesArrayList=new ArrayList<>();
    private MovieFavandListAdapter movieFavandListAdapter;
    private AppDatabase appDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        FragmentFavoritesBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_favorites, container, false);

        View view = binding.getRoot();

        appDatabase = AppDatabase.getInstance(getContext());
        List<MovieFav> movies = appDatabase.movieFavDAO().getAllMovieFavs();
        moviesArrayList.addAll(movies);

        if (!moviesArrayList.isEmpty())
        {
            binding.animationViewFav.setVisibility(View.INVISIBLE);
            binding.textViewFav.setVisibility(View.INVISIBLE);
            binding.rv.setVisibility(View.VISIBLE);

            binding.rv.setHasFixedSize(true);
            movieFavandListAdapter =new MovieFavandListAdapter(getContext(),moviesArrayList);
            binding.rv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
            binding.rv.setAdapter(movieFavandListAdapter);
        }
        else
        {
            binding.animationViewFav.setVisibility(View.VISIBLE);
            binding.textViewFav.setVisibility(View.VISIBLE);
            binding.rv.setVisibility(View.INVISIBLE);

        }



        return view;
    }
}