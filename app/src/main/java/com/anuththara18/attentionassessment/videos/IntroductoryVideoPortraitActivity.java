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
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.anuththara18.attentionassessment.R;
import com.anuththara18.attentionassessment.dividedattention.DividedAttentionGame1;
import com.anuththara18.attentionassessment.focused.AnimalChoosingActivity;
import com.anuththara18.attentionassessment.focused.FocusedAttentionGame1;
import com.anuththara18.attentionassessment.home.MainFragment;
import com.anuththara18.attentionassessment.map.Map1Activity;
import com.anuththara18.attentionassessment.map.Map2Activity;
import com.anuththara18.attentionassessment.selective.SelectiveAttentionGame1;
import com.anuththara18.attentionassessment.sustained.BirdChoosingActivity;

public class IntroductoryVideoPortraitActivity extends AppCompatActivity {

    //String videoUrl;
    String videoUrl = "https://1drv.ms/v/s!AvtsEIqJweWzmFqPURb36VGZZJpp";
    //String videoUrl = "https://drive.google.com/file/d/1Qt8C8Ucf0icgnLYdBSfhThm1nQbatGDa/view?usp=sharing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory_video);

        /*
        if (MainFragment.game.equals("focused")) {
            videoUrl = "https://media.geeksforgeeks.org/wp-content/uploads/20201217192146/Screenrecorder-2020-12-17-19-17-36-828.mp4?_=1";
        }
        else if (MainFragment.game.equals("divided")) {
            videoUrl = "https://1drv.ms/v/s!AvtsEIqJweWzmFqPURb36VGZZJpp";
        }
        else if (MainFragment.game.equals("selective")) {
            videoUrl = "";
        }
        else if (MainFragment.game.equals("sustained")) {
            videoUrl = "";
        }

         */


        // initialising the web view
        WebView wv = (WebView) findViewById(R.id.videoView);

        // add your link here
        wv.loadUrl("https://drive.google.com/file/d/1Qt8C8Ucf0icgnLYdBSfhThm1nQbatGDa/view?usp=sharing");
        //wv.loadUrl("https://1drv.ms/v/s!AvtsEIqJweWzmFqPURb36VGZZJpp");
        wv.setWebViewClient(new Client());
        WebSettings ws = wv.getSettings();

        // Enabling javascript
        ws.setJavaScriptEnabled(true);
        wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wv.clearCache(true);
        wv.clearHistory();

        // download manager is a service that can be used to handle downloads
        wv.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String s1, String s2, String s3, long l) {
                DownloadManager.Request req = new DownloadManager.Request(Uri.parse(url));
                req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(req);
                Toast.makeText(IntroductoryVideoPortraitActivity.this, "Downloading....", Toast.LENGTH_SHORT).show();
            }
        });

         /*

        VideoView videoView = findViewById(R.id.videoView);
        Uri uri = Uri.parse(videoUrl);
        videoView.setVideoURI(uri);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        mediaController.setMediaPlayer(videoView);
        videoView.setMediaController(mediaController);
        videoView.start();

          */

        /*
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //Toast.makeText(getApplicationContext(), "Video completed", Toast.LENGTH_LONG).show();
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
        */

    }

    private class Client extends WebViewClient {
        // on page started load start loading the url
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        // load the url of our drive
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl) {
            // if stop loading
            try {
                webView.stopLoading();
            } catch (Exception e) {
            }

            if (webView.canGoBack()) {
                webView.goBack();
            }

            // if loaded blank then show error
            // to check internet connection using
            // alert dialog
            webView.loadUrl("about:blank");
            AlertDialog alertDialog = new AlertDialog.Builder(IntroductoryVideoPortraitActivity.this).create();
            alertDialog.setTitle("Error");
            alertDialog.setMessage("Check your internet connection and Try again.");
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Try Again", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    startActivity(getIntent());
                }
            });

            alertDialog.show();
            super.onReceivedError(webView, errorCode, description, failingUrl);
        }
    }

}