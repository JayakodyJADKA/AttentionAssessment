package com.anuththara18.attentionassessment.videos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory_video);

        // finding videoview by its id
        VideoView videoView = findViewById(R.id.videoView);

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

        // Uri object to refer the
        // resource from the videoUrl
        Uri uri = Uri.parse(videoUrl);

        // sets the resource from the
        // videoUrl to the videoView
        videoView.setVideoURI(uri);

        // creating object of
        // media controller class
        MediaController mediaController = new MediaController(this);

        // sets the anchor view
        // anchor view for the videoView
        mediaController.setAnchorView(videoView);

        // sets the media player to the videoView
        mediaController.setMediaPlayer(videoView);

        // sets the media controller to the videoView
        videoView.setMediaController(mediaController);

        // starts the video
        videoView.start();

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


    }

}