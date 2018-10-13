package com.masumdroid.photos.utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.TypedValue;

import com.masumdroid.photos.activity.LoginActivity;
import com.masumdroid.photos.activity.MainActivity;

public class AppUtils {

    public static int dpToPx(int dp,Context mContext) {
        Resources r = mContext.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


}
