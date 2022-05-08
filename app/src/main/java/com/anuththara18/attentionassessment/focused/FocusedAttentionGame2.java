package com.anuththara18.attentionassessment.focused;

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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anuththara18.attentionassessment.R;
import com.anuththara18.attentionassessment.age.AgeActivity;
import com.anuththara18.attentionassessment.db.Api;
import com.anuththara18.attentionassessment.db.RequestHandler;
import com.anuththara18.attentionassessment.details.ParentDetailsActivity;
import com.anuththara18.attentionassessment.gender.GenderActivity;
import com.anuththara18.attentionassessment.home.NavigationDrawerActivity;
import com.anuththara18.attentionassessment.language.LanguageSetter;
import com.anuththara18.attentionassessment.map.Map1Activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static com.anuththara18.attentionassessment.consentform.GetSignatureActivity.myEdit;
import static com.anuththara18.attentionassessment.map.Map1Activity.comp1;
import static com.anuththara18.attentionassessment.map.Map1Activity.comp2;

public class FocusedAttentionGame2 extends AppCompatActivity {

    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    public static final String DATABASE_NAME = "focusedAttention";
    SQLiteDatabase mDatabase;

    Random random = new Random();
    int radomTimer = 0;
    int rand = 0;
    int missed = 0;

    int i = 1;
    private long startTime, clickedTime = 0;
    long reactionTime;
    int count;

    int totalCorrectResponses = 0;
    int noOfCorrectResponses = 0;
    int noOfCommissionErrors = 0;
    int noOfOmmissionErrors = 0;
    long totalReactionTime = 0;
    int meanReactionTime = 0;
    int duration = 0;

    ImageView bear1, bear2, bear3, bear4, cross_btn;
    TextView textView;

    String clicked = "null";

    public static ArrayList<String> sequence_of_responses;

    Integer[] isi = { 3000, 3500, 4000, 4500, 5000, 5500, 6000 };

