package com.example.themoviemanager.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.themoviemanager.R;
import com.example.themoviemanager.activities.MovieDetailsActivity;
import com.example.themoviemanager.classes.Movies;
import com.example.themoviemanager.room.MovieFav;
import com.example.themoviemanager.room.MovieWatchlist;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.CardviewTasarimNesneleriniTutucu>
{
    private Context mContext;
    private List<MovieWatchlist> moviesList;

    public MovieListAdapter(Context mContext, List<MovieWatchlist> moviesList) {
        this.mContext = mContext;
        this.moviesList = moviesList;
    }

    @NonNull
    @Override
    public CardviewTasarimNesneleriniTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fav_list_design,parent,false);

        return new CardviewTasarimNesneleriniTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardviewTasarimNesneleriniTutucu holder, int position)
    {
        final MovieWatchlist movies=moviesList.get(position);


        Picasso.get().load("https://image.tmdb.org/t/p/w500"+movies.getPoster_path()).into(holder.imageViewImage);

        holder.textViewDetailsTitle.setText(movies.getTitle());

        holder.cardView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Movies moviesForDetails=new Movies();


               /* Log.e("title",movies.getOriginal_title());
                Log.e("language",movies.getOriginal_language());
                Log.e("overview",movies.getOverview());
                Log.e("poster path",movies.getPoster_path());
                Log.e("date",movies.getRelease_date());
                Log.e("vote",String.valueOf(movies.getVote_average()));*/

                moviesForDetails.setFilm_id(movies.getFilm_id());
                moviesForDetails.setOriginal_title(movies.getTitle());
                moviesForDetails.setOverview(movies.getOverview());
                moviesForDetails.setPoster_path(movies.getPoster_path());
                moviesForDetails.setRelease_date(movies.getRelease_date());
                moviesForDetails.setVote_average(movies.getVote_average());
                String flag="list";

                Intent intent=new Intent(mContext, MovieDetailsActivity.class);
                intent.putExtra("instance",moviesForDetails);
                intent.putExtra("flag",flag);

                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount()
    {
        return moviesList.size();
    }

    public class CardviewTasarimNesneleriniTutucu extends RecyclerView.ViewHolder
    {

        public ImageView imageViewImage;
        public TextView textViewDetailsTitle;
        public CardView cardView;


        public CardviewTasarimNesneleriniTutucu(@NonNull View itemView)
        {
            super(itemView);
            imageViewImage=itemView.findViewById(R.id.imageViewImage);
            textViewDetailsTitle=itemView.findViewById(R.id.textViewDetailsTitle);
            cardView=itemView.findViewById(R.id.cardView);
        }
    }

}
