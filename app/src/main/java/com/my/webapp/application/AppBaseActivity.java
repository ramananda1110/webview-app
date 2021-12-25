package com.my.webapp.application;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import com.my.webapp.R;


public class AppBaseActivity extends AppCompatActivity {


    @Override
    public void finish() {
        super.finish();
        overridePendingTransitionExit();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);

        overridePendingTransitionEnter();
    }


    /**
     * Overrides the pending Activity transition by performing the "Enter" animation.
     */
    protected void overridePendingTransitionEnter() {

        //overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        //overridePendingTransition(R.anim.animate_slide_up_enter, R.anim.animate_slide_up_exit);

    }

    /**
     * Overrides the pending Activity transition by performing the "Exit" animation.
     */
    protected void overridePendingTransitionExit() {
        //overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
        //overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        // overridePendingTransition(R.anim.animate_slide_down_enter, R.anim.animate_slide_down_exit);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransitionExit();
    }
}
