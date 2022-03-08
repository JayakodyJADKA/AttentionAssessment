package com.anuththara18.attentionassessment.splashscreen;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.anuththara18.attentionassessment.R;
import com.anuththara18.attentionassessment.details.ParentDetailsActivity;
import com.anuththara18.attentionassessment.language.LanguageActivity;
import com.anuththara18.attentionassessment.language.LanguageSetter;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("Language", MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();
        String lang = sharedPreferences.getString("Lang", "");
        //int check = sharedPreferences.getInt("ImageCheck", 5);

        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        //Toast.makeText(getApplicationContext(), String.valueOf(check), Toast.LENGTH_SHORT).show();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(lang.isEmpty()){
                    Intent langAct = new Intent(SplashActivity.this, LanguageActivity.class);
                    startActivity(langAct);
                }
                else{
                    Intent parentAct = new Intent(SplashActivity.this, ParentDetailsActivity.class);
                    startActivity(parentAct);
                    LanguageSetter.setLanguage(lang);
                    LanguageSetter.changeLanguage(lang, SplashActivity.this);
                }
                finish();
            }
        },2000);
    }
}