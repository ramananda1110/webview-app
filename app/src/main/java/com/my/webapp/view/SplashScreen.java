package com.my.webapp.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.my.webapp.AppPrefsManager;
import com.my.webapp.R;
import com.my.webapp.application.AppBaseActivity;


@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppBaseActivity implements Runnable {

    private static final int SPLASH_DELAY = 1500;
    private Handler handler;
    AppPrefsManager appPrefsManager;

    public static void start(Context mContext) {
        Intent intent = new Intent(mContext, SplashScreen.class);

        mContext.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        appPrefsManager = new AppPrefsManager(this);

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

        if (appPrefsManager.isExistUrl()) {
            MainActivity.start(this, appPrefsManager.getKeyUserUrl());
        } else {
            AddUrlDialog dialogFragment = AddUrlDialog.display(getSupportFragmentManager(), "dialog_product");
        }
    }

}