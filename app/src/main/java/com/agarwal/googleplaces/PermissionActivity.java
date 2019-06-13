package com.agarwal.googleplaces;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class PermissionActivity extends AppCompatActivity {
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        checkRunTimePermission();


        findViewById(R.id.permission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestRunTimePermission();
            }
        });
    }

    private void requestRunTimePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }
    }

    private void checkRunTimePermission() {
        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) &&
                (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
            startActivity(new Intent(getApplicationContext(), MapActivity.class));
            finish();
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {

        switch (requestCode) {

            case 101:
                if (grantResults.length > 0)
                    if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                        Toast.makeText(this, "Please Allow all Permissions to Proceed", Toast.LENGTH_SHORT).show();
                        boolean showRationale = shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION);
                        if (!showRationale) {
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                            intent.setData(uri);
                            startActivityForResult(intent, 101);
                        }

                    } else {
                        startActivity(new Intent(getApplicationContext(), MapActivity.class));
                        finish();
//                        Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
                    }
                break;
        }
    }

}
