package com.anuththara18.attentionassessment.map;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import com.anuththara18.attentionassessment.R;
import com.anuththara18.attentionassessment.focused.AnimalChoosingActivity;
import com.anuththara18.attentionassessment.focused.FocusedAttentionGame1;
import com.anuththara18.attentionassessment.focused.FocusedAttentionGame2;
import com.anuththara18.attentionassessment.selective.SelectiveAttentionGame1;
import com.anuththara18.attentionassessment.videos.IntroductoryVideoLandscapeActivity;
import com.anuththara18.attentionassessment.videos.IntroductoryVideoPortraitActivity;

public class Map1Activity extends AppCompatActivity {

    ImageButton imageButton1, imageButton2, imageButton3, imageButton4, imageButton5;
    ImageButton star1, star2, star3, star4, star5;

    public static int comp1 = 0, comp2 = 0, comp3 = 0, comp4 = 0, comp5 = 0;

    public static int level = 1;

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map1);

        imageButton1 = findViewById(R.id.imageButton1);
        imageButton2 = findViewById(R.id.imageButton2);
        imageButton3 = findViewById(R.id.imageButton3);
        imageButton4 = findViewById(R.id.imageButton4);
        imageButton5 = findViewById(R.id.imageButton5);
        star1 = findViewById(R.id.star1);
        star2 = findViewById(R.id.star2);
        star3 = findViewById(R.id.star3);
        star4 = findViewById(R.id.star4);
        star5 = findViewById(R.id.star5);

        star1.setVisibility(View.INVISIBLE);
        star2.setVisibility(View.INVISIBLE);
        star3.setVisibility(View.INVISIBLE);
        star4.setVisibility(View.INVISIBLE);
        star5.setVisibility(View.INVISIBLE);

        // Retrieving the value using its keys the file name
        // must be same in both saving and retrieving the data
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        // The value will be default as empty string because for
        // the very first time when the app is opened, there is nothing to show
        comp1 = sh.getInt("fal1", 0);
        comp2 = sh.getInt("fal2", 0);
        comp3 = sh.getInt("fal3", 0);
        comp4 = sh.getInt("fal4", 0);
        comp5 = sh.getInt("fal5", 0);
        Log.d("@@@@@@@@@@@@@@@@@@@@@@@@", String.valueOf(comp1));
        Log.d("@@@@@@@@@@@@@@@@@@@@@@@@", String.valueOf(comp2));
        Log.d("@@@@@@@@@@@@@@@@@@@@@@@@", String.valueOf(comp3));
        Log.d("@@@@@@@@@@@@@@@@@@@@@@@@", String.valueOf(comp4));
        Log.d("@@@@@@@@@@@@@@@@@@@@@@@@", String.valueOf(comp5));

        if (comp1 == 1) {
            star1.setVisibility(View.VISIBLE);
            star1.bringToFront();
        }
        if (comp2 == 1){
            star2.setVisibility(View.VISIBLE);
            star2.bringToFront();
        }
        if (comp3 == 1){
            star3.setVisibility(View.VISIBLE);
            star3.bringToFront();
        }
        if (comp4 == 1){
            star4.setVisibility(View.VISIBLE);
            star4.bringToFront();
        }
        if (comp5 == 1){
            star5.setVisibility(View.VISIBLE);
            star5.bringToFront();
        }

        if (comp1 == 0) {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Animation animZoomOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out);
                            imageButton1.startAnimation(animZoomOut);
                            handler.postDelayed(this, 750);
                        }
                    });
                }
            }, 0);
        }
        else if (comp2 == 0){
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Animation animZoomOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out);
                            imageButton2.startAnimation(animZoomOut);
                            handler.postDelayed(this, 750);
                        }
                    });
                }
            }, 0);
        }
        else if (comp3 == 0){
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Animation animZoomOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out);
                            imageButton3.startAnimation(animZoomOut);
                            handler.postDelayed(this, 750);
                        }
                    });
                }
            }, 0);
        }
        else if (comp4 == 0){
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Animation animZoomOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out);
                            imageButton4.startAnimation(animZoomOut);
                            handler.postDelayed(this, 750);
                        }
                    });
                }
            }, 0);
        }
        else if (comp5 == 0){
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Animation animZoomOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out);
                            imageButton5.startAnimation(animZoomOut);
                            handler.postDelayed(this, 750);
                        }
                    });
                }
            }, 0);
        }


        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level = 1;
                //imageButton1.setBackgroundColor(Color.RED);
                mp = MediaPlayer.create(getApplicationContext(), R.raw.button_click);
                mp.start();
                Intent intent = new Intent(getApplicationContext(), IntroductoryVideoLandscapeActivity.class);
                finish();
                startActivity(intent);
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level = 2;
                mp = MediaPlayer.create(getApplicationContext(), R.raw.button_click);
                mp.start();
                Intent intent = new Intent(getApplicationContext(), IntroductoryVideoLandscapeActivity.class);
                finish();
                startActivity(intent);
            }
        });

        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level = 3;
                //imageButton3.setImageAlpha(127);
                mp = MediaPlayer.create(getApplicationContext(), R.raw.button_click);
                mp.start();
                Intent intent = new Intent(getApplicationContext(), IntroductoryVideoPortraitActivity.class);
                finish();
                startActivity(intent);
            }
        });

        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level = 4;
                mp = MediaPlayer.create(getApplicationContext(), R.raw.button_click);
                mp.start();
                Intent intent = new Intent(getApplicationContext(), IntroductoryVideoPortraitActivity.class);
                finish();
                startActivity(intent);
            }
        });

        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level = 5;
                mp = MediaPlayer.create(getApplicationContext(), R.raw.button_click);
                mp.start();
                Intent intent = new Intent(getApplicationContext(), IntroductoryVideoLandscapeActivity.class);
                finish();
                startActivity(intent);
            }
        });

    }
}