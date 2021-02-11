package com.aaxena.pragya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

public class AboutPragya extends AppCompatActivity {

    ScrollView txtmarquee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_pragya);

        /*txtmarquee=(ScrollView) findViewById(R.id.Aboutpragya);
        txtmarquee.setSelected(true);
*/
        final ScrollView scrollview = ((ScrollView) findViewById(R.id.Aboutpragya));
        scrollview.post(new Runnable() {
            @Override
            public void run() {
                scrollview.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
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

