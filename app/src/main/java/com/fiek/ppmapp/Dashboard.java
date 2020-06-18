package com.fiek.ppmapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Dashboard extends AppCompatActivity {

    String strExtras;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        if (getIntent().getExtras() != null)
            strExtras = getIntent().getExtras().getString("emri");

        Toast.makeText(Dashboard.this, strExtras + " jeni lloguar me sukses!", Toast.LENGTH_LONG).show();

    }
}