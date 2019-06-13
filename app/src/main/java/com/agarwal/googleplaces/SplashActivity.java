package com.agarwal.googleplaces;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {
    ImageView splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        StartAnimations();

        if (NetworkCheck.isConnected(SplashActivity.this)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    startActivity(new Intent(getApplicationContext(), PermissionActivity.class));
                    finish();
                }
            }, 3000);
        }
    }

    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        splash = findViewById(R.id.app_logo);

        splash.clearAnimation();
        splash.startAnimation(anim);

    }
}
