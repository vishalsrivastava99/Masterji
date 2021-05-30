package com.example.Masterji;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
       // getSupportActionBar().hide();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                //Intent intent = new Intent(Splash.this, Student_Login.class);
                Intent intent = new Intent(Splash.this, Landing_Page.class);
                startActivity(intent);
                finish();
            }
        },2500);


    }
}