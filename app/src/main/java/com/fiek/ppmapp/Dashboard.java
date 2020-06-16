package com.fiek.ppmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {

    String strExtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        if (getIntent().getExtras() != null)
            strExtras = getIntent().getExtras().getString("emri");

        Toast.makeText(Dashboard.this, strExtras + " jeni lloguar me sukses!", Toast.LENGTH_LONG).show();

    }
}