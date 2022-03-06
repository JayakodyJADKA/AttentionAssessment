package com.anuththara18.attentionassessment.age;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anuththara18.attentionassessment.R;
import com.anuththara18.attentionassessment.consentform.ConsentFormActivity;
import com.anuththara18.attentionassessment.gender.GenderActivity;
import com.anuththara18.attentionassessment.home.NavigationDrawerActivity;
import com.anuththara18.attentionassessment.language.LanguageActivity;
import com.anuththara18.attentionassessment.language.LanguageSetter;
import com.anuththara18.attentionassessment.selective.SelectiveAttentionGame1;

public class AgeActivity extends AppCompatActivity {

    ImageButton right_btn, left_btn;
    ImageView bunny_img;
    TextView age_txt, next, previous, selectAge;
    public static int age = 4;

    int width;
    int count;
    float rightFromXDelta, rightToXDelta, leftFromXDelta, leftToXDelta;

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
        setContentView(R.layout.activity_age);

        right_btn = (ImageButton)findViewById(R.id.right_btn);
        left_btn = (ImageButton)findViewById(R.id.left_btn);
        bunny_img = (ImageView)findViewById(R.id.bunny_img);
        age_txt = (TextView)findViewById(R.id.age_txt);
        next = (TextView)findViewById(R.id.next);
        previous = (TextView)findViewById(R.id.previous);
        selectAge = (TextView)findViewById(R.id.selectAge);

        next.setText(LanguageSetter.getresources().getString(R.string.next));
        previous.setText(LanguageSetter.getresources().getString(R.string.previous));
        selectAge.setText(LanguageSetter.getresources().getString(R.string.age));

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

        count = 1;

        right_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( count < 4 ) {
                    getDimensions();
                    TranslateAnimation animate = new TranslateAnimation(rightFromXDelta, rightToXDelta, 0, 0);
                    animate.setDuration(500);
                    animate.setFillAfter(true);
                    bunny_img.startAnimation(animate);
                    age_txt.startAnimation(animate);
                    count++;
                    Log.d("tag", String.valueOf(count));
                    setAge();
                }
            }
        });

        left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( count > 1 ) {
                    getDimensions();
                    TranslateAnimation animate = new TranslateAnimation(leftFromXDelta, leftToXDelta, 0, 0);
                    animate.setDuration(500);
                    animate.setFillAfter(true);
                    bunny_img.startAnimation(animate);
                    age_txt.startAnimation(animate);
                    count--;
                    Log.d("tag", String.valueOf(count));
                    setAge();
                }
            }

        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AgeActivity.this, ConsentFormActivity.class);
                startActivity(intent);
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AgeActivity.this, GenderActivity.class);
                startActivity(intent);
            }
        });

    }

    public void getDimensions() {
        if ( count == 1 ) {
            leftFromXDelta = 0;
            leftToXDelta = 0;
            rightFromXDelta = 0;
            rightToXDelta = width / 5.5f;
        }
        else if ( count == 2 ) {
            rightToXDelta = 2 * (width / 5.5f);
            rightFromXDelta = 1 * (width / 5.5f);
            leftFromXDelta = 1 * (width / 5.5f);
            leftToXDelta = 0;
        }
        else if ( count == 3 ) {
            rightToXDelta = 3 * (width / 5.5f);
            rightFromXDelta = 2 * (width / 5.5f);
            leftFromXDelta = 2 * (width / 5.5f);
            leftToXDelta = 1 * (width / 5.5f);
        }
        else if ( count == 4 ) {
            leftFromXDelta = 3 * (width / 5.5f);
            leftToXDelta = 2 * (width / 5.5f);
        }
    }

    public void setAge() {
        if ( count == 1 ) {
            age = 4;
            age_txt.setText("4");
            age_txt.setPadding(0, 0, 0,0 );
            Log.d("age", String.valueOf(age));
        }
        else if ( count == 2 ) {
            age = 5;
            age_txt.setText("5");
            age_txt.setPadding(10, 0, 0,0 );
            Log.d("age", String.valueOf(age));
        }
        else if ( count == 3 ) {
            age = 6;
            age_txt.setText("6");
            age_txt.setPadding(15, 0, 0,0 );
            Log.d("age", String.valueOf(age));
        }
        else if ( count == 4 ) {
            age = 7;
            age_txt.setText("7");
            age_txt.setPadding(25, 0, 0,0 );
            Log.d("age", String.valueOf(age));
        }
    }
}