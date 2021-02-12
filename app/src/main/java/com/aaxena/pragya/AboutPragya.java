package com.aaxena.pragya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ScrollView;

public class AboutPragya extends AppCompatActivity {

    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_pragya);

        scrollView = findViewById(R.id.about_pragya);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent toLanding = new Intent(AboutPragya.this,Landing.class);
        startActivity (toLanding);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}

