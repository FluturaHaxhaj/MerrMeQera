package com.fiek.ppmapp.MenuItems;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fiek.ppmapp.R;

public class Feedback extends AppCompatActivity {
    RatingBar mRatingBar;
    TextView mRatingScale;
    EditText mFeedback;
    Button mSendFeedback;
    ImageView backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_feedback);


        mRatingBar = (RatingBar) findViewById(R.id.ratingBar);
        mRatingScale = (TextView) findViewById(R.id.tvRatingScale);
        mFeedback = (EditText) findViewById(R.id.etFeedback);
        mSendFeedback = (Button) findViewById(R.id.btnSubmit);
        backBtn = findViewById(R.id.back_btn_feedback);

        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mRatingScale.setText(String.valueOf(v));
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        mRatingScale.setText("Tmershëm");
                        break;
                    case 2:
                        mRatingScale.setText("Keq");
                        break;
                    case 3:
                        mRatingScale.setText("Në rregull");
                        break;
                    case 4:
                        mRatingScale.setText("Shume mirë");
                        break;
                    case 5:
                        mRatingScale.setText("Perfekt");
                        break;
                    default:
                        mRatingScale.setText("");
                }
            }
        });

        mSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFeedback.getText().toString().isEmpty()) {
                    Toast.makeText(Feedback.this, "Ju lutemi plotësoni kutinë e tekstit të feedback ", Toast.LENGTH_LONG).show();
                } else {
                    mFeedback.setText("");
                    mRatingBar.setRating(0);
                    Toast.makeText(Feedback.this, "Faleminderit për shpërndarje të feedback", Toast.LENGTH_SHORT).show();
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Feedback.super.onBackPressed();
            }
        });



    }



}
