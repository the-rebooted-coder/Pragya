package com.aaxena.pragya;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        setAudio();
        playVideo();
        playAudio();
        goToNextScreen();
    }

    private void goToNextScreen() {
        int splash_screen_sound = 23000;
        new Handler().postDelayed(() -> {
            Button start = findViewById(R.id.firstStart);
            start.setVisibility(View.VISIBLE);
            start.setOnClickListener(v -> {
                Intent i = new Intent(SplashScreen.this, SignUp.class);
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            });
        }, splash_screen_sound);}

    private void setAudio() {
         /*
        Sets Media Volume up so that if the user's volume is low the audio still sounds
         */
        AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 10, 0);
    }

    private void playAudio() {
        int keyboard_sound = 4600;
        new Handler().postDelayed(() -> {
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.pragya_keyboard);
            mp.start();
        }, keyboard_sound);}

    private void playVideo() {
        VideoView videoview = findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.splash_long);
        videoview.setVideoURI(uri);
        videoview.start();
    }

}