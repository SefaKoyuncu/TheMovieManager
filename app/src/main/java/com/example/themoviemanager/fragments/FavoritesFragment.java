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
//import com.example.themoviemanager.databinding.FragmentFavoritesBinding;
import com.example.themoviemanager.room.AppDatabase;
import com.example.themoviemanager.room.MovieFav;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    private ArrayList<MovieFav> moviesArrayList=new ArrayList<>();
    private MovieFavAdapter movieFavandListAdapter;
    private AppDatabase appDatabase;
    // private FragmentFavoritesBinding binding;
    private LottieAnimationView animationViewFav;
    private TextView textViewFav;
    private RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        //binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites,container, false);
        //View view = binding.getRoot();
        View view= inflater.inflate(R.layout.fragment_favorites, container, false);
        animationViewFav=view.findViewById(R.id.animationViewFav);
        textViewFav=view.findViewById(R.id.textViewFav);
        rv=view.findViewById(R.id.rv);

        appDatabase = AppDatabase.getInstance(getContext());
        List<MovieFav> movies = appDatabase.movieDAO().getAllMovieFavs();
        moviesArrayList.addAll(movies);

        if (!moviesArrayList.isEmpty())
        {
            animationViewFav.setVisibility(View.INVISIBLE);
            textViewFav.setVisibility(View.INVISIBLE);
            rv.setVisibility(View.VISIBLE);

            rv.setHasFixedSize(true);
            movieFavandListAdapter =new MovieFavAdapter(getContext(),moviesArrayList);
            rv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
            rv.setAdapter(movieFavandListAdapter);
        }
        else
        {
           animationViewFav.setVisibility(View.VISIBLE);
           textViewFav.setVisibility(View.VISIBLE);
           rv.setVisibility(View.INVISIBLE);
        }

        return view;
    }
}