package com.aaxena.pragya;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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

    }

    /*
    Sets Media Volume up so that if the user's volume is low the audio still sounds
     */
    private void setAudio() {
        AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 10, 0);
    }

    private void playAudio() {
        int splash_screen_time_out = 4600;
        new Handler().postDelayed(() -> {
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.pragya_keyboard);
            mp.start();
        }, splash_screen_time_out);}

    private void playVideo() {
        VideoView videoview = findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.splash_long);
        videoview.setVideoURI(uri);
        videoview.start();
    }

}

/*
 Intent i = new Intent(SplashScreen.this, SignUp.class);
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
 */