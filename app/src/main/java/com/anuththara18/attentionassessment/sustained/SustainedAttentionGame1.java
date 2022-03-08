package com.anuththara18.attentionassessment.sustained;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anuththara18.attentionassessment.R;
import com.anuththara18.attentionassessment.age.AgeActivity;
import com.anuththara18.attentionassessment.db.Api;
import com.anuththara18.attentionassessment.db.RequestHandler;
import com.anuththara18.attentionassessment.gender.GenderActivity;
import com.anuththara18.attentionassessment.home.NavigationDrawerActivity;
import com.anuththara18.attentionassessment.language.LanguageSetter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Random;

public class SustainedAttentionGame1 extends AppCompatActivity {

    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    public static final String DATABASE_NAME = "sustainedAttention";
    SQLiteDatabase mDatabase;

    ImageButton red_btn;
    ImageView gifImageView, cross_btn, bird;
    TextView textView;

    // isi = 10 - 60 secs

    Integer[] isi = { 10000, 45000, 25000, 50000, 20000, 35000, 60000, 15000, 30000, 55000, 40000,
                      10000, 45000, 25000, 50000, 20000, 35000, 60000, 15000, 30000, 55000, 40000,
                      10000, 15000, 10000, 15000 };
    /*Integer[] isi = { 1000, 4500, 2500, 5000, 2000, 3500, 6000, 1500, 3000, 5500, 4000,
            1000, 4500, 2500, 5000, 2000, 3500, 6000, 1500, 3000, 5500, 4000,
            1000, 1500, 1000, 1500 }; */

    Integer[] image = { R.drawable.red_bird_gif,
            R.drawable.blue_bird_gif,
            R.drawable.green_bird_gif,
            R.drawable.black_bird_gif,
            R.drawable.blue_bird2_gif,
            R.drawable.yellow_bird_gif};

    int correctImage, clickedImage;

    Random random = new Random();
    int randomImage = 0;
    int i = 1;
    int j = 0;
    int duration = 0;
    private long startTime, clickedTime = 0;
    float reactionTime;

    int totalCorrectResponses = 0;
    int noOfCorrectResponses = 0;
    int noOfCommissionErrors = 0;
    int noOfOmmissionErrors = 0;
    long totalReactionTime = 0;
    int meanReactionTime = 0;

    MediaPlayer mp, mp2;

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
        setContentView(R.layout.activity_sustained_attention_game1);

        gifImageView = findViewById(R.id.gifImageView);
        red_btn = (ImageButton) findViewById(R.id.red_btn);
        cross_btn = (ImageView) findViewById(R.id.cross_btn);
        bird = (ImageView) findViewById(R.id.bird);
        textView = (TextView) findViewById(R.id.textView);
        textView = (TextView) findViewById(R.id.textView);

        textView.setText(LanguageSetter.getresources().getString(R.string.susg1));

        mp = MediaPlayer.create(getApplicationContext(), R.raw.sustained);
        mp.start();

        long gameStart = System.currentTimeMillis();

        //creating a database
        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        //Toast.makeText(getApplicationContext(), BirdChoosingActivity.birdSelected.toString(), Toast.LENGTH_SHORT).show();
        Log.d("******************************", String.valueOf(BirdChoosingActivity.birdSelected));

