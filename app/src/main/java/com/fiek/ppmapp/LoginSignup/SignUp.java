package com.fiek.ppmapp.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fiek.ppmapp.Home.Dashboard;
import com.fiek.ppmapp.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.FirebaseDatabase;


public class SignUp extends AppCompatActivity {


    TextInputLayout regName, regUsername, regEmail, regPhoneNo, regPassword;
    Button regBtn, regToLoginBtn;
    ImageView fotoNalt;
    TextView regLogoText, regSloganText;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);


        regName = findViewById(R.id.reg_name);
        regUsername = findViewById(R.id.reg_username);
        regEmail = findViewById(R.id.reg_email);
        regPhoneNo = findViewById(R.id.reg_phoneNo);
        regPassword = findViewById(R.id.reg_password);
        regBtn = findViewById(R.id.reg_btn);
        regToLoginBtn = findViewById(R.id.reg_login_btn);
        fotoNalt = findViewById(R.id.fotonalt);
        regLogoText = findViewById(R.id.mirid);
        regSloganText = findViewById(R.id.regjid);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);

    }

    private boolean validateName() {
        String val = regName.getEditText().getText().toString();

        if (val.isEmpty()) {
            regName.setError("Hapsira nuk duhet te jete e zbrazet");
            return false;
        } else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUsername() {
        String val = regUsername.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            regUsername.setError("Hapsira nuk duhet te jete e zbrazet");
            return false;
        } else if (val.length() >= 15) {
            regUsername.setError("Emri i perdoruesit shume i gjate");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            regUsername.setError("Hapsirat nuk lejohen");
            return false;
        } else {
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = regEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            regEmail.setError("Hapsira nuk duhet te jete e zbrazet");
            return false;
        } else if (!val.matches(emailPattern)) {
            regEmail.setError("Formati i email-it nuk eshte i sakte");
            return false;
        } else {
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhoneNo() {
        String val = regPhoneNo.getEditText().getText().toString();

        if (val.isEmpty()) {
            regPhoneNo.setError("Hapsira nuk duhet te jete e zbrazet");
            return false;
        } else {
            regPhoneNo.setError(null);
            regPhoneNo.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = regPassword.getEditText().getText().toString();
        String passwordVal = "^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
//                "(?=.*[a-zA-Z])" +      //any letter
//                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{8,}" +               //at least 8 characters
                "$";

        if (val.isEmpty()) {
            regPassword.setError("Hapsira nuk duhet te jete e zbrazet");
            return false;
        } else if (!val.matches(passwordVal)) {
            regPassword.setError("Fjalekalimi eshte i dobet");
            return false;
        } else {
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }
    }

    public void registerUser(View view) {

        if(!validateName() | !validateUsername() | !validateEmail() | !validatePhoneNo() | !validatePassword()){
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        String name = regName.getEditText().getText().toString();
        String username = regUsername.getEditText().getText().toString();
        String email = regEmail.getEditText().getText().toString();
        String phoneNo = regPhoneNo.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();

        UserHelperClass helperClass = new UserHelperClass(name, username, email, phoneNo, password);
        FirebaseDatabase.getInstance().getReference("users").child(username).setValue(helperClass);

        SessionManager sessionManager = new SessionManager(SignUp.this,SessionManager.SESSION_USERSESSION);
        sessionManager.createLoginSession(name,username,phoneNo,email,password);

        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
        startActivity(intent);
        finish();

    }

    public void callLoginScreen(View view){
        Intent intent = new Intent(SignUp.this, Login.class);
        Pair[] pairs = new Pair[7];

        pairs[0] = new Pair<View, String>(fotoNalt, "logo_name");
        pairs[1] = new Pair<View, String>(regLogoText, "logo_text");
        pairs[2] = new Pair<View, String>(regSloganText, "logo_desc");
        pairs[3] = new Pair<View, String>(regUsername, "usenrame_tran");
        pairs[4] = new Pair<View, String>(regPassword, "password_tran");
        pairs[5] = new Pair<View, String>(regBtn, "button_tran");
        pairs[6] = new Pair<View, String>(regToLoginBtn, "login_signup_tran");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp.this, pairs);
            startActivity(intent, options.toBundle());

        }
    }
}