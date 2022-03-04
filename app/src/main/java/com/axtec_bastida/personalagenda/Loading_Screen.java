package com.axtec_bastida.personalagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Loading_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        int Time =3000;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               startActivity(new Intent(Loading_Screen.this, MainActivity.class));
                finish();
            }
        },Time);
    }
}