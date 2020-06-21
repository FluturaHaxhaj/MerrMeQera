package com.fiek.ppmapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class Login extends AppCompatActivity {

    Button callSignUp, login_btn;
    ImageView image;
    TextView logoText, sloganText;
    TextInputLayout username, password;
    EditText  usernameEditText , passwordEditText;
    CheckBox remember;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        callSignUp = findViewById(R.id.signup_screen);
        image = findViewById(R.id.Logo_image);
        logoText = findViewById(R.id.Logo_name);
        sloganText = findViewById(R.id.slogan_name);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.login_btn);
        remember = findViewById(R.id.remember);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);


        SessionManager sessionManager = new SessionManager(Login.this,SessionManager.SESSION_REMEMBER);
        if(sessionManager.checkRemember()){
            HashMap<String,String> rememberDetais = sessionManager.getRememberDetailFromSession();
            usernameEditText.setText(rememberDetais.get(SessionManager.KEY_SESSIONUSERNAME));
            passwordEditText.setText(rememberDetais.get(SessionManager.KEY_SESSIONPASSWORD));

        }

    }

    private Boolean validateUsername() {
        String val = username.getEditText().getText().toString();

        if (val.isEmpty()) {
            username.setError("Hapsira nuk duhet te jete e zbrazet");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = username.getEditText().getText().toString();


        if (val.isEmpty()) {
            password.setError("Hapsira nuk duhet te jete e zbrazet");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    public void loginUser(View view) {
        //Validate Login Info
        if (!validateUsername() | !validatePassword()) {
            return;
        } else {
            isUser();
        }

    }



    private void isUser() {
        progressBar.setVisibility(View.VISIBLE);
        final String userEnteredUsername = username.getEditText().getText().toString().trim();
        final String userEnteredPassword = password.getEditText().getText().toString().trim();

        if(remember.isChecked()){
            SessionManager sessionManager = new SessionManager(Login.this,SessionManager.SESSION_REMEMBER);
            sessionManager.createRememberSession(userEnteredUsername,userEnteredPassword);
        }

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    username.setError(null);
                    username.setErrorEnabled(false);
                    String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);
                    if (passwordFromDB.equals(userEnteredPassword)) {
                        username.setError(null);
                        username.setErrorEnabled(false);
                        String nameFromDB = dataSnapshot.child(userEnteredUsername).child("name").getValue(String.class);
                        String usernameFromDB = dataSnapshot.child(userEnteredUsername).child("username").getValue(String.class);
                        String phoneNoFromDB = dataSnapshot.child(userEnteredUsername).child("phoneNo").getValue(String.class);
                        String emailFromDB = dataSnapshot.child(userEnteredUsername).child("email").getValue(String.class);


                        //User Session
                        SessionManager sessionManager = new SessionManager(Login.this,SessionManager.SESSION_USERSESSION);
                        sessionManager.createLoginSession(nameFromDB,usernameFromDB,phoneNoFromDB,emailFromDB,passwordFromDB);



                        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
//                        intent.putExtra("name", nameFromDB);
//                        intent.putExtra("username", usernameFromDB);
//                        intent.putExtra("email", emailFromDB);
//                        intent.putExtra("phoneNo", phoneNoFromDB);
//                        intent.putExtra("password", passwordFromDB);
                        intent.putExtra("emri",nameFromDB);
                        startActivity(intent);

                        finish();
                    } else {
                        progressBar.setVisibility(View.GONE);
                        username.setError("Emri i perdoruesit ose fjalekalimi eshte gabim");
                        password.setError("Emri i perdoruesit ose fjalekalimi eshte gabim");
                        password.requestFocus();
                    }
                } else {
                    progressBar.setVisibility(View.GONE);
                    username.setError("Emri i perdoruesit ose fjalekalimi eshte gabim");
                    password.setError("Emri i perdoruesit ose fjalekalimi eshte gabim");
                    password.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void callSignUpScreen(View view){
        Intent intent = new Intent(Login.this, SignUp.class);
        Pair[] pairs = new Pair[7];

        pairs[0] = new Pair<View, String>(image, "logo_name");
        pairs[1] = new Pair<View, String>(logoText, "logo_text");
        pairs[2] = new Pair<View, String>(sloganText, "logo_desc");
        pairs[3] = new Pair<View, String>(username, "usenrame_tran");
        pairs[4] = new Pair<View, String>(password, "password_tran");
        pairs[5] = new Pair<View, String>(login_btn, "button_tran");
        pairs[6] = new Pair<View, String>(callSignUp, "login_signup_tran");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
            startActivity(intent, options.toBundle());

        }
    }
}
