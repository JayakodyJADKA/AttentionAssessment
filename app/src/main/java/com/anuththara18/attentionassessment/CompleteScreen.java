package com.anuththara18.attentionassessment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.anuththara18.attentionassessment.focused.FACompleteScreen;
import com.anuththara18.attentionassessment.home.NavigationDrawerActivity;

import java.util.Random;

public class CompleteScreen extends AppCompatActivity {

    Random random;
    ImageView complete, gifImageView;
    int gifs[] = {R.drawable.complete_gif, R.drawable.complete_gif2, R.drawable.complete_gif3,
            R.drawable.complete_gif4, R.drawable.complete_gif5, R.drawable.complete_gif6,
            R.drawable.complete_gif7, R.drawable.complete_gif8};

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
        setContentView(R.layout.activity_complete_screen);

        complete = findViewById(R.id.complete);
        gifImageView = findViewById(R.id.gifImageView);

        random = new Random();
        gifImageView.setImageResource(gifs[random.nextInt(8)]);

        /*
        new Runnable() {
            @Override
            public void run() {
                gifImageView.postDelayed(this, 3000);
                complete.setVisibility(View.VISIBLE);
            }
        }.run();

         */

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                finish();
                startActivity(intent);
            }
        });

    }
}