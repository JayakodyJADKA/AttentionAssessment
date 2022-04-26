package com.anuththara18.attentionassessment.map;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

    public static int comp1, comp2, comp3, comp4, comp5 = 0;

    public static int level = 1;

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

        if (comp1 == 1) {
            star1.setVisibility(View.VISIBLE);
        }
        if (comp2 == 1){
            star2.setVisibility(View.VISIBLE);
        }
        if (comp3 == 1){
            star3.setVisibility(View.VISIBLE);
        }
        if (comp4 == 1){
            star4.setVisibility(View.VISIBLE);
        }
        if (comp5 == 1){
            star5.setVisibility(View.VISIBLE);
        }

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level = 1;
                Intent intent = new Intent(getApplicationContext(), IntroductoryVideoLandscapeActivity.class);
                finish();
                startActivity(intent);
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level = 2;
                Intent intent = new Intent(getApplicationContext(), IntroductoryVideoLandscapeActivity.class);
                finish();
                startActivity(intent);
            }
        });

        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level = 3;
                Intent intent = new Intent(getApplicationContext(), IntroductoryVideoPortraitActivity.class);
                finish();
                startActivity(intent);
            }
        });

        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level = 4;
                Intent intent = new Intent(getApplicationContext(), IntroductoryVideoPortraitActivity.class);
                finish();
                startActivity(intent);
            }
        });

        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level = 5;
                Intent intent = new Intent(getApplicationContext(), IntroductoryVideoLandscapeActivity.class);
                finish();
                startActivity(intent);
            }
        });

    }
}