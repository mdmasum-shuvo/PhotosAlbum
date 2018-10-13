package com.masumdroid.photos.activity;

import android.app.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.masumdroid.photos.R;

import com.masumdroid.photos.preference.AppPreference;
import com.masumdroid.photos.preference.PrefKey;
import com.masumdroid.photos.utility.ActivityUtils;


public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin=findViewById(R.id.btn_login);
        mActivity=LoginActivity.this;
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppPreference.getInstance(mActivity).setBoolean(PrefKey.IS_LOGIN,true);
                ActivityUtils.getInstance().invokeActivity(mActivity,MainActivity.class,true);
            }
        });

    }
}
