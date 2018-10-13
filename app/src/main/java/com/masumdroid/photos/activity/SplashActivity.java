package com.masumdroid.photos.activity;


import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.masumdroid.photos.R;
import com.masumdroid.photos.appConstant.AppConstant;
import com.masumdroid.photos.preference.AppPreference;
import com.masumdroid.photos.preference.PrefKey;
import com.masumdroid.photos.utility.ActivityUtils;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullScreenActivity();
        setContentView(R.layout.activity_splash);
        splashThread();
    }

    private void splashThread() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (AppPreference.getInstance(SplashActivity.this).getBoolean(PrefKey.IS_LOGIN)) {
                    ActivityUtils.getInstance().invokeActivity(SplashActivity.this, MainActivity.class, true);
                } else {
                    ActivityUtils.getInstance().invokeActivity(SplashActivity.this, LoginActivity.class, true);
                }
            }
        }, AppConstant.SPLASH_TIME);
    }

    private void fullScreenActivity() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
