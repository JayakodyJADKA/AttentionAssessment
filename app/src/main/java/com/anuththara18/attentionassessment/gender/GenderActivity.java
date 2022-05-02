package com.anuththara18.attentionassessment.gender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anuththara18.attentionassessment.R;
import com.anuththara18.attentionassessment.age.AgeActivity;
import com.anuththara18.attentionassessment.details.ParentDetailsActivity;
import com.anuththara18.attentionassessment.language.LanguageActivity;
import com.anuththara18.attentionassessment.language.LanguageSetter;

public class GenderActivity extends AppCompatActivity {

    ImageButton toggle_btn, next, previous;
    ImageView girl_img, boy_img;
    TextView boy_txt, girl_txt, selectGender;

    public static int gender = 0;

    int click = 1;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_gender);

        //toggle_btn = (ImageButton)findViewById(R.id.toggle_btn);
        girl_img = (ImageView)findViewById(R.id.girl_img);
        boy_img = (ImageView)findViewById(R.id.boy_img);
        girl_txt = (TextView)findViewById(R.id.girl_txt);
        boy_txt = (TextView)findViewById(R.id.boy_txt);
        next = (ImageButton)findViewById(R.id.next);
        previous = (ImageButton)findViewById(R.id.previous);
        selectGender = (TextView)findViewById(R.id.selectGender);

        girl_txt.setText(LanguageSetter.getresources().getString(R.string.girl));
        boy_txt.setText(LanguageSetter.getresources().getString(R.string.boy));
        selectGender.setText(LanguageSetter.getresources().getString(R.string.gender));

        if (gender == 1) {
            boy_img.setImageAlpha(127);
            boy_txt.setAlpha(0.5f);
            girl_img.setImageAlpha(255);
            girl_txt.setAlpha(1f);
        }
        else if (gender == 2) {
            girl_img.setImageAlpha(127);
            girl_txt.setAlpha(0.5f);
            boy_img.setImageAlpha(255);
            boy_txt.setAlpha(1f);
        }

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Animation animZoomOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out);
                        next.startAnimation(animZoomOut);
                        handler.postDelayed(this, 750);

                    }
                });
            }
        }, 0);

        girl_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.button_click);
                mp.start();
                boy_img.setImageAlpha(127);
                boy_txt.setAlpha(0.5f);
                girl_img.setImageAlpha(255);
                girl_txt.setAlpha(1f);
                gender = 1; // girl
                Log.d("gender", String.valueOf(gender));
            }
        });

        girl_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.button_click);
                mp.start();
                boy_img.setImageAlpha(127);
                boy_txt.setAlpha(0.5f);
                girl_img.setImageAlpha(255);
                girl_txt.setAlpha(1f);
                gender = 1; // girl
                Log.d("gender", String.valueOf(gender));
            }
        });

        boy_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.button_click);
                mp.start();
                girl_img.setImageAlpha(127);
                girl_txt.setAlpha(0.5f);
                boy_img.setImageAlpha(255);
                boy_txt.setAlpha(1f);
                gender = 2; // boy
                Log.d("gender", String.valueOf(gender));
            }
        });

        boy_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.button_click);
                mp.start();
                girl_img.setImageAlpha(127);
                girl_txt.setAlpha(0.5f);
                boy_img.setImageAlpha(255);
                boy_txt.setAlpha(1f);
                gender = 2; // boy
                Log.d("gender", String.valueOf(gender));
            }
        });

        /*
        toggle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click++;
                if(click % 2 == 0) {
                    toggle_btn.setBackgroundResource(R.drawable.toggle_right);
                    //deg = toggle_btn.getRotation() + 180F;
                    //toggle_btn.animate().rotation(deg).setInterpolator(new AccelerateDecelerateInterpolator());
                    boy_img.setImageAlpha(127);
                    boy_txt.setAlpha(0.5f);
                    girl_img.setImageAlpha(255);
                    girl_txt.setAlpha(1f);
                    gender = 1; // girl
                    Log.d("gender", String.valueOf(gender));
                }
                else {
                    toggle_btn.setBackgroundResource(R.drawable.toggle_left);
                    //deg = toggle_btn.getRotation() + 180F;
                    //toggle_btn.animate().rotation(deg).setInterpolator(new AccelerateDecelerateInterpolator());
                    girl_img.setImageAlpha(127);
                    girl_txt.setAlpha(0.5f);
                    boy_img.setImageAlpha(255);
                    boy_txt.setAlpha(1f);
                    gender = 2; // boy
                    Log.d("gender", String.valueOf(gender));
                }
            }
        });
         */

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (gender == 0) {
                    Toast.makeText(getApplicationContext(), LanguageSetter.getresources().getString(R.string.gendernull), Toast.LENGTH_SHORT).show();
                }
                else{
                    finish();
                    Intent intent = new Intent(GenderActivity.this, AgeActivity.class);
                    startActivity(intent);
                }

            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GenderActivity.this, LanguageActivity.class);
                startActivity(intent);
            }
        });

    }
}