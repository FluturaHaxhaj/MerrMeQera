package com.fiek.ppmapp.Lista;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fiek.ppmapp.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ShtepiActivity extends AppCompatActivity {
    private static final int REQUEST_CALL = 1;

    String telefoni;
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
        telefoni = getIntent().getExtras().getString("sh_tel");
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
                makePhoneCall();
            }
        });
        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

        Glide.with(this).load(image_url).apply(requestOptions).into(img);

    }
    private void makePhoneCall(){
        String numri = telefoni;
        if (ContextCompat.checkSelfPermission(ShtepiActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(ShtepiActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
        }else {
            String dial = "tel:"+numri;
            startActivity(new Intent(Intent.ACTION_CALL,Uri.parse(dial)));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }else {
                Toast.makeText(this,"Nuk keni akses",Toast.LENGTH_LONG).show();
            }
        }
    }
}