package com.fiek.ppmapp.Lista;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fiek.ppmapp.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ShtepiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_shtepi);

        String shtepi = getIntent().getExtras().getString("sh_shtepi");
        String pershkrimi = getIntent().getExtras().getString("sh_pershkrimi");
        String lokacioni = getIntent().getExtras().getString("sh_lokacioni");
        String cmimi = getIntent().getExtras().getString("sh_cmimi");
        String siperfaqja = getIntent().getExtras().getString("sh_siperfaqja");
        String kate = getIntent().getExtras().getString("sh_kate");
        String telefoni = getIntent().getExtras().getString("sh_tel");
        String image_url = getIntent().getExtras().getString("sh_img");

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.shcollapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView tv_shtepi = findViewById(R.id.sh_shtepi);
        TextView tv_cmimi = findViewById(R.id.sh_cmimi);
        TextView tv_pershkrimi = findViewById(R.id.sh_pershkrimi);
        TextView tv_kate = findViewById(R.id.sh_kate);
        TextView tv_siperfaqja = findViewById(R.id.sh_siperfaqja);
        ImageView img = findViewById(R.id.sh_thumbnail);
        Button telBtn = findViewById(R.id.tel_btn);


        tv_shtepi.setText(shtepi);
        tv_siperfaqja.setText(siperfaqja);
        tv_kate.setText(kate);
        tv_cmimi.setText(cmimi);
        tv_pershkrimi.setText(pershkrimi);
        String btnText = (String) telBtn.getText();
        telBtn.setText(btnText+telefoni);
        collapsingToolbarLayout.setTitle(shtepi);

        telBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse(telefoni));
                startActivity(callIntent);
            }
        });
        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

        Glide.with(this).load(image_url).apply(requestOptions).into(img);



    }
}