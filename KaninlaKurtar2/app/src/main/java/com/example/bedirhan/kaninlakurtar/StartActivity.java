package com.example.bedirhan.kaninlakurtar;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.messaging.FirebaseMessaging;

public class StartActivity extends AppCompatActivity {
    private static  int SPLASH_TIME_OUT=1000; //1 saniyelik bekleme süresi tanımlandı

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        FirebaseMessaging.getInstance().subscribeToTopic("all");
        //Butonsuz geçiş
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
