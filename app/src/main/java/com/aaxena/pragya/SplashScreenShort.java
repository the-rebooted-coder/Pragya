package com.aaxena.pragya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

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
        check();
    }

    private void check() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (account !=null){
            //User Signed In, Proceeding to Landing
            Intent i=new Intent(SplashScreenShort.this,Landing.class);
            startActivity(i);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }
        else {
            //Newbie
            Intent i=new Intent(SplashScreenShort.this,SignUp.class);
            startActivity(i);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }
    }
}