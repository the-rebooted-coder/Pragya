package com.aaxena.pragya;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class SplashScreenShort extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_short);
        textView = findViewById(R.id.textView);
        String[] names = getResources().getStringArray(R.array.loading_msg);
        int randomIndex = new Random().nextInt(names.length);
        String randomName = names[randomIndex];
        textView.setText(randomName);
    }
}