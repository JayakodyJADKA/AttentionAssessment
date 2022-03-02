package com.anuththara18.attentionassessment.gender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.anuththara18.attentionassessment.R;
import com.anuththara18.attentionassessment.age.AgeActivity;
import com.anuththara18.attentionassessment.details.ParentDetailsActivity;
import com.anuththara18.attentionassessment.language.LanguageActivity;
import com.anuththara18.attentionassessment.language.LanguageSetter;

public class GenderActivity extends AppCompatActivity {

    ImageButton toggle_btn;
    ImageView girl_img, boy_img;
    TextView boy_txt, girl_txt, next, previous, selectGender;

    public static int gender = 0;

    int click = 1;

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

        toggle_btn = (ImageButton)findViewById(R.id.toggle_btn);
        girl_img = (ImageView)findViewById(R.id.girl_img);
        boy_img = (ImageView)findViewById(R.id.boy_img);
        girl_txt = (TextView)findViewById(R.id.girl_txt);
        boy_txt = (TextView)findViewById(R.id.boy_txt);
        next = (TextView)findViewById(R.id.next);
        previous = (TextView)findViewById(R.id.previous);
        selectGender = (TextView)findViewById(R.id.selectGender);

        girl_txt.setText(LanguageSetter.getresources().getString(R.string.girl));
        boy_txt.setText(LanguageSetter.getresources().getString(R.string.boy));
        next.setText(LanguageSetter.getresources().getString(R.string.next));
        previous.setText(LanguageSetter.getresources().getString(R.string.previous));
        selectGender.setText(LanguageSetter.getresources().getString(R.string.gender));

        // initially
        girl_img.setImageAlpha(127);
        girl_txt.setAlpha(0.5f);
        boy_img.setImageAlpha(255);
        boy_txt.setAlpha(1f);

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
                    gender = 0; // boy
                    Log.d("gender", String.valueOf(gender));
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GenderActivity.this, AgeActivity.class);
                startActivity(intent);
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GenderActivity.this, ParentDetailsActivity.class);
                startActivity(intent);
            }
        });

    }
}