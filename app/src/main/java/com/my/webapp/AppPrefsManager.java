package com.my.webapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.my.webapp.view.SplashScreen;

public class AppPrefsManager {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context mContext;

    private static final String DB_NAME = "web_app_db";


    private static final String KEY_USER_URL = "user_url";
    private static final String KEY_IS_EXIT_URL = "exist_url";


    @SuppressLint("CommitPrefEdits")
    public AppPrefsManager(Context context) {
        this.mContext = context;
        preferences = this.mContext.getSharedPreferences(DB_NAME, 0);
        editor = preferences.edit();

    }


    public boolean isExistUrl() {
        return preferences.getBoolean(KEY_IS_EXIT_URL, false);
    }

    public void setKeyUrlStatus(boolean status
    ) {
        editor.putBoolean(KEY_IS_EXIT_URL, status);
        editor.apply();
    }

    public String getKeyUserUrl() {
        return preferences.getString(KEY_USER_URL, "");
    }


    public void setKeyUserUrl(String url
    ) {
        editor.putString(KEY_USER_URL, url);
        editor.apply();
    }

    private void clearSession() {
        editor.clear();
        editor.apply();
    }

    public void clearUrl() {

        clearSession();

        Intent intent = new Intent(mContext, SplashScreen.class);
        // Closing all the Activities
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        mContext.startActivity(intent);


    }


}
