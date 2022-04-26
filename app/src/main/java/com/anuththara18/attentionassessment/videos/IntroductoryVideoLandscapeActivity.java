package com.anuththara18.attentionassessment.videos;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.anuththara18.attentionassessment.R;
import com.anuththara18.attentionassessment.alternating.AlternatingAttentionGame1;
import com.anuththara18.attentionassessment.dividedattention.DividedAttentionGame1;
import com.anuththara18.attentionassessment.focused.AnimalChoosingActivity;
import com.anuththara18.attentionassessment.focused.FocusedAttentionGame1;
import com.anuththara18.attentionassessment.focused.FocusedAttentionGame2;
import com.anuththara18.attentionassessment.home.MainFragment;
import com.anuththara18.attentionassessment.language.LanguageSetter;
import com.anuththara18.attentionassessment.map.Map1Activity;
import com.anuththara18.attentionassessment.map.Map2Activity;
import com.anuththara18.attentionassessment.selective.SelectiveAttentionGame1;
import com.anuththara18.attentionassessment.sustained.BirdChoosingActivity;

public class IntroductoryVideoLandscapeActivity extends AppCompatActivity {

    String videoStr;

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
        setContentView(R.layout.activity_introductory_video);

        TextView skip = findViewById(R.id.skip);
        skip.setText(LanguageSetter.getresources().getString(R.string.skip));

        /*
        // finding videoview by its id
        VideoView videoView = findViewById(R.id.videoView);

        if (MainFragment.game.equals("focused")) {
            videoUrl = "";
        }
        else if (MainFragment.game.equals("alternating")) {
            videoUrl = "";
        }

         */

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainFragment.game.equals("focused")) {
                    if (Map1Activity.level == 1){
                        startActivity(new Intent(getApplicationContext(), FocusedAttentionGame2.class));
                    }
                    else if (Map1Activity.level == 2){
                        startActivity(new Intent(getApplicationContext(), FocusedAttentionGame2.class));
                    }
                    else if (Map1Activity.level == 5){
                        startActivity(new Intent(getApplicationContext(), FocusedAttentionGame1.class));
                    }
                }
                else if (MainFragment.game.equals("alternating")) {
                    startActivity(new Intent(getApplicationContext(), AlternatingAttentionGame1.class));
                }
            }
        });

        if (MainFragment.game.equals("focused")) {
            if (Map1Activity.level == 1 || Map1Activity.level == 2) {
                videoStr = "<html><body><iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/_Ob4e8Upofg\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
            }
            else if (Map1Activity.level == 5){
                videoStr = "<html><body><iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/bRPtJDKBPu4\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
            }
        }
        else if (MainFragment.game.equals("alternating")) {
            videoStr = "<html><body><iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/zphdLe78ils\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
        }

        Log.i("*********************************************************Video", "Video Playing....");

        WebView mWebView=(WebView)findViewById(R.id.videoView);

        int height = 2;

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        WebSettings ws = mWebView.getSettings();
        ws.setJavaScriptEnabled(true);
        mWebView.loadData(videoStr, "text/html", "utf-8");

    }

}