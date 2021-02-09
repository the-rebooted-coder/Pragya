package com.aaxena.pragya;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenLong extends AppCompatActivity {
    String TEXT = "text";
    private static final String PREFS_NAME = "Vibration";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_long);
        setAudio();
        playVideo();
        playAudio();
        goToNextScreen();
    }

    private void goToNextScreen() {
        int splash_screen_sound = 27000;
        new Handler().postDelayed(() -> {
            Button start = findViewById(R.id.firstStart);
            start.setVisibility(View.VISIBLE);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            start.setOnClickListener(v -> {
                final MediaPlayer mp = MediaPlayer.create(this, R.raw.short_click);
                mp.start();
                vibrateDevice();
                //Intent i = new Intent(SplashScreenLong.this, SignUp.class);
                Intent i = new Intent(SplashScreenLong.this, SignUp.class);
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
        int keyboard_sound = 5300;
        new Handler().postDelayed(() -> {
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.pragya_keyboard);
            mp.start();
        }, keyboard_sound);
    }

    private void playVideo() {
        /*
        Playing splash screen video
         */
        VideoView videoview = findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.splash_long);
        videoview.setVideoURI(uri);
        videoview.start();
    }

    @SuppressLint("ApplySharedPref")
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (action == KeyEvent.ACTION_DOWN) {
                    SharedPreferences settings = this.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString(TEXT, "on");
                    editor.commit();
                    Toast.makeText(this,"Vibrations Turned On",Toast.LENGTH_SHORT).show();
                }
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_DOWN) {
                    vibrateDevice();
                    int vib_delay = 100;
                    new Handler().postDelayed(() -> {
                        Vibrator v4 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            v4.vibrate(VibrationEffect.createOneShot(35, VibrationEffect.DEFAULT_AMPLITUDE));
                        } else {
                            //deprecated in API 26
                            v4.vibrate(30);
                        }
                    }, vib_delay);
                    SharedPreferences settings = this.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString(TEXT, "off");
                    editor.commit();
                    Toast.makeText(this,"Vibrations Turned Off",Toast.LENGTH_SHORT).show();

                }
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    }

    private void vibrateDevice() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String vibration_setting = sharedPreferences.getString(TEXT,"on");
        if(vibration_setting.equals("on")) {
            Vibrator v2 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v2.vibrate(VibrationEffect.createOneShot(28, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                //deprecated in API 26
                v2.vibrate(25);
            }
        }
        else {
            Vibrator v3 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v3.vibrate(0);
        }
    }
}