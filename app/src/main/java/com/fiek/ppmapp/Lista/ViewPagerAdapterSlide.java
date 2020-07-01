package com.fiek.ppmapp.Lista;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

public class ViewPagerAdapterSlide extends PagerAdapter {
    private Context context;
    private String[] imageURLS;

    public ViewPagerAdapterSlide(Context context, String[] imageURLS) {
        this.context = context;
        this.imageURLS = imageURLS;
    }

    @Override
    public int getCount() {
        return imageURLS.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        Picasso.get().load(imageURLS[position]).fit().centerCrop().into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
