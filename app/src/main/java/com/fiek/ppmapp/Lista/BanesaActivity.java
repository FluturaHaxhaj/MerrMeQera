package com.fiek.ppmapp.Lista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.fiek.ppmapp.R;

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

public class BanesaActivity extends AppCompatActivity {
    private static final int REQUEST_CALL = 1;
    String telefoni;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_banesa);

        String banesa = getIntent().getExtras().getString("b_banesa");
        String pershkrimi = getIntent().getExtras().getString("b_pershkrimi");
        String lokacioni = getIntent().getExtras().getString("b_lokacioni");
        String cmimi = getIntent().getExtras().getString("b_cmimi");
        String siperfaqja = getIntent().getExtras().getString("b_siperfaqja");
        String dhoma = getIntent().getExtras().getString("b_dhoma");
        telefoni = getIntent().getExtras().getString("b_tel");
        String image_url = getIntent().getExtras().getString("b_img");

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView tv_banesa = findViewById(R.id.b_banesa);
        TextView tv_cmimi = findViewById(R.id.b_cmimi);
        TextView tv_pershkrimi = findViewById(R.id.b_pershkrimi);
        TextView tv_dhoma = findViewById(R.id.b_dhoma);
        TextView tv_siperfaqja = findViewById(R.id.b_siperfaqja);
        ImageView img = findViewById(R.id.b_thumbnail);
        Button telBtn = findViewById(R.id.tel_btn);


        tv_banesa.setText(banesa);
        tv_siperfaqja.setText(siperfaqja);
        tv_dhoma.setText(dhoma);
        tv_cmimi.setText(cmimi);
        tv_pershkrimi.setText(pershkrimi);
        String btnText = (String) telBtn.getText();
        telBtn.setText(btnText+telefoni);
        collapsingToolbarLayout.setTitle(banesa);

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
        if (ContextCompat.checkSelfPermission(BanesaActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(BanesaActivity.this,
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