    MediaPlayer mp, mp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        else {
            View decorView = getWindow().getDecorView();
            // Hide the status bar.
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
        setContentView(R.layout.activity_focused_attention_game2);

        bear1 = (ImageView) findViewById(R.id.bear1);
        bear2 = (ImageView) findViewById(R.id.bear2);
        bear3 = (ImageView) findViewById(R.id.bear3);
        bear4 = (ImageView) findViewById(R.id.bear4);
        cross_btn = (ImageView) findViewById(R.id.cross_btn);
        textView = (TextView) findViewById(R.id.textView);

        textView.setText(LanguageSetter.getresources().getString(R.string.focg2));

        mp = MediaPlayer.create(getApplicationContext(), R.raw.focused);
        mp.start();

        sequence_of_responses = new ArrayList<>();

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.d("#############", "restart");
                mp.start();
            }

        });

        long gameStart = System.currentTimeMillis();

        //creating a database
        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);

        getCount();

        new Runnable() {
            int updateInterval;

            @Override
            public void run() {

                if ( i <= count ) { // 41 times

                    if ( i % 2 != 0 ) {
                        bear1.setVisibility(View.INVISIBLE);
                        bear2.setVisibility(View.INVISIBLE);
                        bear3.setVisibility(View.INVISIBLE);
                        bear4.setVisibility(View.INVISIBLE);
                        clicked = "null";

                        bear1.setEnabled(true);
                        bear2.setEnabled(true);
                        bear3.setEnabled(true);
                        bear4.setEnabled(true);

                        if (missed == 0) {
                            sequence_of_responses.add("M");
                            Log.d("%%%%%%%%%%%%%%%%%%", String.valueOf(sequence_of_responses));
                        }

                        radomTimer = random.nextInt(5);
                        updateInterval = isi[radomTimer];
                        bear1.postDelayed(this, updateInterval);
                        Log.d("***************radomTimer******************", String.valueOf(i + " " + updateInterval));
                        duration = duration + updateInterval;
                        i++;
                    }
                    // appearance - 1s * 20 times
                    else {

                        if ( Map1Activity.level == 1 || Map1Activity.level == 2 ) {

                            if ( i == 2 || i == 14 || i == 26 || i == 38 ) {
                                bear1.setVisibility(View.VISIBLE);
                                bear2.setVisibility(View.INVISIBLE);
                                bear3.setVisibility(View.INVISIBLE);
                                bear4.setVisibility(View.INVISIBLE);
                            }
                            else if ( i == 4 || i == 16 || i == 28 || i == 40 ) {
                                bear3.setVisibility(View.VISIBLE);
                                bear1.setVisibility(View.INVISIBLE);
                                bear2.setVisibility(View.INVISIBLE);
                                bear4.setVisibility(View.INVISIBLE);
                            }
                            else if ( i == 6 || i == 18 || i == 30 ) {
                                bear2.setVisibility(View.VISIBLE);
                                bear3.setVisibility(View.INVISIBLE);
                                bear1.setVisibility(View.INVISIBLE);
                                bear4.setVisibility(View.INVISIBLE);
                            }
                            else if ( i == 8 || i == 20 || i == 32 ) {
                                bear4.setVisibility(View.VISIBLE);
                                bear2.setVisibility(View.INVISIBLE);
                                bear3.setVisibility(View.INVISIBLE);
                                bear1.setVisibility(View.INVISIBLE);
                            }
                            else if ( i == 10 || i == 22 || i == 34 ) {
                                bear1.setVisibility(View.VISIBLE);
                                bear2.setVisibility(View.INVISIBLE);
                                bear3.setVisibility(View.INVISIBLE);
                                bear4.setVisibility(View.INVISIBLE);
                            }
                            else if ( i == 12 || i == 24 || i == 36 ) {
                                bear4.setVisibility(View.VISIBLE);
                                bear1.setVisibility(View.INVISIBLE);
                                bear2.setVisibility(View.INVISIBLE);
                                bear1.setVisibility(View.INVISIBLE);
                            }

                        }

                        missed = 0;

                        bear1.setEnabled(true);
                        bear2.setEnabled(true);
                        bear3.setEnabled(true);
                        bear4.setEnabled(true);
                        clicked = "monkey";
                        startTime = System.currentTimeMillis();
                        bear1.postDelayed(this, 3000);
                        totalCorrectResponses++;
                        duration = duration + 3000;
                        i++;
                    }

                }
                else {
                    long gameEnd = System.currentTimeMillis();
                    long seconds = (gameEnd - gameStart) / 1000;
                    if ( noOfCorrectResponses == 0 ) {
                        meanReactionTime = 0;
                    }
                    else {
                        meanReactionTime = (int) Math.ceil(totalReactionTime / noOfCorrectResponses); // ms
                    }
                    Log.d("****************************************************", "****************************************************");
                    Log.d("Game Time", String.valueOf(seconds));
                    Log.d("total", String.valueOf(totalCorrectResponses));
                    Log.d("correctResponses", String.valueOf(noOfCorrectResponses));
                    noOfOmmissionErrors = totalCorrectResponses - noOfCorrectResponses;
                    Log.d("omissionErrors", String.valueOf(totalCorrectResponses - noOfCorrectResponses));
                    Log.d("commissionErrors", String.valueOf(noOfCommissionErrors));
                    Log.d("meanReactionTime", String.valueOf(meanReactionTime));
                    Log.d("duration", String.valueOf(duration));
                    sequence_of_responses.remove(0);
                    Log.d("responses", String.valueOf(sequence_of_responses));
                    //saveDataToOnlineDB();
                    createTable();
                    saveDataToLocalDB();

                    if (Map1Activity.level == 1){
                        comp1 = 1;
                        myEdit.putInt("fal1", Map1Activity.comp1);
                        myEdit.commit();
                    }
                    else if (Map1Activity.level == 2){
                        comp2 = 1;
                        myEdit.putInt("fal2", Map1Activity.comp2);
                        myEdit.commit();
                    }
                    Intent intent = new Intent(getApplicationContext(), FACompleteScreen.class);
                    finish();
                    mp.pause();
                    startActivity(intent);
                }

            }
        }.run();

        bear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animZoomOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
                bear1.startAnimation(animZoomOut);
                mp2 = MediaPlayer.create(getApplicationContext(), R.raw.button_click);
                mp2.start();
                // Your action here on button click
                clickedTime = System.currentTimeMillis();
                reactionTime = (clickedTime - startTime);
                //if (clicked.equals("monkey")) {
                if ( i - 1 == 2 || i - 1 == 14 || i - 1 == 26 || i - 1 == 38 || i - 1 == 10 || i - 1 == 22 || i - 1 == 34) {
                    totalReactionTime = totalReactionTime + reactionTime;
                    Log.d("correct ", startTime + " " + clickedTime + " " + reactionTime);
                    noOfCorrectResponses++;
                    missed = 1;
                    sequence_of_responses.add("C");
                    bear1.setEnabled(false);
                } else {
                    Log.d("wrong", startTime + " " + clickedTime + " " + reactionTime);
                    noOfCommissionErrors++;
                }
            }
        });

        bear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animZoomOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
                bear2.startAnimation(animZoomOut);
                mp2 = MediaPlayer.create(getApplicationContext(), R.raw.button_click);
                mp2.start();
                // Your action here on button click
                clickedTime = System.currentTimeMillis();
                reactionTime = (clickedTime - startTime);
                //if (clicked.equals("monkey")) {
                if ( i - 1 == 6 || i - 1 == 18 || i - 1 == 30 || i - 1 == 42 ) {
                    totalReactionTime = totalReactionTime + reactionTime;
                    Log.d("correct ", startTime + " " + clickedTime + " " + reactionTime);
                    noOfCorrectResponses++;
                    missed = 1;
                    sequence_of_responses.add("C");
                    bear2.setEnabled(false);
                } else {
                    Log.d("wrong", startTime + " " + clickedTime + " " + reactionTime);
                    noOfCommissionErrors++;
                }
            }
        });

        bear3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animZoomOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
                bear3.startAnimation(animZoomOut);
                mp2 = MediaPlayer.create(getApplicationContext(), R.raw.button_click);
                mp2.start();
                // Your action here on button click
                clickedTime = System.currentTimeMillis();
                reactionTime = (clickedTime - startTime);
                if ( i - 1 == 4 || i - 1 == 16 || i - 1 == 28 || i - 1 == 40 ) {
                    totalReactionTime = totalReactionTime + reactionTime;
                    Log.d("correct ", startTime + " " + clickedTime + " " + reactionTime);
                    noOfCorrectResponses++;
                    missed = 1;
                    sequence_of_responses.add("C");
                    bear3.setEnabled(false);
                } else {
                    Log.d("wrong", startTime + " " + clickedTime + " " + reactionTime);
                    noOfCommissionErrors++;
                }

            }
        });

        bear4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animZoomOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
                bear4.startAnimation(animZoomOut);
                mp2 = MediaPlayer.create(getApplicationContext(), R.raw.button_click);
                mp2.start();
                // Your action here on button click
                clickedTime = System.currentTimeMillis();
                reactionTime = (clickedTime - startTime);
                if ( i - 1 == 8 || i - 1 == 20 || i - 1 == 32 || i - 1 == 12 || i - 1 == 24 || i - 1 == 36 )  {
                    totalReactionTime = totalReactionTime + reactionTime;
                    Log.d("correct ", startTime + " " + clickedTime + " " + reactionTime);
                    noOfCorrectResponses++;
                    missed = 1;
                    sequence_of_responses.add("C");
                    bear4.setEnabled(false);
                } else {
                    Log.d("wrong", startTime + " " + clickedTime + " " + reactionTime);
                    noOfCommissionErrors++;
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

    private void getCount() {

        if (AgeActivity.age == 4 ) {
            count = 17;
        }
        else if (AgeActivity.age == 5 ) {
            count = 21;
        }
        else if (AgeActivity.age == 6 ) {
            count = 25;
        }
        else {
            count= 29;
        }

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
        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_FOCUSED_ATTENTION, params, CODE_POST_REQUEST);
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
                    //refreshing the herolist after every operation
                    //so we get an updated list
                    //we will create this method right now it is commented
                    //because we haven't created it yet
                    //refreshHeroList(object.getJSONArray("heroes"));
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
                "CREATE TABLE IF NOT EXISTS focusedAttention (\n" +
                        "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                        "    childID int NOT NULL,\n" +
                        "    stimulus text NOT NULL,\n" +
                        "    colour text NOT NULL,\n" +
                        "    sequence_of_responses text NOT NULL,\n" +
                        "    totalCorrectResponses int NOT NULL,\n" +
                        "    noOfCorrectResponses int NOT NULL,\n" +
                        "    noOfCommissionErrors int NOT NULL,\n" +
                        "    noOfOmmissionErrors int NOT NULL,\n" +
                        "    meanReactionTime int NOT NULL,\n" +
                        "    totalDuration int NOT NULL,\n" +
                        "    diagnosis text NOT NULL\n" +
                        ");"
        );
    }

    /*************************************************************************************************/

    private void saveDataToLocalDB() {

        String child = String.valueOf(GenderActivity.gender) + String.valueOf(AgeActivity.age) + String.valueOf(Map1Activity.level);
        int child_ID = Integer.parseInt(child);
        int total_correct_responses = totalCorrectResponses;
        int no_of_correct_responses = noOfCorrectResponses;
        int no_of_ommission_errors = noOfOmmissionErrors;
        int no_of_commission_errors = noOfCommissionErrors;
        int mean_reaction_time = (int) meanReactionTime;
        int total_duration = duration;

        String insertSQL = "INSERT INTO focusedAttention \n" +
                "(childID, stimulus, colour, sequence_of_responses, totalCorrectResponses, noOfCorrectResponses, noOfCommissionErrors, noOfOmmissionErrors, meanReactionTime, totalDuration, diagnosis)\n" +
                "VALUES \n" +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        mDatabase.execSQL(insertSQL, new Object[]{child_ID, "bear", "brown", sequence_of_responses, total_correct_responses, no_of_correct_responses, no_of_commission_errors, no_of_ommission_errors, mean_reaction_time, total_duration, ParentDetailsActivity.diagnosis});

    }

    /*************************************************************************************************/

}