package com.example.themoviemanager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.themoviemanager.databinding.ActivityNavigationBinding;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;


public class NavigationActivity extends AppCompatActivity {

    private MeowBottomNavigation meowBottomNavigation;
    private ActivityNavigationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= DataBindingUtil.setContentView(this,R.layout.activity_navigation);
      //  setContentView(R.layout.activity_navigation);
       // meowBottomNavigation=findViewById(R.id.meowBottomNavigation);


        binding.meowBottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.search));
        binding.meowBottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.list));
        binding.meowBottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.favorite));

        binding.meowBottomNavigation.show(1,true);

        replace(new SearchFragment());

        binding.meowBottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model,
                        Unit>()
        {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {

                switch (model.getId())
                {

                    case 1:
                        replace(new SearchFragment());
                        break;
                    case 2:
                        replace(new WatchlistFragment());
                        break;
                    case 3:
                        replace(new FavoritesFragment());
                        break;
                }
                return null;
            }
        });

    }

    private void replace(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(binding.frameLayout.getId(),fragment);
        transaction.commit();
    }
}