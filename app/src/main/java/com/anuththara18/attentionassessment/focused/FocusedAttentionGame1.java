package com.anuththara18.attentionassessment.focused;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PorterDuff;
import android.graphics.drawable.AnimationDrawable;
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

import com.anuththara18.attentionassessment.CompleteScreen;
import com.anuththara18.attentionassessment.R;
import com.anuththara18.attentionassessment.age.AgeActivity;
import com.anuththara18.attentionassessment.db.Api;
import com.anuththara18.attentionassessment.db.RequestHandler;
import com.anuththara18.attentionassessment.gender.GenderActivity;
import com.anuththara18.attentionassessment.home.NavigationDrawerActivity;
import com.anuththara18.attentionassessment.language.LanguageSetter;
import com.anuththara18.attentionassessment.map.Map1Activity;
import com.anuththara18.attentionassessment.sustained.SustainedAttentionGame1;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class FocusedAttentionGame1 extends AppCompatActivity {

    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    public static final String DATABASE_NAME = "focusedAttention";
    SQLiteDatabase mDatabase;

    Random random = new Random();
    int radomTimer = 0;
    int rand = 0;

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

    ImageView imageView, cross_btn, imageView2, imageView3, imageView4, imageView5, imageView6;
    ImageButton red_btn;
    TextView textView;

    String clicked = "null";

    Integer[] isi = { 3000, 3500, 4000, 4500, 5000, 5500, 6000 };

    Integer[] images = {R.drawable.monkey, R.drawable.baby_zebra, R.drawable.elephant, R.drawable.pig, R.drawable.giraffe,
                        R.drawable.cow, R.drawable.horse, R.drawable.dino, R.drawable.lion, R.drawable.dog};

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
        setContentView(R.layout.activity_focused_attention_game1);

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        imageView4 = (ImageView) findViewById(R.id.imageView4);
        imageView5 = (ImageView) findViewById(R.id.imageView5);
        imageView6 = (ImageView) findViewById(R.id.imageView6);
        cross_btn = (ImageView) findViewById(R.id.cross_btn);
        red_btn = (ImageButton) findViewById(R.id.red_btn);
        textView = (TextView) findViewById(R.id.textView);

        //textView.setText(LanguageSetter.getresources().getString(R.string.focg1));

        mp = MediaPlayer.create(getApplicationContext(), R.raw.focused);
        mp.start();

        long gameStart = System.currentTimeMillis();

        //creating a database
        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);

        List<Integer> unpickedNumbers = new ArrayList<Integer>();
        unpickedNumbers.add(0);
        unpickedNumbers.add(1);
        unpickedNumbers.add(2);
        unpickedNumbers.add(3);
        unpickedNumbers.add(4);
        unpickedNumbers.add(5);
        unpickedNumbers.add(6);
        unpickedNumbers.add(7);
        unpickedNumbers.add(8);
        unpickedNumbers.add(9);

        getCount();

        new Runnable() {
            int updateInterval;

            @Override
            public void run() {

                if ( i <= count ) { // 41 times

                    if ( i % 2 != 0 ) {
                        imageView.setVisibility(View.INVISIBLE);
                        imageView2.setVisibility(View.INVISIBLE);
                        imageView3.setVisibility(View.INVISIBLE);
                        imageView4.setVisibility(View.INVISIBLE);
                        imageView5.setVisibility(View.INVISIBLE);
                        imageView6.setVisibility(View.INVISIBLE);
                        clicked = "null";

                        imageView.setEnabled(true);
                        imageView2.setEnabled(true);
                        imageView3.setEnabled(true);
                        imageView4.setEnabled(true);
                        imageView5.setEnabled(true);
                        imageView6.setEnabled(true);

                        radomTimer = random.nextInt(5);
                        updateInterval = isi[radomTimer];
                        imageView.postDelayed(this, updateInterval);
                        Log.d("***************radomTimer******************", String.valueOf(i + " " + updateInterval));
                        duration = duration + updateInterval;
                        i++;
                    }
                    // appearance - 1s * 20 times
                    else {
                        Log.d("***************radomTimer******************", String.valueOf(i + " 3000" ));

                        if ( i == 22 ) {
                            unpickedNumbers.add(0);
                            unpickedNumbers.add(1);
                            unpickedNumbers.add(2);
                            unpickedNumbers.add(3);
                            unpickedNumbers.add(4);
                            unpickedNumbers.add(5);
                            unpickedNumbers.add(6);
                            unpickedNumbers.add(7);
                            unpickedNumbers.add(8);
                            unpickedNumbers.add(9);
                        }
                        rand = getRandomElement(unpickedNumbers);

                        if ( Map1Activity.level == 3 ) {

                            if ( i == 2 || i == 14 || i == 26 || i == 38 ) {
                                imageView.setVisibility(View.VISIBLE);
                                imageView2.setVisibility(View.INVISIBLE);
                                imageView3.setVisibility(View.INVISIBLE);
                                imageView4.setVisibility(View.INVISIBLE);
                                imageView5.setVisibility(View.INVISIBLE);
                                imageView6.setVisibility(View.INVISIBLE);
                                imageView.setImageResource(images[AnimalChoosingActivity.birdSelected]);
                            }
                            else if ( i == 4 || i == 16 || i == 28 || i == 40 ) {
                                imageView2.setVisibility(View.VISIBLE);
                                imageView.setVisibility(View.INVISIBLE);
                                imageView3.setVisibility(View.INVISIBLE);
                                imageView4.setVisibility(View.INVISIBLE);
                                imageView5.setVisibility(View.INVISIBLE);
                                imageView6.setVisibility(View.INVISIBLE);
                                imageView2.setImageResource(images[AnimalChoosingActivity.birdSelected]);
                            }
                            else if ( i == 6 || i == 18 || i == 30 ) {
                                imageView3.setVisibility(View.VISIBLE);
                                imageView2.setVisibility(View.INVISIBLE);
                                imageView.setVisibility(View.INVISIBLE);
                                imageView4.setVisibility(View.INVISIBLE);
                                imageView5.setVisibility(View.INVISIBLE);
                                imageView6.setVisibility(View.INVISIBLE);
                                imageView3.setImageResource(images[AnimalChoosingActivity.birdSelected]);
                            }
                            else if ( i == 8 || i == 20 || i == 32 ) {
                                imageView4.setVisibility(View.VISIBLE);
                                imageView2.setVisibility(View.INVISIBLE);
                                imageView3.setVisibility(View.INVISIBLE);
                                imageView.setVisibility(View.INVISIBLE);
                                imageView5.setVisibility(View.INVISIBLE);
                                imageView6.setVisibility(View.INVISIBLE);
                                imageView4.setImageResource(images[AnimalChoosingActivity.birdSelected]);
                            }
                            else if ( i == 10 || i == 22 || i == 34 ) {
                                imageView5.setVisibility(View.VISIBLE);
                                imageView2.setVisibility(View.INVISIBLE);
                                imageView3.setVisibility(View.INVISIBLE);
                                imageView4.setVisibility(View.INVISIBLE);
                                imageView.setVisibility(View.INVISIBLE);
                                imageView6.setVisibility(View.INVISIBLE);
                                imageView5.setImageResource(images[AnimalChoosingActivity.birdSelected]);
                            }
                            else if ( i == 12 || i == 24 || i == 36 ) {
                                imageView6.setVisibility(View.VISIBLE);
                                imageView2.setVisibility(View.INVISIBLE);
                                imageView3.setVisibility(View.INVISIBLE);
                                imageView4.setVisibility(View.INVISIBLE);
                                imageView5.setVisibility(View.INVISIBLE);
                                imageView.setVisibility(View.INVISIBLE);
                                imageView6.setImageResource(images[AnimalChoosingActivity.birdSelected]);
                            }

                        }

                        else if ( Map1Activity.level == 4 ) {

                            if ( i == 2 || i == 14 || i == 26 || i == 38 ) {
                                imageView.setVisibility(View.VISIBLE);
                                imageView2.setVisibility(View.INVISIBLE);
                                imageView3.setVisibility(View.INVISIBLE);
                                imageView4.setVisibility(View.INVISIBLE);
                                imageView5.setVisibility(View.INVISIBLE);
                                imageView6.setVisibility(View.INVISIBLE);
                                imageView.setImageResource(images[AnimalChoosingActivity.birdSelected]);
                            }
                            else if ( i == 4 || i == 16 || i == 28 || i == 40 ) {
                                imageView2.setVisibility(View.VISIBLE);
                                imageView.setVisibility(View.INVISIBLE);
                                imageView3.setVisibility(View.INVISIBLE);
                                imageView4.setVisibility(View.INVISIBLE);
                                imageView5.setVisibility(View.INVISIBLE);
                                imageView6.setVisibility(View.INVISIBLE);
                                imageView2.setImageResource(images[AnimalChoosingActivity.birdSelected]);
                            }
                            else if ( i == 6 || i == 18 || i == 30 ) {
                                imageView3.setVisibility(View.VISIBLE);
                                imageView2.setVisibility(View.INVISIBLE);
                                imageView.setVisibility(View.INVISIBLE);
                                imageView4.setVisibility(View.INVISIBLE);
                                imageView5.setVisibility(View.INVISIBLE);
                                imageView6.setVisibility(View.INVISIBLE);
                                imageView3.setImageResource(images[AnimalChoosingActivity.birdSelected]);
                            }
                            else if ( i == 8 || i == 20 || i == 32 ) {
                                imageView4.setVisibility(View.VISIBLE);
                                imageView2.setVisibility(View.INVISIBLE);
                                imageView3.setVisibility(View.INVISIBLE);
                                imageView.setVisibility(View.INVISIBLE);
                                imageView5.setVisibility(View.INVISIBLE);
                                imageView6.setVisibility(View.INVISIBLE);
                                imageView4.setImageResource(images[AnimalChoosingActivity.birdSelected]);
                            }
                            else if ( i == 10 || i == 22 || i == 34 ) {
                                imageView5.setVisibility(View.VISIBLE);
                                imageView2.setVisibility(View.INVISIBLE);
                                imageView3.setVisibility(View.INVISIBLE);
                                imageView4.setVisibility(View.INVISIBLE);
                                imageView.setVisibility(View.INVISIBLE);
                                imageView6.setVisibility(View.INVISIBLE);
                                imageView5.setImageResource(images[AnimalChoosingActivity.birdSelected]);
                            }
                            else if ( i == 12 || i == 24 || i == 36 ) {
                                imageView6.setVisibility(View.VISIBLE);
                                imageView2.setVisibility(View.INVISIBLE);
                                imageView3.setVisibility(View.INVISIBLE);
                                imageView4.setVisibility(View.INVISIBLE);
                                imageView5.setVisibility(View.INVISIBLE);
                                imageView.setVisibility(View.INVISIBLE);
                                imageView6.setImageResource(images[AnimalChoosingActivity.birdSelected]);
                            }

                        }

                        else if ( Map1Activity.level == 5 ) {

                            if ( i == 2 || i == 14 || i == 26 || i == 38 ) {
                                imageView.setVisibility(View.VISIBLE);
                                imageView2.setVisibility(View.INVISIBLE);
                                imageView3.setVisibility(View.INVISIBLE);
                                imageView4.setVisibility(View.INVISIBLE);
                                imageView5.setVisibility(View.INVISIBLE);
                                imageView6.setVisibility(View.INVISIBLE);
                                imageView.setImageResource(images[rand]);
                            }
                            else if ( i == 4 || i == 16 || i == 28 || i == 40 ) {
                                imageView2.setVisibility(View.VISIBLE);
                                imageView.setVisibility(View.INVISIBLE);
                                imageView3.setVisibility(View.INVISIBLE);
                                imageView4.setVisibility(View.INVISIBLE);
                                imageView5.setVisibility(View.INVISIBLE);
                                imageView6.setVisibility(View.INVISIBLE);
                                imageView2.setImageResource(images[rand]);
                            }
                            else if ( i == 6 || i == 18 || i == 30 ) {
                                imageView3.setVisibility(View.VISIBLE);
                                imageView2.setVisibility(View.INVISIBLE);
                                imageView.setVisibility(View.INVISIBLE);
                                imageView4.setVisibility(View.INVISIBLE);
                                imageView5.setVisibility(View.INVISIBLE);
                                imageView6.setVisibility(View.INVISIBLE);
                                imageView3.setImageResource(images[rand]);
                            }
                            else if ( i == 8 || i == 20 || i == 32 ) {
                                imageView4.setVisibility(View.VISIBLE);
                                imageView2.setVisibility(View.INVISIBLE);
                                imageView3.setVisibility(View.INVISIBLE);
                                imageView.setVisibility(View.INVISIBLE);
                                imageView5.setVisibility(View.INVISIBLE);
                                imageView6.setVisibility(View.INVISIBLE);
                                imageView4.setImageResource(images[rand]);
                            }
                            else if ( i == 10 || i == 22 || i == 34 ) {
                                imageView5.setVisibility(View.VISIBLE);
                                imageView2.setVisibility(View.INVISIBLE);
                                imageView3.setVisibility(View.INVISIBLE);
                                imageView4.setVisibility(View.INVISIBLE);
                                imageView.setVisibility(View.INVISIBLE);
                                imageView6.setVisibility(View.INVISIBLE);
                                imageView5.setImageResource(images[rand]);
                            }
                            else if ( i == 12 || i == 24 || i == 36 ) {
                                imageView6.setVisibility(View.VISIBLE);
                                imageView2.setVisibility(View.INVISIBLE);
                                imageView3.setVisibility(View.INVISIBLE);
                                imageView4.setVisibility(View.INVISIBLE);
                                imageView5.setVisibility(View.INVISIBLE);
                                imageView.setVisibility(View.INVISIBLE);
                                imageView6.setImageResource(images[rand]);
                            }

                        }

                        imageView.setEnabled(true);
                        imageView2.setEnabled(true);
                        imageView3.setEnabled(true);
                        imageView4.setEnabled(true);
                        imageView5.setEnabled(true);
                        imageView6.setEnabled(true);
                        clicked = "monkey";
                        startTime = System.currentTimeMillis();
                        imageView.postDelayed(this, 3000);
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
                    saveDataToOnlineDB();
                    createTable();
                    saveDataToLocalDB();
                    Intent intent = new Intent(getApplicationContext(), FACompleteScreen.class);
                    finish();
                    mp.pause();
                    startActivity(intent);
                }

            }
        }.run();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    mp2 = MediaPlayer.create(getApplicationContext(), R.raw.button_click);
                    mp2.start();
                    // Your action here on button click
                    clickedTime = System.currentTimeMillis();
                    reactionTime = (clickedTime - startTime);
                    //if (clicked.equals("monkey")) {
                    if ( i - 1 == 2 || i - 1 == 14 || i - 1 == 26 || i - 1 == 38 ) {
                        totalReactionTime = totalReactionTime + reactionTime;
                        Log.d("correct ", startTime + " " + clickedTime + " " + reactionTime);
                        noOfCorrectResponses++;
                        imageView.setEnabled(false);
                    } else {
                        Log.d("wrong", startTime + " " + clickedTime + " " + reactionTime);
                        noOfCommissionErrors++;
                    }
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    mp2 = MediaPlayer.create(getApplicationContext(), R.raw.button_click);
                    mp2.start();
                    // Your action here on button click
                    clickedTime = System.currentTimeMillis();
                    reactionTime = (clickedTime - startTime);
                    //if (clicked.equals("monkey")) {
                    if ( i - 1 == 4 || i - 1 == 16 || i - 1 == 28 || i - 1 == 40 ) {
                        totalReactionTime = totalReactionTime + reactionTime;
                        Log.d("correct ", startTime + " " + clickedTime + " " + reactionTime);
                        noOfCorrectResponses++;
                        imageView2.setEnabled(false);
                    } else {
                        Log.d("wrong", startTime + " " + clickedTime + " " + reactionTime);
                        noOfCommissionErrors++;
                    }
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    mp2 = MediaPlayer.create(getApplicationContext(), R.raw.button_click);
                    mp2.start();
                    // Your action here on button click
                    clickedTime = System.currentTimeMillis();
                    reactionTime = (clickedTime - startTime);
                    if ( i - 1 == 6 || i - 1 == 18 || i - 1 == 30 ) {
                        totalReactionTime = totalReactionTime + reactionTime;
                        Log.d("correct ", startTime + " " + clickedTime + " " + reactionTime);
                        noOfCorrectResponses++;
                        imageView3.setEnabled(false);
                    } else {
                        Log.d("wrong", startTime + " " + clickedTime + " " + reactionTime);
                        noOfCommissionErrors++;
                    }

            }
        });

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    mp2 = MediaPlayer.create(getApplicationContext(), R.raw.button_click);
                    mp2.start();
                    // Your action here on button click
                    clickedTime = System.currentTimeMillis();
                    reactionTime = (clickedTime - startTime);
                    if ( i - 1 == 8 || i - 1 == 20 || i - 1 == 32 )  {
                        totalReactionTime = totalReactionTime + reactionTime;
                        Log.d("correct ", startTime + " " + clickedTime + " " + reactionTime);
                        noOfCorrectResponses++;
                        imageView4.setEnabled(false);
                    } else {
                        Log.d("wrong", startTime + " " + clickedTime + " " + reactionTime);
                        noOfCommissionErrors++;
                    }

            }
        });

        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    mp2 = MediaPlayer.create(getApplicationContext(), R.raw.button_click);
                    mp2.start();
                    // Your action here on button click
                    clickedTime = System.currentTimeMillis();
                    reactionTime = (clickedTime - startTime);
                    if ( i - 1 == 10 || i - 1 == 22 || i - 1 == 34 ) {
                        totalReactionTime = totalReactionTime + reactionTime;
                        Log.d("correct ", startTime + " " + clickedTime + " " + reactionTime);
                        noOfCorrectResponses++;
                        imageView5.setEnabled(false);
                    } else {
                        Log.d("wrong", startTime + " " + clickedTime + " " + reactionTime);
                        noOfCommissionErrors++;
                    }

            }
        });

        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    mp2 = MediaPlayer.create(getApplicationContext(), R.raw.button_click);
                    mp2.start();
                    // Your action here on button click
                    clickedTime = System.currentTimeMillis();
                    reactionTime = (clickedTime - startTime);
                    if ( i - 1 == 12 || i - 1 == 24 || i - 1 == 36 ) {
                        totalReactionTime = totalReactionTime + reactionTime;
                        Log.d("correct ", startTime + " " + clickedTime + " " + reactionTime);
                        noOfCorrectResponses++;
                        imageView6.setEnabled(false);
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

    // Function select an element base on index
    // and return an element
    public int getRandomElement(List<Integer> list) {
        Random rand = new Random();
        //int val = rand.nextInt(list.size());
        int val = list.get(rand.nextInt(list.size()));
        //list.remove("val");

        Iterator itr = list.iterator();
        while (itr.hasNext()) {
            int data = (Integer)itr.next();
            if (data == val)
                itr.remove();
        }

        Log.d("1111111111111111111111111", val + "----" + list);
        return val;
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

        String insertSQL = "INSERT INTO focusedAttention \n" +
                "(childID, totalCorrectResponses, noOfCorrectResponses, noOfCommissionErrors, noOfOmmissionErrors, meanReactionTime, totalDuration)\n" +
                "VALUES \n" +
                "(?, ?, ?, ?, ?, ?, ?);";

        mDatabase.execSQL(insertSQL, new Integer[]{child_ID, total_correct_responses, no_of_correct_responses, no_of_commission_errors, no_of_ommission_errors, mean_reaction_time, total_duration});

        //Toast.makeText(this, "Data Added Successfully", Toast.LENGTH_SHORT).show();
    }

    /*************************************************************************************************/

}