        new Runnable() {
            int updateInterval;

            @Override
            public void run() {

                if ( i <= 51 ) { // tot duration - 14.5 mins = 870000
                    //385000 , 770000, (50000)

                    if ( i % 2 != 0 || totalCorrectResponses > 25 ) {
                        randomImage = random.nextInt(6);
                        bird.setVisibility(View.INVISIBLE);
                        gifImageView.setVisibility(View.VISIBLE);
                        red_btn.setEnabled(true);
                        gifImageView.setImageResource(image[randomImage]);
                        clickedImage = image[randomImage];
                        updateInterval = isi[j];
                        Log.d("radomTimer", String.valueOf(i + " " + updateInterval));
                        gifImageView.postDelayed(this, updateInterval);
                        duration = duration + updateInterval;
                        i++;
                        j++;
                    }
                    // appearance - 0.5s * 25 times
                    else if ( totalCorrectResponses <= 25 ) {
                        Log.d("radomTimer", String.valueOf(i + " 3000" ));
                        Log.d("******************************", "start 1");

                        if (BirdChoosingActivity.birdSelected == 1) {
                            bird.setImageResource(R.drawable.blue_bird);
                            clickedImage = R.drawable.blue_bird;
                        }
                        else if (BirdChoosingActivity.birdSelected == 2) {
                            Log.d("******************************", "start 2");
                            bird.setImageResource(R.drawable.yellow_bird);
                            clickedImage = R.drawable.yellow_bird;
                        }
                        else if (BirdChoosingActivity.birdSelected == 3) {
                            bird.setImageResource(R.drawable.red_angry_bird);
                            clickedImage = R.drawable.red_angry_bird;
                        }
                        else if (BirdChoosingActivity.birdSelected == 4) {
                            bird.setImageResource(R.drawable.woody_bird);
                            clickedImage = R.drawable.woody_bird;
                        }

                        bird.setVisibility(View.VISIBLE);
                        red_btn.setEnabled(true);
                        gifImageView.setVisibility(View.INVISIBLE);
                        startTime = System.currentTimeMillis();
                        gifImageView.postDelayed(this, 3000);
                        totalCorrectResponses++;
                        duration = duration + 3000;
                        i++;
                    }

                }
                else {
                    long gameEnd = System.currentTimeMillis();
                    long seconds = (gameEnd - gameStart) / 1000;
                    meanReactionTime = (int) Math.ceil(totalReactionTime / noOfCorrectResponses); // ms
                    Log.d("****************************************************", "****************************************************");
                    Log.d("Game Time", String.valueOf(seconds));
                    Log.d("total", String.valueOf(totalCorrectResponses));
                    Log.d("correctResponses", String.valueOf(noOfCorrectResponses));
                    noOfOmmissionErrors = totalCorrectResponses - noOfCorrectResponses;
                    Log.d("omissionErrors", String.valueOf(totalCorrectResponses - noOfCorrectResponses));
                    Log.d("commissionErrors", String.valueOf(noOfCommissionErrors));
                    Log.d("meanReactionTime", String.valueOf(meanReactionTime));
                    Log.d("duration", String.valueOf(duration));
                    saveDataToOnlineDB();
                    createTable();
                    saveDataToLocalDB();
                    Intent intent = new Intent(getApplicationContext(), SA1CompleteScreen.class);
                    finish();
                    mp.pause();
                    startActivity(intent);
                }

            }
        }.run();

        if (BirdChoosingActivity.birdSelected == 1) {
            correctImage = R.drawable.blue_bird;
        }
        else if (BirdChoosingActivity.birdSelected == 2) {
            correctImage = R.drawable.yellow_bird;
        }
        else if (BirdChoosingActivity.birdSelected == 3) {
            correctImage = R.drawable.red_angry_bird;
        }
        else if (BirdChoosingActivity.birdSelected == 4) {
            correctImage = R.drawable.woody_bird;
        }

