package com.fiek.ppmapp.MenuItems.Favorites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.WindowManager;

import com.fiek.ppmapp.Lista.ViewPagerAdapter;
import com.fiek.ppmapp.R;
import com.google.android.material.tabs.TabLayout;


public class FavoritesActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_favorites);

        tabLayout = (TabLayout) findViewById(R.id.tablayout_id_fav);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id_fav);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        //Add Fargment
        adapter.AddFragment(new FavoritesFragment(), "Favorites");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }
}