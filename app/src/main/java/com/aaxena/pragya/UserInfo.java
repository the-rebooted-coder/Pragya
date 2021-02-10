package com.aaxena.pragya;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

/*
Bit by Bit Information
@author Spandan Saxena aka the-rebooted-coder
 */

public class UserInfo extends AppCompatActivity {
    private static final String PREFS_NAME = "Vibration";
    String TEXT = "text";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        TextView user_bios = findViewById(R.id.current_user);
        user_bios.setText("->JUETPragya % ");
        showInfoAsBios();
    }

    @SuppressLint("SetTextI18n")
    private void showInfoAsBios() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        String personName = account.getDisplayName();
        String personEmail = account.getEmail();

        TextView user_bios = findViewById(R.id.current_user);

        int splash_screen_short = 600;
        new Handler().postDelayed(() -> {
            user_bios.setText("->JUETPragya % u_");
        }, splash_screen_short);
        int splash_screen_short2 = 1200;
        new Handler().postDelayed(() -> {
            user_bios.setText("->JUETPragya % us_");
        }, splash_screen_short2);
        int splash_screen_short3 = 1800;
        new Handler().postDelayed(() -> {
            user_bios.setText("->JUETPragya % use_");
        }, splash_screen_short3);
        int splash_screen_short4 = 2400;
        new Handler().postDelayed(() -> {
            user_bios.setText("->JUETPragya % user_");
        }, splash_screen_short4);
        int splash_screen_short5 = 3000;
        new Handler().postDelayed(() -> {
            user_bios.setText("->JUETPragya % user\n_");
        }, splash_screen_short5);
        int splash_screen_short6 = 3600;
        new Handler().postDelayed(() -> {
            user_bios.setText("->JUETPragya % user\n\n====="+personName+"=====");
        }, splash_screen_short6);
        int splash_screen_short7 = 4200;
        new Handler().postDelayed(() -> {
            user_bios.setText("->JUETPragya % user\n\n====="+personName+"=====\n\n->JUETPragya % e_");
        }, splash_screen_short7);
        int splash_screen_short8 = 4800;
        new Handler().postDelayed(() -> {
            user_bios.setText("->JUETPragya % user\n\n====="+personName+"=====\n\n->JUETPragya % em_");
        }, splash_screen_short8);
        int splash_screen_short9 = 5400;
        new Handler().postDelayed(() -> {
            user_bios.setText("->JUETPragya % user\n\n====="+personName+"=====\n\n->JUETPragya % ema_");
        }, splash_screen_short9);
        int splash_screen_short10 = 6000;
        new Handler().postDelayed(() -> {
            user_bios.setText("->JUETPragya % user\n\n====="+personName+"=====\n\n->JUETPragya % emai_");
        }, splash_screen_short10);
        int splash_screen_short11 = 6600;
        new Handler().postDelayed(() -> {
            user_bios.setText("->JUETPragya % user\n\n====="+personName+"=====\n\n->JUETPragya % email_");
        }, splash_screen_short11);
        int splash_screen_short12 = 7200;
        new Handler().postDelayed(() -> {
            user_bios.setText("->JUETPragya % user\n\n====="+personName+"=====\n\n->JUETPragya % email\n\n====="+personEmail+"=====\n\n\n->JUETPragya % _");
        }, splash_screen_short12);
        int splash_screen_short13 = 7900;
        new Handler().postDelayed(() -> {
            user_bios.setText("->JUETPragya % user\n\n====="+personName+"=====\n\n->JUETPragya % email\n\n====="+personEmail+"=====\n\n\n->JUETPragya % Double Tap To Logout.");
        }, splash_screen_short13);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent toLanding = new Intent(UserInfo.this,Landing.class);
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