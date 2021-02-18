package com.aaxena.pragya;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


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

        int splash_screen_short = 2000;
        new Handler().postDelayed(() -> {
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.short_click);
            mp.start();
            Intent toLanding = new Intent(this, Landing.class);
            startActivity(toLanding);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }, splash_screen_short);

    }
}