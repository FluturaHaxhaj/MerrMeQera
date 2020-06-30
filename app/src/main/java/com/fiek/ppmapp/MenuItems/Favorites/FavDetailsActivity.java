package com.fiek.ppmapp.MenuItems.Favorites;

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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fiek.ppmapp.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class FavDetailsActivity extends AppCompatActivity {
    private static final int REQUEST_CALL = 1;
    String telefoni;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fav_details);

        String shtepi = getIntent().getExtras().getString("fav_shtepi");
        String pershkrimi = getIntent().getExtras().getString("fav_pershkrimi");
        String lokacioni = getIntent().getExtras().getString("fav_lokacioni");
        String cmimi = getIntent().getExtras().getString("fav_cmimi");
        String siperfaqja = getIntent().getExtras().getString("fav_siperfaqja");
        String kate = getIntent().getExtras().getString("fav_dhoma");
        telefoni = getIntent().getExtras().getString("fav_tel");
        String image_url = getIntent().getExtras().getString("fav_img");

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.shcollapsingtoolbar_id_fav);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView tv_shtepi = findViewById(R.id.sh_shtepi_fav);
        TextView tv_cmimi = findViewById(R.id.sh_cmimi_fav);
        TextView tv_pershkrimi = findViewById(R.id.sh_pershkrimi_fav);
        TextView tv_kate = findViewById(R.id.sh_kate_fav);
        TextView tv_siperfaqja = findViewById(R.id.sh_siperfaqja_fav);
        ImageView img = findViewById(R.id.sh_thumbnail_fav);
        Button telBtn = findViewById(R.id.tel_btn_fav);


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
        if (ContextCompat.checkSelfPermission(FavDetailsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(FavDetailsActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
        }else {
            String dial = "tel:"+numri;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
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
