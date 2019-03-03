package com.moksh.spinthebottle;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView bottleImage;
    private int lastDir;
    private float pivotX;
    private float pivotY;
    private boolean isSpinning = false;
    private Random random = new Random();
    private MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottleImage = findViewById(R.id.bottleImage);
        mediaPlayer = MediaPlayer.create(this, R.raw.bottlesound);



        bottleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinTheBottle();

            }
        });
    }

    private void spinTheBottle(){
        if(!isSpinning) {
            final int newDir = random.nextInt(1800);
            pivotX = bottleImage.getPivotX();
            pivotY = bottleImage.getPivotY();
            Animation rotate = new RotateAnimation(lastDir, newDir, pivotX, pivotY);
            rotate.setDuration(2500);
            rotate.setFillAfter(true);
            rotate.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    isSpinning = true;

                    mediaPlayer.seekTo(random.nextInt(1450));
                    mediaPlayer.start();
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    isSpinning = false;
                    mediaPlayer.pause();

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            lastDir = newDir;
            bottleImage.startAnimation(rotate);
        }
    }
}
