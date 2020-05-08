package com.hym.errands.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Permission {

    private String permission = Manifest.permission.ACCESS_COARSE_LOCATION;

    private String[] permissionArr = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    public void checkPermission(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            int check = ContextCompat.checkSelfPermission(activity,permission);
            if (check != PackageManager.PERMISSION_GRANTED) {
                requestPermission(activity);
            }
        }
    }

    private void requestPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity,permissionArr,1000);
    }
}
