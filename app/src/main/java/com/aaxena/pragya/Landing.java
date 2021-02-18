package com.aaxena.pragya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import static android.provider.Telephony.Mms.Part.TEXT;

public class Landing extends AppCompatActivity {
    private static final String PREFS_NAME = "Vibration";
    String TEXT = "text";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                vibrateDevice();
                Intent toSettings = new Intent(Landing.this, Settings.class);
                startActivity(toSettings);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
                break;
            case R.id.devs:
                vibrateDevice();
                Intent toDevs = new Intent(Landing.this, AboutDevs.class);
                startActivity(toDevs);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
                break;
            case R.id.about:
                vibrateDevice();
                Intent toAboutPragya = new Intent(Landing.this, AboutPragya.class);
                startActivity(toAboutPragya);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
                break;
            case R.id.profile:
                vibrateDevice();
                Intent toProfile = new Intent(Landing.this, UserInfo.class);
                startActivity(toProfile);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
                break;
        }
        return true;
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
}