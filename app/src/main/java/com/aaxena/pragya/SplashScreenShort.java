package com.aaxena.pragya;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.cis470.lakiel.ocrreader.ScannerActivity;

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
            vibrateDevice();
            setAudio();
            Intent toLanding = new Intent(this, ScannerActivity.class);
            startActivity(toLanding);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }, splash_screen_short);

    }
    private void setAudio() {
         /*
        Sets Media Volume up so that if the user's volume is low the audio still sounds
         */
        AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 10, 0);
    }
    private void vibrateDevice() {
            Vibrator v3 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v3.vibrate(VibrationEffect.createOneShot(28, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                //deprecated in API 26
                v3.vibrate(25);
            }
    }
}