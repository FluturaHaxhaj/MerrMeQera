package com.fiek.ppmapp.Home;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.palette.graphics.Palette;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.WindowManager;

import com.fiek.ppmapp.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.Objects;

public class Rrethnesh extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_rrethnesh);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        final CollapsingToolbarLayout collapsingToolbarLayout=findViewById(R.id.collapsingToolbarLayout);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.bg);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener(){
            @Override
            public void onGenerated(@Nullable Palette palette) {
                if(palette != null){
                    collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(R.attr.colorPrimary));
                }
            }
        });
    }
}