package com.fiek.ppmapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    String strExtras;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    RelativeLayout rrethNesh;
    ImageView menuIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        rrethNesh = findViewById(R.id.rreth_nesh);
        menuIcon = findViewById(R.id.menu_icon);

        if (getIntent().getExtras() != null)
            strExtras = getIntent().getExtras().getString("emri");

        Toast.makeText(Dashboard.this, strExtras + " jeni lloguar me sukses!", Toast.LENGTH_LONG).show();


        rrethNesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rrethNeshIntent = new Intent(getApplicationContext(), Rrethnesh.class);
                startActivity(rrethNeshIntent);
            }
        });


        navigationDrawer();


    }



    private void navigationDrawer() {

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

    }


    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return true;
    }
}