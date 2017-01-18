package com.example.andrej.homequest_2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

public class ActivitySplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        VideoView videoView = null;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        try{
            videoView = (VideoView) findViewById(R.id.videoView);
            Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.home_quest_animation);
            videoView.setVideoURI(video);

            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                public void onCompletion(MediaPlayer mp) {
                    jump();
                }
            });
            videoView.start();
        } catch(Exception ex)
        {   videoView.stopPlayback();
            this.finish();
            jump();
        }
    }
    private void jump() {
        if(isFinishing())
            return;
        finish();
        startActivity(new Intent(this, LoginActivity.class));

    }
}
