package com.anuththara18.attentionassessment.alternating;

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
import com.anuththara18.attentionassessment.focused.FACompleteScreen;
import com.anuththara18.attentionassessment.gender.GenderActivity;
import com.anuththara18.attentionassessment.home.NavigationDrawerActivity;
import com.anuththara18.attentionassessment.language.LanguageSetter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Random;

public class AlternatingAttentionGame1 extends AppCompatActivity {

    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    public static final String DATABASE_NAME = "alternatingAttention";
    SQLiteDatabase mDatabase;

    ImageView imageView1, imageView2, cross_btn;
    //ImageButton red_btn_left, red_btn_right;
    TextView textView;

    Random random = new Random();
    int radomImage = 0;
    int randomSide;
    String side;

    int i = 1;
    private long startTime, clickedTime = 0;
    long reactionTime;

    String clickedSide;

    int totalCorrectResponses = 0;
    int noOfCorrectResponses = 0;
    int noOfCommissionErrors = 0;
    int noOfOmmissionErrors = 0;
    long totalReactionTime = 0;
    int meanReactionTime = 0;
    int duration = 0;

    int leftcount, rightcount = 0;

    Integer[] left_images = { R.drawable.dancing_bunny,
            R.drawable.motor_piggy,
            R.drawable.waving_dog,
            R.drawable.dancing_cat,
            R.drawable.walking_unicorn,
            R.drawable.yellow_bird_gif};

    Integer[] right_images = { R.drawable.dancing_star,
            R.drawable.blowing_crab,
            R.drawable.swimming_fish,
            R.drawable.blue_fishy,
            R.drawable.brown_octopus,
            R.drawable.yellow_bird_gif};

    //Integer[] sequence1 = { 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0};

    MediaPlayer mp, mp2, mp3;

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
        setContentView(R.layout.activity_alternating_attention_game1);

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        //red_btn_left = findViewById(R.id.red_btn_left);
        //red_btn_right = findViewById(R.id.red_btn_right);
        textView = findViewById(R.id.textView);
        cross_btn = (ImageView) findViewById(R.id.cross_btn);

        textView.setText(LanguageSetter.getresources().getString(R.string.altg1));

        mp = MediaPlayer.create(getApplicationContext(), R.raw.alternating);
        mp.start();

        long gameStart = System.currentTimeMillis();

        //creating a database
        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);

        new Runnable() {

            @Override
            public void run() {

                randomSide = random.nextInt(100);

                if ( i < 20 ) { // 20 times

                    if ( randomSide >= 0 && randomSide <= 10) {
                        side ="left";
                    } else if ( randomSide > 10 && randomSide <= 20) {
                        side = "right";
                    } else if ( randomSide > 20 && randomSide <= 30) {
                        side ="left";
                    } else if ( randomSide > 30 && randomSide <= 40) {
                        side ="right";
                    } else if ( randomSide > 40 && randomSide <= 50) {
                        side ="left";
                    } else if ( randomSide > 50 && randomSide <= 60) {
                        side ="right";
                    } else if ( randomSide > 60 && randomSide <= 70) {
                        side ="left";
                    } else if ( randomSide > 70 && randomSide <= 80) {
                        side ="right";
                    } else if ( randomSide > 80 && randomSide <= 90) {
                        side ="left";
                    } else if ( randomSide > 90 && randomSide <= 100) {
                        side ="right";
                    }

                    if( side.equals("left")) {
                        radomImage = random.nextInt(4);
                        //imageView2.setVisibility(View.INVISIBLE);
                        imageView2.setImageResource(0);
                        imageView1.setVisibility(View.VISIBLE);
                        if (leftcount > 4 ){
                            leftcount = 0;
                        }
                        imageView1.setImageResource(left_images[leftcount]);
                        imageView1.setEnabled(true);
                        imageView2.setEnabled(true);
                        leftcount++;
                        startTime = System.currentTimeMillis();
                        clickedSide = "left";
                        imageView1.postDelayed(this, 3000);
                        totalCorrectResponses++;
                        Log.d("***************radomTimer******************", String.valueOf(i + " " + side));
                        duration = duration + 3000;
                        i++;
                    }
                    // appearance - 1s * 20 times
                    else {
                        radomImage = random.nextInt(4);
                        //imageView1.setVisibility(View.INVISIBLE);
                        imageView1.setImageResource(0);
                        imageView2.setVisibility(View.VISIBLE);
                        if (rightcount > 4 ){
                            rightcount = 0;
                        }
                        imageView2.setImageResource(right_images[rightcount]);
                        imageView1.setEnabled(true);
                        imageView2.setEnabled(true);
                        rightcount++;
                        startTime = System.currentTimeMillis();
                        clickedSide = "right";
                        imageView2.postDelayed(this, 3000);
                        totalCorrectResponses++;
                        Log.d("***************radomTimer******************", String.valueOf(i + "  " + side ));
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
                        meanReactionTime = (int) (totalReactionTime / noOfCorrectResponses); // ms
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
                    saveDataToOnlineDB();
                    createTable();
                    saveDataToLocalDB();
                    Intent intent = new Intent(getApplicationContext(), AACompleteScreen.class);
                    finish();
                    mp.pause();
                    startActivity(intent);
                }

            }
        }.run();

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp2 = MediaPlayer.create(getApplicationContext(), R.raw.button_click);
                mp2.start();
                clickedTime = System.currentTimeMillis();
                reactionTime = ( clickedTime - startTime );
                if (clickedSide.equals("left")) {
                    totalReactionTime = totalReactionTime + reactionTime;
                    Log.d("correct " , startTime + " " + clickedTime + " " + reactionTime);
                    noOfCorrectResponses++;
                    imageView1.setEnabled(false);
                }
                else {
                    Log.d( "wrong" , startTime + " " + clickedTime + " " + reactionTime);
                    noOfCommissionErrors++;
                }
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp3 = MediaPlayer.create(getApplicationContext(), R.raw.button_click);
                mp3.start();
                clickedTime = System.currentTimeMillis();
                reactionTime = ( clickedTime - startTime );
                if (clickedSide.equals("right")) {
                    totalReactionTime = totalReactionTime + reactionTime;
                    Log.d("correct " , startTime + " " + clickedTime + " " + reactionTime);
                    noOfCorrectResponses++;
                    imageView2.setEnabled(false);
                }
                else {
                    Log.d( "wrong" , startTime + " " + clickedTime + " " + reactionTime);
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
                "CREATE TABLE IF NOT EXISTS alternatingAttention (\n" +
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

        String insertSQL = "INSERT INTO alternatingAttention \n" +
                "(childID, totalCorrectResponses, noOfCorrectResponses, noOfCommissionErrors, noOfOmmissionErrors, meanReactionTime, totalDuration)\n" +
                "VALUES \n" +
                "(?, ?, ?, ?, ?, ?, ?);";

        mDatabase.execSQL(insertSQL, new Integer[]{child_ID, total_correct_responses, no_of_correct_responses, no_of_commission_errors, no_of_ommission_errors, mean_reaction_time, total_duration});

        //Toast.makeText(this, "Data Added Successfully", Toast.LENGTH_SHORT).show();
    }

    /*************************************************************************************************/

}