package com.fiek.ppmapp.Home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fiek.ppmapp.LoginSignup.Login;
import com.fiek.ppmapp.Map.MapActivity;
import com.fiek.ppmapp.MenuItems.Feedback;
import com.fiek.ppmapp.R;
import com.fiek.ppmapp.LoginSignup.SessionManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.snackbar.SnackbarContentLayout;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    RelativeLayout rrethNesh, lokacioni;
    ImageView menuIcon, profilePic;
    TextView menuProfileName;
    String currentPhotoPath;
    StorageReference storageReference;


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


        storageReference = FirebaseStorage.getInstance().getReference();


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

        String fullName = showUserName();
        Toast.makeText(Dashboard.this, fullName + " jeni lloguar me sukses!", Toast.LENGTH_LONG).show();
        menuProfileName.setText(fullName);
        UploadPicTask uploadPicTask = new UploadPicTask();
        uploadPicTask.execute(fullName);
        naviagtionDrawer();


    }

    private class UploadPicTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            StorageReference profileRef = storageReference.child("users/" + strings[0] + "/profile.jpg");
            profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Picasso.get().load(uri).into(profilePic);
                }
            });
            return null;
        }
    }

    private void askCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERM_CODE);
        } else {
            dispatchTakePictureIntent();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CAMERA_PERM_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent();
            } else {
                Toast.makeText(this, "Per te perdorur kameren ju duhet te jepni akses ne kamere.", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                File f = new File(currentPhotoPath);
                //profilePic.setImageURI(Uri.fromFile(f));

                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(f);
                mediaScanIntent.setData(contentUri);
                this.sendBroadcast(mediaScanIntent);

                uploadImageToFirebase(contentUri);
            }
        }
    }

    private void uploadImageToFirebase(Uri imageUri) {
        String fullName = showUserName();
        StorageReference fileRef = storageReference.child("users/" + fullName + "/profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(profilePic);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Snackbar.make(drawerLayout,"Ngarkimi i fotos deshtoi.",Snackbar.LENGTH_LONG)
                        .setAction("Provo Perseri", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                askCameraPermission();
                            }
                        });
            }
        });

    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.fiek.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
            }
        }
    }


    private String showUserName() {
        SessionManager sessionManager = new SessionManager(Dashboard.this, SessionManager.SESSION_USERSESSION);
        HashMap<String, String> usersDetails = sessionManager.getUsersDetailFromSession();
        String fullName = usersDetails.get(SessionManager.KEY_NAME);
        return fullName;
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    private void naviagtionDrawer(){

        //Naviagtion Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.nav_feedback:
                startActivity(new Intent(getApplicationContext(), Feedback.class));
                menuItem.setCheckable(false);
                break;
            case R.id.nav_home:
                startActivity(new Intent(getApplicationContext(), Dashboard.class));
                break;

            case R.id.nav_logout:
                startActivity(new Intent(getApplicationContext(), Login.class));
                Toast.makeText(Dashboard.this, "Jeni shkyqur me sukses", Toast.LENGTH_SHORT).show();

        }
        return true;
    }

}