        red_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedTime = System.currentTimeMillis();
                reactionTime = ( clickedTime - startTime );
                mp2 = MediaPlayer.create(getApplicationContext(), R.raw.button_click);
                mp2.start();
                if (correctImage == clickedImage) {
                    totalReactionTime = (long) (totalReactionTime + reactionTime);
                    Log.d("correct " , startTime + " " + clickedTime + " " + reactionTime);
                    noOfCorrectResponses++;
                    red_btn.setEnabled(false);
                }
                else {
                    noOfCommissionErrors++;
                    Log.d( "wrong" , startTime + " " + clickedTime + " " + reactionTime);
                }
            }
        });

        cross_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        cross_btn.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        alert();
                        mp.pause();
                    case MotionEvent.ACTION_CANCEL: {
                        cross_btn.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

    }

    /*************************************************************************************************/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mp.pause();
    }

    /*************************************************************************************************/

    private void alert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Do you really want to quit the game?");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                        finish();
                        startActivity(intent);
                        //Toast.makeText(FocusedAttentionGame1.this,"You clicked yes button",Toast.LENGTH_LONG).show();
                    }
                });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    /*************************************************************************************************/

    private void saveDataToOnlineDB() {

        String child = String.valueOf(GenderActivity.gender) + String.valueOf(AgeActivity.age);
        int child_ID = Integer.parseInt(child);
        int total_correct_responses = totalCorrectResponses;
        int no_of_correct_responses = noOfCorrectResponses;
        int no_of_ommission_errors = noOfOmmissionErrors;
        int no_of_commission_errors = noOfCommissionErrors;
        int mean_reaction_time = (int) meanReactionTime;
        int total_duration = duration;

        HashMap<String, Integer> params = new HashMap<>();
        params.put("childID", child_ID);
        params.put("totalCorrectResponses", total_correct_responses);
        params.put("noOfCorrectResponses", no_of_correct_responses);
        params.put("noOfCommissionErrors", no_of_commission_errors);
        params.put("noOfOmmissionErrors", no_of_ommission_errors);
        params.put("meanReactionTime", mean_reaction_time);
        params.put("totalDuration", total_duration);

        //Calling the create hero API
        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_SUSTAINED_ATTENTION, params, CODE_POST_REQUEST);
        request.execute();
    }

    /*************************************************************************************************/

    //inner class to perform network request extending an AsyncTask
    private class PerformNetworkRequest extends AsyncTask<Void, Void, String> {

        //the url where we need to send the request
        String url;

        //the parameters
        HashMap<String, Integer> params;

        //the request code to define whether it is a GET or POST
        int requestCode;

        //constructor to initialize values
        PerformNetworkRequest(String url, HashMap<String, Integer> params, int requestCode) {
            this.url = url;
            this.params = params;
            this.requestCode = requestCode;
        }

        //when the task started displaying a progressbar
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        //this method will give the response from the request
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject object = new JSONObject(s);
                if (!object.getBoolean("error")) {
                    Toast.makeText(getApplicationContext(), object.getString("message"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //the network operation will be performed in background
        @Override
        protected String doInBackground(Void... voids) {
            RequestHandler requestHandler = new RequestHandler();

            if (requestCode == CODE_POST_REQUEST)
                return requestHandler.sendPostRequest(url, params);


            if (requestCode == CODE_GET_REQUEST)
                return requestHandler.sendGetRequest(url);

            return null;
        }
    }

    /*************************************************************************************************/

    private void createTable() {
        mDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS sustainedAttention (\n" +
                        "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                        "    childID int NOT NULL,\n" +
                        "    totalCorrectResponses int NOT NULL,\n" +
                        "    noOfCorrectResponses int NOT NULL,\n" +
                        "    noOfCommissionErrors int NOT NULL,\n" +
                        "    noOfOmmissionErrors int NOT NULL,\n" +
                        "    meanReactionTime int NOT NULL,\n" +
                        "    totalDuration int NOT NULL\n" +
                        ");"
        );
    }

    /*************************************************************************************************/

    private void saveDataToLocalDB() {

        String child = String.valueOf(GenderActivity.gender) + String.valueOf(AgeActivity.age);
        int child_ID = Integer.parseInt(child);
        int total_correct_responses = totalCorrectResponses;
        int no_of_correct_responses = noOfCorrectResponses;
        int no_of_ommission_errors = noOfOmmissionErrors;
        int no_of_commission_errors = noOfCommissionErrors;
        int mean_reaction_time = (int) meanReactionTime;
        int total_duration = duration;

        String insertSQL = "INSERT INTO sustainedAttention \n" +
                "(childID, totalCorrectResponses, noOfCorrectResponses, noOfCommissionErrors, noOfOmmissionErrors, meanReactionTime, totalDuration)\n" +
                "VALUES \n" +
                "(?, ?, ?, ?, ?, ?, ?);";

        mDatabase.execSQL(insertSQL, new Integer[]{child_ID, total_correct_responses, no_of_correct_responses, no_of_ommission_errors, no_of_commission_errors, mean_reaction_time, total_duration});

        Toast.makeText(this, "Data Added Successfully", Toast.LENGTH_SHORT).show();

    }

    /*************************************************************************************************/

}