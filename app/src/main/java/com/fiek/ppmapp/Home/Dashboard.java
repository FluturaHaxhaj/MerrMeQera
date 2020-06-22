package com.fiek.ppmapp.Home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fiek.ppmapp.Map.MapActivity;
import com.fiek.ppmapp.MenuItems.Feedback;
import com.fiek.ppmapp.R;
import com.fiek.ppmapp.LoginSignup.SessionManager;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    RelativeLayout rrethNesh, lokacioni;
    ImageView menuIcon, profilePic;
    TextView menuProfileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);



        lokacioni = findViewById(R.id.lokacioni);
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
        lokacioni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lokacioniIntent = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(lokacioniIntent);
            }
        });

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askCameraPermission();
            }


        });


        navigationDrawer();
        showUserName();

    }

    private void askCameraPermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CAMERA}, CAMERA_PERM_CODE);
        }else {
            openCamera();
        }

    }

    private void openCamera() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, CAMERA_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_REQUEST_CODE){
            Bitmap image = (Bitmap) data.getExtras().get("data");
            profilePic.setImageBitmap(image);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == CAMERA_PERM_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            }else {
                Toast.makeText(this,"Per te perdorur kameren ju duhet te jepni akses ne kamere.",Toast.LENGTH_LONG).show();
            }
        }
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
                menuItem.setCheckable(false);
                break;
            case R.id.nav_home:
                startActivity(new Intent(getApplicationContext(),Dashboard.class));
                break;


        }
        return true;
    }

}