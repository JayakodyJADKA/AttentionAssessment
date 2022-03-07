package com.anuththara18.attentionassessment.language;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.anuththara18.attentionassessment.R;
import com.anuththara18.attentionassessment.details.ParentDetailsActivity;
import com.anuththara18.attentionassessment.gender.GenderActivity;

public class LanguageActivity extends AppCompatActivity {

    ImageButton english_btn, sinhala_btn, sinhala_txt_btn;
    TextView english_txt;
    public static String text = "English";
    TextView selectLanguage, next;

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

        english_btn = (ImageButton)findViewById(R.id.english_btn);
        sinhala_btn = (ImageButton)findViewById(R.id.sinhala_btn);
        sinhala_txt_btn = (ImageButton)findViewById(R.id.sinhala_txt_btn);
        english_txt = (TextView) findViewById(R.id.english_txt);
        selectLanguage = (TextView) findViewById(R.id.selectLanguage);
        next = (TextView) findViewById(R.id.next);

        english_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                englishSelected();
            }
        });

        english_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                englishSelected();
            }
        });

        sinhala_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sinhalaSelected();
            }
        });

        sinhala_txt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sinhalaSelected();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( text == null ) {
                    Toast.makeText(getApplicationContext(), "Please select a language", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(LanguageActivity.this, ParentDetailsActivity.class);
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
        changeLanguage();
    }

    public void changeLanguage() {
        LanguageSetter.setLanguage(text);
        LanguageSetter.changeLanguage(text, LanguageActivity.this);
        next.setText(LanguageSetter.getresources().getString(R.string.next));
        selectLanguage.setText(LanguageSetter.getresources().getString(R.string.language));

    }

}