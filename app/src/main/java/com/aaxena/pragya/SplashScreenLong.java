package com.aaxena.pragya;

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
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import org.tensorflow.lite.examples.classification.ClassifierActivity;

public class SplashScreenLong extends AppCompatActivity {
    private static final String LONG_SPLASH = "Splash";
    String SPLASH = "splash";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_long);
        splashCheck();
    }

    private void splashCheck() {
        SharedPreferences sharedPreferences = getSharedPreferences(LONG_SPLASH, Context.MODE_PRIVATE);
        String splash_settings = sharedPreferences.getString(SPLASH,"on");
        if (splash_settings.equals("off")){
            Intent toShortSplash = new Intent(SplashScreenLong.this,SplashScreenShort.class);
            startActivity(toShortSplash);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }
        else
        {
            setAudio();
            playVideo();
            playAudio();
            goToNextScreen();
        }

    }

    private void goToNextScreen() {
        int splash_screen_sound = 28000;
        new Handler().postDelayed(() -> {
                check();
                final MediaPlayer mp = MediaPlayer.create(this, R.raw.short_click);
                mp.start();
                Vibrator v2 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v2.vibrate(VibrationEffect.createOneShot(28, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    //deprecated in API 26
                    v2.vibrate(25);
                }
        }, splash_screen_sound);}

    private void check() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (account !=null){
            //User Signed In, Proceeding to Landing
            Intent i=new Intent(SplashScreenLong.this, ClassifierActivity.class);
            startActivity(i);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }
        else {
            //Newbie
            Intent i=new Intent(SplashScreenLong.this,WelcomeActivity.class);
            startActivity(i);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }
    }

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
}