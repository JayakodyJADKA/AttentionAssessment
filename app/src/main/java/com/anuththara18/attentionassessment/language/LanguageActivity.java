package com.anuththara18.attentionassessment.language;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsoluteLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anuththara18.attentionassessment.R;
import com.anuththara18.attentionassessment.details.ParentDetailsActivity;
import com.anuththara18.attentionassessment.gender.GenderActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import pl.droidsonroids.gif.GifImageView;

public class LanguageActivity extends AppCompatActivity {

    ImageView english_btn, sinhala_btn;
    TextView english_txt;
    public static String text = "";
    public static String uid = "";
    public static final String sharedKey = "Language";
    TextView selectLanguage;
    ImageButton next, sinhala_txt_btn;
    public static SharedPreferences sharedPref;
    GifImageView red_circle;

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
        setContentView(R.layout.activity_language);

        english_btn = (ImageView)findViewById(R.id.english_btn);
        sinhala_btn = (ImageView)findViewById(R.id.sinhala_btn);
        sinhala_txt_btn = (ImageButton)findViewById(R.id.sinhala_txt_btn);
        english_txt = (TextView) findViewById(R.id.english_txt);
        selectLanguage = (TextView) findViewById(R.id.selectLanguage);
        next = (ImageButton) findViewById(R.id.next);
        red_circle = findViewById(R.id.red_circle);

        sharedPref = getSharedPreferences(sharedKey, MODE_PRIVATE);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        if ( auth.getCurrentUser() == null ) {
            auth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Log.i("**********************************************test user", String.valueOf(task.isSuccessful()));
                    Log.i("**********************************************test user2", String.valueOf(auth.getCurrentUser().getUid()));
                    uid = auth.getCurrentUser().getUid();
                }
            });
        }

        int bt_notes_blink = 0;

        if (text.equals("English")){
            sinhala_btn.setImageAlpha(127);
            sinhala_txt_btn.setAlpha(0.5f);
            english_btn.setImageAlpha(255);
            english_txt.setAlpha(1f);
        }
        else if (text.equals("සිංහල")){
            english_btn.setImageAlpha(127);
            english_txt.setAlpha(0.5f);
            sinhala_btn.setImageAlpha(255);
            sinhala_txt_btn.setAlpha(1f);
        }

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        /*
                        Animation animation1 =
                                AnimationUtils.loadAnimation(getApplicationContext(),
                                        R.anim.blink);
                        next.startAnimation(animation1);
                        Animation animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in);
                        next.startAnimation(animZoomIn);

                         */
                        Animation animZoomOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out);
                        next.startAnimation(animZoomOut);
                        handler.postDelayed(this, 750);

                    }
                });
            }
        }, 0);

        english_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.button_click);
                mp.start();
                red_circle.setVisibility(View.VISIBLE);
                sinhala_btn.setImageAlpha(127);
                sinhala_txt_btn.setAlpha(0.5f);
                english_btn.setImageAlpha(255);
                english_txt.setAlpha(1f);
                text = "English";
                setLangPref();
                changeLanguage();
            }
        });

        english_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.button_click);
                mp.start();
                red_circle.setVisibility(View.VISIBLE);
                sinhala_btn.setImageAlpha(127);
                sinhala_txt_btn.setAlpha(0.5f);
                english_btn.setImageAlpha(255);
                english_txt.setAlpha(1f);
                text = "English";
                setLangPref();
                changeLanguage();
            }
        });

        sinhala_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.button_click);
                mp.start();
                red_circle.setVisibility(View.VISIBLE);
                english_btn.setImageAlpha(127);
                english_txt.setAlpha(0.5f);
                sinhala_btn.setImageAlpha(255);
                sinhala_txt_btn.setAlpha(1f);
                text = "සිංහල";
                setLangPref();
                changeLanguage();
            }
        });

        sinhala_txt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.button_click);
                mp.start();
                red_circle.setVisibility(View.VISIBLE);
                english_btn.setImageAlpha(127);
                english_txt.setAlpha(0.5f);
                sinhala_btn.setImageAlpha(255);
                sinhala_txt_btn.setAlpha(1f);
                text = "සිංහල";
                setLangPref();
                changeLanguage();
            }
        });

        /*
        toggle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click++;
                mp = MediaPlayer.create(getApplicationContext(), R.raw.button_click);
                mp.start();
                if(click % 2 == 0) {
                    toggle_btn.setBackgroundResource(R.drawable.toggle_right);
                    english_btn.setImageAlpha(127);
                    english_txt.setAlpha(0.5f);
                    sinhala_btn.setImageAlpha(255);
                    sinhala_txt_btn.setAlpha(1f);
                    text = "සිංහල";
                    setLangPref();
                    changeLanguage();
                }
                else {
                    toggle_btn.setBackgroundResource(R.drawable.toggle_left);
                    sinhala_btn.setImageAlpha(127);
                    sinhala_txt_btn.setAlpha(0.5f);
                    english_btn.setImageAlpha(255);
                    english_txt.setAlpha(1f);
                    text = "English";
                    setLangPref();
                    changeLanguage();
                }
            }
        });


        sinhala_btn.getLayoutParams().height = 656;
        sinhala_btn.getLayoutParams().width= 656;
        sinhala_btn.requestLayout();
        sinhala_txt_btn.getLayoutParams().height = 184;
        sinhala_txt_btn.getLayoutParams().width= 394;
        sinhala_txt_btn.requestLayout();
        english_btn.getLayoutParams().height = 656;
        english_btn.getLayoutParams().width= 656;
        english_btn.requestLayout();
        english_txt.setPadding(0, 10, 0,0 );
        english_txt.setTextSize(40);
         */

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( text.isEmpty() ) {
                    Toast.makeText(getApplicationContext(), "Please select a language", Toast.LENGTH_SHORT).show();
                }
                else {
                    //mp.pause();
                    finish();
                    Intent intent = new Intent(LanguageActivity.this, GenderActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void englishSelected() {
        english_btn.getLayoutParams().height = 700;
        english_btn.getLayoutParams().width= 700;
        english_btn.requestLayout();
        sinhala_btn.getLayoutParams().height = 656;
        sinhala_btn.getLayoutParams().width= 656;
        sinhala_btn.requestLayout();
        english_txt.setPadding(0, 0, 0,10 );
        english_txt.setTextSize(43);
        sinhala_txt_btn.getLayoutParams().height = 184;
        sinhala_txt_btn.getLayoutParams().width= 394;
        sinhala_txt_btn.requestLayout();
        text = "English";
        setLangPref();
        changeLanguage();
    }

    public void sinhalaSelected() {
        english_btn.getLayoutParams().height = 656;
        english_btn.getLayoutParams().width= 656;
        english_btn.requestLayout();
        sinhala_btn.getLayoutParams().height = 700;
        sinhala_btn.getLayoutParams().width= 700;
        sinhala_btn.requestLayout();
        english_txt.setPadding(0, 10, 0,0 );
        english_txt.setTextSize(40);
        sinhala_txt_btn.getLayoutParams().height = 200;
        sinhala_txt_btn.getLayoutParams().width= 410;
        sinhala_txt_btn.requestLayout();
        text = "සිංහල";
        setLangPref();
        changeLanguage();
    }

    public void changeLanguage() {
        LanguageSetter.setLanguage(text);
        LanguageSetter.changeLanguage(text, LanguageActivity.this);
        selectLanguage.setText(LanguageSetter.getresources().getString(R.string.language));
    }

    public void setLangPref() {
        SharedPreferences.Editor edit = sharedPref.edit();
        edit.putString("Lang", text);
        edit.apply();
    }
}