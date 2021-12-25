package com.my.webapp.view;

import android.os.Bundle;
import android.os.Handler;

import com.my.webapp.R;
import com.my.webapp.application.AppBaseActivity;


public class SplashScreen extends AppBaseActivity implements Runnable {

    private static final int SPLASH_DELAY = 1500;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

    }

    @Override
    protected void onStart() {
        super.onStart();
        handler = new Handler();
        handler.postDelayed(this, SPLASH_DELAY);
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(this);
        handler = null;
    }


    @Override
    public void run() {
        MainActivity.start(this, "https://google.com/");

    }

}