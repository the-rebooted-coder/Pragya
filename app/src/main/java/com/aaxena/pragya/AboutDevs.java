package com.aaxena.pragya;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class AboutDevs extends AppCompatActivity {

    CarouselView carouselView;

    int[] sampleImages = {R.drawable.spandan,R.drawable.shrish,R.drawable.shubham,R.drawable.sriniv};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_devs);

        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
    }
    ImageListener imageListener = (position, imageView) -> imageView.setImageResource(sampleImages[position]);
}