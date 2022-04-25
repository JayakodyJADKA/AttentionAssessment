package com.anuththara18.attentionassessment.videos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.anuththara18.attentionassessment.R;
import com.anuththara18.attentionassessment.dividedattention.DividedAttentionGame1;
import com.anuththara18.attentionassessment.focused.AnimalChoosingActivity;
import com.anuththara18.attentionassessment.focused.FocusedAttentionGame1;
import com.anuththara18.attentionassessment.home.MainFragment;
import com.anuththara18.attentionassessment.language.LanguageSetter;
import com.anuththara18.attentionassessment.map.Map1Activity;
import com.anuththara18.attentionassessment.map.Map2Activity;
import com.anuththara18.attentionassessment.selective.SelectiveAttentionGame1;
import com.anuththara18.attentionassessment.sustained.BirdChoosingActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class IntroductoryVideoPortraitActivity extends AppCompatActivity {

    String videoStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory_video);

        TextView skip = findViewById(R.id.skip);
        skip.setText(LanguageSetter.getresources().getString(R.string.skip));

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainFragment.game.equals("focused")) {
                    if (Map1Activity.level == 3){
                        startActivity(new Intent(getApplicationContext(), AnimalChoosingActivity.class));
                    }
                    else if (Map1Activity.level == 4){
                        startActivity(new Intent(getApplicationContext(), AnimalChoosingActivity.class));
                    }
                }
                else if (MainFragment.game.equals("divided")) {
                    startActivity(new Intent(getApplicationContext(), DividedAttentionGame1.class));
                }
                else if (MainFragment.game.equals("selective")) {
                    startActivity(new Intent(getApplicationContext(), SelectiveAttentionGame1.class));
                }
                else if (MainFragment.game.equals("sustained")) {
                    startActivity(new Intent(getApplicationContext(), BirdChoosingActivity.class));
                }
            }
        });

        if (MainFragment.game.equals("focused")) {
            videoStr = "<html><body><iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/9n7kxFr-nBA\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
        }
        else if (MainFragment.game.equals("divided")) {
            videoStr = "<html><body><iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/9n7kxFr-nBA\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
        }
        else if (MainFragment.game.equals("selective")) {
            videoStr = "<html><body><iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/9n7kxFr-nBA\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
        }
        else if (MainFragment.game.equals("sustained")) {
            videoStr = "<html><body><iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/9n7kxFr-nBA\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
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