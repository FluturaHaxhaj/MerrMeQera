package com.fiek.ppmapp.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fiek.ppmapp.MenuItems.Feedback;
import com.fiek.ppmapp.R;
import com.fiek.ppmapp.LoginSignup.SessionManager;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private static final int RESULT_LOAD_IMAGE = 1;
    String strExtras;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    RelativeLayout rrethNesh;
    ImageView menuIcon, profilePic;
    TextView menuProfileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);




        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        rrethNesh = findViewById(R.id.rreth_nesh);
        menuIcon = findViewById(R.id.menu_icon);
        View header = navigationView.getHeaderView(0);
        profilePic = header.findViewById(R.id.profile_pic);
        menuProfileName = header.findViewById(R.id.menu_profile_name);


        rrethNesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rrethNeshIntent = new Intent(getApplicationContext(), Rrethnesh.class);
                startActivity(rrethNeshIntent);
            }
        });

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);


        profilePic.setOnClickListener(this);
        navigationDrawer();
        showUserName();

    }
    private void showUserName() {
        SessionManager sessionManager = new SessionManager(Dashboard.this,SessionManager.SESSION_USERSESSION);
        HashMap<String, String> usersDetails = sessionManager.getUsersDetailFromSession();
        String fullName = usersDetails.get(SessionManager.KEY_NAME);
        Toast.makeText(Dashboard.this, fullName + " jeni lloguar me sukses!", Toast.LENGTH_LONG).show();
        menuProfileName.setText(fullName);
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

        switch (menuItem.getItemId()){

            case R.id.nav_feedback:
                startActivity(new Intent(getApplicationContext(), Feedback.class));
                break;

        }
        return true;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.profile_pic:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
        }

    }
}