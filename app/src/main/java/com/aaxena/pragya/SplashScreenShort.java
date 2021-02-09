package com.aaxena.pragya;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class SplashScreenShort extends AppCompatActivity {

    private String[] names;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_short);
        int random_msg = new Random().nextInt (names.length);
        String randomName = names[random_msg];
        textView.setText(randomName);
        names = getResources().getStringArray(R.array.loading_msg);
    }
}