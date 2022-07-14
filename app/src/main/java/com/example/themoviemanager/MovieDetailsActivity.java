package com.example.themoviemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textViewTitle, textViewDate, textViewVote, textViewOverview;
    private Button buttonFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        imageView=findViewById(R.id.imageView);
        textViewTitle=findViewById(R.id.textViewTitle);
        textViewDate=findViewById(R.id.textViewDate);
        textViewVote=findViewById(R.id.textViewVote);
        textViewOverview=findViewById(R.id.textViewOverview);
        buttonFav=findViewById(R.id.buttonFav);

        String title=getIntent().getStringExtra("title");
        String language=getIntent().getStringExtra("language");
        String overview=getIntent().getStringExtra("overview");
        String poster_path=getIntent().getStringExtra("poster_path");
        String relesase_date=getIntent().getStringExtra("relesase_date");
        double vote_average=getIntent().getDoubleExtra("vote_average",0.0);

        Picasso.get().load("https://image.tmdb.org/t/p/original"+poster_path).into(imageView);

        textViewTitle.setText(title);
        textViewDate.setText(relesase_date);
        textViewVote.setText(String.valueOf(vote_average));
        textViewOverview.setText(overview);

        buttonFav.setText("Favorilere Ekle");







    }
}