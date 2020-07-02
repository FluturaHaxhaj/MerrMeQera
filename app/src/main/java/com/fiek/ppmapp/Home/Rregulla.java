package com.fiek.ppmapp.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.view.WindowManager;

import com.fiek.ppmapp.R;

import java.util.ArrayList;
import java.util.List;

public class Rregulla extends AppCompatActivity {

    ViewPager viewPager;
    Adapter adapter;
    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_rregulla);

        models = new ArrayList<>();
        models.add(new Model(R.drawable.img1, "Një qiramarrës i mirë është përgjegjës", "Një qiramarrës i mirë jo vetëm që paguan qiranë dhe faturat e tjera në kohë, por ai kujdeset për çështjet e mirëmbajtjes ditore që janë përgjegjësi e tij.\n" +
                "\n" +
                "Përpikmëria është treguesi i parë nëse një qiramarrës i mundshëm është përgjegjës. Një qiramarrës i përgjegjshëm do të paguajë qiranë e tij në kohë dhe në tërësi, dhe natyrisht, ai nuk do të dëmtojë pronën."));
        models.add(new Model(R.drawable.img2, "Një qiramarrës i mirë është respektues", "Respekti nuk është i kufizuar vetëm në mënyrën se si ai ju trajton. Nëse qiramarrësi nuk është i respektueshëm, ai mund të abuzojë me pronën duke bërë gjëra të tilla si përplas dyert ose duke i lënë fëmijët e tij të ngjyrosin në mure. Ose, ai thjesht mund të neglizhojë pronën sepse nuk ju respekton sa duhet për t'u kujdesur."));
        models.add(new Model(R.drawable.img3, "Një qiramarrës i mirë është i pastër", " Potencialisht, sa më e madhe rrëmuja që bën qiramarrësi gjatë kohës që ai është në pronën tuaj, aq më e madhe është rrëmuja me të cilën do të keni të bëni kur ai largohet."));
        models.add(new Model(R.drawable.img4, "Një qiramarrës i mirë është në gjendje të paguajë", "Nëse qiramarrësi nuk është në gjendje të përballojë qiranë, nuk duhet të habiteni kur ai nuk e paguan qiranë."));
        models.add(new Model(R.drawable.img5, "Një qiramarrës i mirë është pa dramë", "Disa qiramarrës lulëzojnë në dramë. Këta janë qiramarrësit që thërrasin me justifikim pas shfajësimit se pse qiraja është vonë.Një qiramarrës i mirë nuk bën justifikime dhe nuk ju shqetëson pa nevojë me detajet e jetës së tij."));

        adapter = new Adapter(models, this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4),
                getResources().getColor(R.color.color5)
        };

        colors = colors_temp;

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (position < (adapter.getCount() - 1) && position < (colors.length - 1)) {
                    viewPager.setBackgroundColor(

                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]
                            )
                    );
                } else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
