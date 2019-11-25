package com.zovvo.tastaz.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

import android.widget.LinearLayout;
import android.widget.VideoView;

import com.zovvo.tastaz.R;


public class SplashActivity extends AppCompatActivity {
    private LinearLayout splash_logo;
    VideoView videoView;
    private static int splashTimeOut=8000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        videoView =(VideoView) findViewById(R.id.videosplash);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splash);
        videoView.setVideoURI(uri);
        videoView.setMediaController(null);
        videoView.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i= new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);
            }
        },splashTimeOut);


    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
