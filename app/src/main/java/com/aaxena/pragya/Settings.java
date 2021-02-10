package com.aaxena.pragya;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Settings extends AppCompatActivity {
    private static final String PREFS_NAME = "Vibration";
    String TEXT = "text";
    private static final String LONG_SPLASH = "Splash";
    String SPLASH = "splash";
    MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ImageView longSplash = findViewById(R.id.switch_on);
        SharedPreferences sharedPreferences = getSharedPreferences(LONG_SPLASH, Context.MODE_PRIVATE);
        String splash_settings = sharedPreferences.getString(SPLASH,"on");
        if (splash_settings.equals("off"))
        {
         longSplash.setBackgroundResource(R.drawable.switch_off);
        }
        else {
            longSplash.setBackgroundResource(R.drawable.switch_on);
        }
        longSplash.setOnClickListener(v -> {
            vibrateDevice();
            playSound();
            SharedPreferences settings = Settings.this.getSharedPreferences(LONG_SPLASH, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            if (splash_settings.equals("off"))
            {
                editor.putString(SPLASH, "on");
                editor.commit();
                Toast.makeText(Settings.this,"BIOS Like Startup Enabled",Toast.LENGTH_SHORT).show();
                longSplash.setBackgroundResource(R.drawable.switch_on);
                recreate();
            }
            else{
                editor.putString(SPLASH, "off");
                editor.commit();
                Toast.makeText(Settings.this,"BIOS Like Startup Disabled",Toast.LENGTH_SHORT).show();
                longSplash.setBackgroundResource(R.drawable.switch_off);
                recreate();
            }
        });
    }

    private void playSound() {
        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.stop();
            mPlayer.reset();
        }
        mPlayer = MediaPlayer.create(this, R.raw.short_click);
        mPlayer.start();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent toLanding = new Intent(Settings.this,Landing.class);
        startActivity (toLanding);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
    private void vibrateDevice() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String vibration_setting = sharedPreferences.getString(TEXT,"on");

        if(vibration_setting.equals("on")) {
            Vibrator v3 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v3.vibrate(VibrationEffect.createOneShot(28, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                //deprecated in API 26
                v3.vibrate(25);
            }
        }
        else {
            Vibrator v3 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v3.vibrate(0);
        }
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
}