package com.anuththara18.attentionassessment.sustained;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anuththara18.attentionassessment.R;
import com.anuththara18.attentionassessment.language.LanguageSetter;

public class BirdChoosingActivity extends AppCompatActivity {

    TextView textView8;
    CardView bird1, bird2, bird3, bird4;
    ImageButton imageButton;
    LinearLayout l1, l2, l3, l4;

    public static Integer birdSelected = 0;

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
        setContentView(R.layout.activity_bird_choosing);

        textView8 = findViewById(R.id.textview8);
        bird1 = findViewById(R.id.bird1);
        bird2 = findViewById(R.id.bird2);
        bird3 = findViewById(R.id.bird3);
        bird4 = findViewById(R.id.bird4);
        imageButton = findViewById(R.id.imageButton);
        l1 = findViewById(R.id.l1);
        l2 = findViewById(R.id.l2);
        l3 = findViewById(R.id.l3);
        l4 = findViewById(R.id.l4);

        //textView8.setText(LanguageSetter.getresources().getString(R.string.birdselect));

        bird1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                birdSelected = 1;
                l1.setBackgroundResource(R.drawable.selected_gridview_background);
                l2.setBackgroundResource(R.drawable.gridview_background);
                l3.setBackgroundResource(R.drawable.gridview_background);
                l4.setBackgroundResource(R.drawable.gridview_background);
            }
        });

        bird2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                birdSelected = 2;
                l2.setBackgroundResource(R.drawable.selected_gridview_background);
                l1.setBackgroundResource(R.drawable.gridview_background);
                l3.setBackgroundResource(R.drawable.gridview_background);
                l4.setBackgroundResource(R.drawable.gridview_background);
                //Toast.makeText(getApplicationContext(), "yellow", Toast.LENGTH_SHORT).show();
            }
        });

        bird3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                birdSelected = 3;
                l3.setBackgroundResource(R.drawable.selected_gridview_background);
                l2.setBackgroundResource(R.drawable.gridview_background);
                l1.setBackgroundResource(R.drawable.gridview_background);
                l4.setBackgroundResource(R.drawable.gridview_background);
            }
        });

        bird4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                birdSelected = 4;
                l2.setBackgroundResource(R.drawable.gridview_background);
                l3.setBackgroundResource(R.drawable.gridview_background);
                l1.setBackgroundResource(R.drawable.gridview_background);
                l4.setBackgroundResource(R.drawable.selected_gridview_background);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(birdSelected == 0) {
                    Toast.makeText(getApplicationContext(), LanguageSetter.getresources().getString(R.string.birdselect), Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(BirdChoosingActivity.this, SustainedAttentionGame1.class);
                    startActivity(intent);
                }
            }
        });

    }
}