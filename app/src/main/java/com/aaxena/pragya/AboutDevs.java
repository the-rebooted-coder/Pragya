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
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.cis470.lakiel.ocrreader.ScannerActivity;

import java.util.ArrayList;
import java.util.List;

public class AboutDevs extends AppCompatActivity {
    private static final String PREFS_NAME = "Vibration";
    String TEXT = "text";
    ViewPager viewPager;
    Adapter adapter;
    List<Model> models;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_devs);
        viewPager = findViewById(R.id.viewPager);


        TextView developer_text = findViewById(R.id.developerOnInner);
        TextView terminal = findViewById(R.id.terminal);
        terminal.setVisibility(View.VISIBLE);
        developer_text.setVisibility(View.VISIBLE);
        viewPager.setVisibility(View.INVISIBLE);
        int splash_screen_short = 100;
        new Handler().postDelayed(() -> {
            terminal.setText("->JUETPragya % d_");
        }, splash_screen_short);
        int splash_screen_short1 = 300;
        new Handler().postDelayed(() -> {
            terminal.setText("->JUETPragya % de_");
        }, splash_screen_short1);
        int splash_screen_short2 = 600;
        new Handler().postDelayed(() -> {
            terminal.setText("->JUETPragya % dev_");
        }, splash_screen_short2);
        int splash_screen_short3 = 900;
        new Handler().postDelayed(() -> {
            terminal.setText("->JUETPragya % deve_");
        }, splash_screen_short3);
        int splash_screen_short4 = 1200;
        new Handler().postDelayed(() -> {
            terminal.setText("->JUETPragya % devel_");
        }, splash_screen_short4);
        int splash_screen_short5 = 1500;
        new Handler().postDelayed(() -> {
            terminal.setText("->JUETPragya % develo_");
        }, splash_screen_short5);
        int splash_screen_short6 = 1800;
        new Handler().postDelayed(() -> {
            terminal.setText("->JUETPragya % develop_");
        }, splash_screen_short6);
        int splash_screen_short7 = 2100;
        new Handler().postDelayed(() -> {
            terminal.setText("->JUETPragya % develope_");
        }, splash_screen_short7);
        int splash_screen_short8 = 2400;
        new Handler().postDelayed(() -> {
            terminal.setText("->JUETPragya % developers_");
        }, splash_screen_short8);
        int splash_screen_short9 = 2700;
        new Handler().postDelayed(() -> {
            terminal.setText("->JUETPragya % developers\n\n_");
        }, splash_screen_short9);
        int splash_screen_short10 = 2900;
        new Handler().postDelayed(() -> {
            terminal.setText("->JUETPragya % developers\n\n_");
            terminal.setVisibility(View.INVISIBLE);
            developer_text.setVisibility(View.INVISIBLE);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            viewPager.setVisibility(View.VISIBLE);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }, splash_screen_short10);

        models = new ArrayList<>();
        models.add(new Model(R.drawable.spandan, "Spandan Saxena", "BackEnd Developer","Developers"));
        models.add(new Model(R.drawable.shrish, "Shrish Sharma", "UI and UX","are just"));
        models.add(new Model(R.drawable.sriniv, "Srinivasan Bashyam", "Code Watcher","tools that transform"));
        models.add(new Model(R.drawable.shubham, "Shubham Munjani", "Text Slider","caffeine to code"));

        adapter = new Adapter(models, this);
        viewPager.setAdapter(adapter);
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