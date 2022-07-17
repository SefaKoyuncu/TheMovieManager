package com.example.themoviemanager.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

//import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.themoviemanager.adapters.MovieAdapter;
import com.example.themoviemanager.classes.Movies;
import com.example.themoviemanager.R;
//import com.example.themoviemanager.databinding.FragmentSearchBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchFragment extends Fragment
{

    private final String url = "https://api.themoviedb.org/3/search/movie?api_key=c76938ab688628aa0eb7ed3f2111a145&query=";
    private MovieAdapter movieAdapter;
    private String temporaryUrl = "";
    ArrayList<Movies> moviesArrayList=new ArrayList<>();
   // private FragmentSearchBinding binding;
    private EditText editTextSearch;
    private RecyclerView rv;
    private ImageView imageViewClearEdittext;
    private TextView textViewCancel,textViewSearch;
    private LottieAnimationView animationViewSearch;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        //binding= DataBindingUtil.setContentView(getActivity(),R.layout.fragment_search);
        View view= inflater.inflate(R.layout.fragment_search, container, false);

        rv=view.findViewById(R.id.rv);
        editTextSearch=view.findViewById(R.id.editTextSearch);
        imageViewClearEdittext=view.findViewById(R.id.imageViewClearEdittext);
        textViewCancel=view.findViewById(R.id.textViewCancel);
        animationViewSearch=view.findViewById(R.id.animationViewSearch);
        textViewSearch=view.findViewById(R.id.textViewSearch);


        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                moviesArrayList.clear();
                getMoviesFromApi(editable.toString());
            }
        });

        imageViewClearEdittext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                editTextSearch.getText().clear();
            }
        });

        textViewCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                editTextSearch.getText().clear();
                hideKeyboardFrom(getActivity(),view);
            }
        });

        return view;
    }

    public void getMoviesFromApi(String text)
    {
        String URL = url + text;

        if (editTextSearch.getText().toString().equals(""))
        {
            rv.setVisibility(View.INVISIBLE);
            animationViewSearch.setVisibility(View.VISIBLE);
            textViewSearch.setVisibility(View.VISIBLE);
        }

        else
        {
            rv.setVisibility(View.VISIBLE);
            animationViewSearch.setVisibility(View.INVISIBLE);
            textViewSearch.setVisibility(View.INVISIBLE);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Log.e("response", response);

                    try {

                        JSONObject jsonObject=new JSONObject(response);
                        JSONArray jsonArrayResults=jsonObject.getJSONArray("results");
                        for (int i = 0; i<jsonArrayResults.length(); i++)
                        {
                            JSONObject movieObject = jsonArrayResults.getJSONObject(i);
                            String title = movieObject.getString("original_title");
                            String original_language = movieObject.getString("original_language");
                            String overview = movieObject.getString("overview");//overview:metin olarak açıklama.
                            String poster_path = movieObject.getString("poster_path");
                            String release_date = movieObject.getString("release_date");
                            double vote_average = movieObject.getDouble("vote_average");

                            Movies movies=new Movies(title,original_language,overview,poster_path,release_date,vote_average);

                            Log.e("title",title);
                            Log.e("date",release_date);

                            moviesArrayList.add(movies);
                        }
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

                    rv.setHasFixedSize(true);
                    rv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
                    movieAdapter=new MovieAdapter(getActivity(),moviesArrayList);
                    rv.setAdapter(movieAdapter);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    if (error instanceof NoConnectionError)
                    {
                        Toast.makeText(getActivity(),"Please Check Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Volley.newRequestQueue(getActivity()).add(stringRequest);
        }
    }

    public static void hideKeyboardFrom(Context context, View view)
    {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}