/****************************************************************************************************************

 if ( age >= 4 && age <= 5 ) {
 if (level == 1) {
 radomMainImage = random.nextInt(1);
 }
 else if (level == 2) {
 radomMainImage = random.nextInt(4);
 }
 else if (level == 3) {
 radomMainImage = random.nextInt(8);
 }
 else if (level == 4) {
 radomMainImage = random.nextInt(12);
 }
 else if (level == 5) {
 radomMainImage = random.nextInt(16);
 }
 }

 if ( age >= 6 && age <= 7 ) {
 if (level == 1) {
 radomMainImage = random.nextInt(1);
 }
 else if (level == 2) {
 radomMainImage = random.nextInt(4);
 }
 else if (level == 3) {
 radomMainImage = random.nextInt(8);
 }
 else if (level == 4) {
 radomMainImage = random.nextInt(12);
 }
 else if (level == 5) {
 radomMainImage = random.nextInt(16);
 }
 }

 imageView_q.setImageResource(image[radomMainImage]);
 correctImage = image[radomMainImage];

 ****************************************************************************************************************/

/*
                    if ( age >= 4 && age <= 5 ) {
                        if (level == 1) {
                            radomTimer = random.nextInt(4);
                            radomImage = random.nextInt(1);
                            imax = 10;
                        }
                        else if (level == 2) {
                            radomTimer = random.nextInt(max - min + 1) + min;
                            radomImage = random.nextInt(4);
                            imax = 15;
                        }
                        else if (level == 3) {
                            radomImage = random.nextInt(8);
                            imax = 20;
                        }
                        else if (level == 4) {
                            radomImage = random.nextInt(12);
                            imax = 25;
                        }
                        else if (level == 5) {
                            radomImage = random.nextInt(16);
                            imax = 30;
                        }
                    }

                    if ( age >= 6 && age <= 7 ) {
                        if (level == 1) {
                            radomImage = random.nextInt(1);
                            imax = 15;
                        }
                        else if (level == 2) {
                            radomImage = random.nextInt(4);
                            imax = 20;
                        }
                        else if (level == 3) {
                            radomImage = random.nextInt(8);
                            imax = 25;
                        }
                        else if (level == 4) {
                            radomImage = random.nextInt(12);
                            imax = 30;
                        }
                        else if (level == 5) {
                            radomImage = random.nextInt(16);
                            imax = 35;
                        }
                    }

                    */

//int finalRadomImage = radomImage;
//int finalImax = imax;



    /*Integer[] image = { R.drawable.pink_pig, R.drawable.orange_tiger,R.drawable.green_dino, R.drawable.yellow_leapoard,
            R.drawable.white_cow, R.drawable.brown_donkey, R.drawable.grey_goat, R.drawable.blue_elephant,
            R.drawable.brown_ox, R.drawable.white_zebra, R.drawable.green_snake, R.drawable.blue_hippo,
            R.drawable.blue_peacock, R.drawable.yellow_girraffe};
    */
