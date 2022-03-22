package com.anuththara18.attentionassessment.dividedattention;

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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anuththara18.attentionassessment.CompleteScreen;
import com.anuththara18.attentionassessment.CompleteScreen2;
import com.anuththara18.attentionassessment.R;
import com.anuththara18.attentionassessment.age.AgeActivity;
import com.anuththara18.attentionassessment.db.Api;
import com.anuththara18.attentionassessment.db.RequestHandler;
import com.anuththara18.attentionassessment.focused.FocusedAttentionGame1;
import com.anuththara18.attentionassessment.gender.GenderActivity;
import com.anuththara18.attentionassessment.home.NavigationDrawerActivity;
import com.anuththara18.attentionassessment.language.LanguageSetter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class DividedAttentionGame1 extends AppCompatActivity {

    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    ImageView sq1, sq2;
    ImageButton cross_btn;
    TextView textView2;

    int i = 0;
    int correctImage, clickedImage;

    private long startTime, clickedTime = 0;
    long reactionTime;

    int totalCorrectResponses = 0;
    int noOfCorrectResponses = 0;
    int noOfCommissionErrors = 0;
    int noOfOmmissionErrors = 0;
    long meanReactionTime = 0; // total reaction time
    long totalReactionTime = 0;
    int duration = 0;

    public static final String DATABASE_NAME = "dividedAttention";
    SQLiteDatabase mDatabase;

    Integer[] image = { R.color.red, R.color.blue, R.color.yellow, R.color.green,
            R.color.pink, R.color.purple, R.color.orange, R.color.teal};

    Integer[] animal5 = {R.drawable.red_snake, R.drawable.blue_elephant, R.drawable.yelllow_giraffe, R.drawable.green_dino,
            R.drawable.pink_pig, R.drawable.purple_hippo, R.drawable.orange_tiger, R.drawable.brown_donkey};


    Integer[] animal7 = {R.drawable.cute_deer, R.drawable.cute_donkey, R.drawable.cute_elephant, R.drawable.cute_zebra,
            R.drawable.cute_giraffe, R.drawable.cute_horse, R.drawable.cute_snail, R.drawable.cute_unicorn};

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
        setContentView(R.layout.activity_divided_attention_game1);

        sq1 = (ImageView) findViewById(R.id.sq1);
        sq2 = (ImageView) findViewById(R.id.sq2);
        cross_btn = (ImageButton) findViewById(R.id.cross_btn);
        textView2 = (TextView) findViewById(R.id.textView2);

        textView2.setText(LanguageSetter.getresources().getString(R.string.divg1));

        mp = MediaPlayer.create(getApplicationContext(), R.raw.divided);
        mp.start();

        long gameStart = System.currentTimeMillis();

        //creating a database
        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);

        totalCorrectResponses = 8;

        new Runnable() {

            @Override
            public void run() {
                if ( i < 40 ) { // 40 times

                    /*
                    if (AgeActivity.age == 4) {

                        if (i >= 0 && i < 5) {
                            sq1.setImageResource(R.color.pink);
                            correctImage = R.color.pink;
                        } else if (i >= 5 && i < 10) {
                            sq1.setImageResource(R.color.blue);
                            correctImage = R.color.blue;
                        } else if (i >= 10 && i < 15) {
                            sq1.setImageResource(R.color.yellow);
                            correctImage = R.color.yellow;
                        } else if (i >= 15 && i < 20) {
                            sq1.setImageResource(R.color.green);
                            correctImage = R.color.green;
                        } else if (i >= 20 && i < 25) {
                            sq1.setImageResource(R.color.red);
                            correctImage = R.color.red;
                        } else if (i >= 25 && i < 30) {
                            sq1.setImageResource(R.color.purple);
                            correctImage = R.color.purple;
                        } else if (i >= 30 && i < 35) {
                            sq1.setImageResource(R.color.orange);
                            correctImage = R.color.orange;
                        } else if (i >= 35 && i < 40) {
                            sq1.setImageResource(R.color.teal);
                            correctImage = R.color.teal;
                        }

                        String j = String.valueOf(i);

                        if (j.equals("0") || j.equals("8") || j.equals("16") || j.equals("24") || j.equals("34") || j.equals("40")) {
                            sq2.setImageResource(image[0]);
                            sq2.setEnabled(true);
                            sq2.setTag(image[0]);
                        } else if (j.equals("1") || j.equals("6") || j.equals("17") || j.equals("25") || j.equals("33") || j.equals("41")) {
                            sq2.setImageResource(image[1]);
                            sq2.setEnabled(true);
                            sq2.setTag(image[1]);
                        } else if (j.equals("2") || j.equals("14") || j.equals("19") || j.equals("26") || j.equals("30") || j.equals("42")) {
                            sq2.setImageResource(image[2]);
                            sq2.setEnabled(true);
                            sq2.setTag(image[2]);
                        } else if (j.equals("4") || j.equals("11") || j.equals("18") || j.equals("27") || j.equals("35") || j.equals("43")) {
                            sq2.setImageResource(image[3]);
                            sq2.setEnabled(true);
                            sq2.setTag(image[3]);
                        } else if (j.equals("3") || j.equals("12") || j.equals("20") || j.equals("29") || j.equals("36") || j.equals("44")) {
                            sq2.setImageResource(image[4]);
                            sq2.setEnabled(true);
                            sq2.setTag(image[4]);
                        } else if (j.equals("5") || j.equals("13") || j.equals("21") || j.equals("28") || j.equals("37") || j.equals("45")) {
                            sq2.setImageResource(image[5]);
                            sq2.setEnabled(true);
                            sq2.setTag(image[5]);
                        } else if (j.equals("9") || j.equals("15") || j.equals("22") || j.equals("32") || j.equals("39") || j.equals("46")) {
                            sq2.setImageResource(image[6]);
                            sq2.setEnabled(true);
                            sq2.setTag(image[6]);
                        } else if (j.equals("7") || j.equals("10") || j.equals("23") || j.equals("31") || j.equals("38") || j.equals("47")) {
                            sq2.setImageResource(image[7]);
                            sq2.setEnabled(true);
                            sq2.setTag(image[7]);
                        }
                        startTime = System.currentTimeMillis();
                        sq2.postDelayed(this, 2000);
                        Log.d("int", String.valueOf(i));
                        duration = duration + 2000;
                        i++;
                    }
                    */

                    /*---------------------------------------------------------------------------------*/

                    if (AgeActivity.age == 4 || AgeActivity.age == 5) {

                        textView2.setText(LanguageSetter.getresources().getString(R.string.divg2));

                        if (i >= 0 && i < 5) {
                            sq1.setImageResource(R.drawable.pink_pig);
                            correctImage = R.drawable.pink_pig;
                        } else if (i >= 5 && i < 10) {
                            sq1.setImageResource(R.drawable.blue_elephant);
                            correctImage = R.drawable.blue_elephant;
                        } else if (i >= 10 && i < 15) {
                            sq1.setImageResource(R.drawable.yelllow_giraffe);
                            correctImage = R.drawable.yelllow_giraffe;
                        } else if (i >= 15 && i < 20) {
                            sq1.setImageResource(R.drawable.green_dino);
                            correctImage = R.drawable.green_dino;
                        } else if (i >= 20 && i < 25) {
                            sq1.setImageResource(R.drawable.red_snake);
                            correctImage = R.drawable.red_snake;
                        } else if (i >= 25 && i < 30) {
                            sq1.setImageResource(R.drawable.purple_hippo);
                            correctImage = R.drawable.purple_hippo;
                        } else if (i >= 30 && i < 35) {
                            sq1.setImageResource(R.drawable.orange_tiger);
                            correctImage = R.drawable.orange_tiger;
                        } else if (i >= 35 && i < 40) {
                            sq1.setImageResource(R.drawable.brown_donkey);
                            correctImage = R.drawable.brown_donkey;
                        }

                        String j = String.valueOf(i);

                        if (j.equals("0") || j.equals("8") || j.equals("16") || j.equals("24") || j.equals("34") || j.equals("40")) {
                            sq2.setImageResource(animal5[0]);
                            sq2.setEnabled(true);
                            sq2.setTag(animal5[0]);
                        } else if (j.equals("1") || j.equals("6") || j.equals("17") || j.equals("25") || j.equals("33") || j.equals("41")) {
                            sq2.setImageResource(animal5[1]);
                            sq2.setEnabled(true);
                            sq2.setTag(animal5[1]);
                        } else if (j.equals("2") || j.equals("14") || j.equals("19") || j.equals("26") || j.equals("30") || j.equals("42")) {
                            sq2.setImageResource(animal5[2]);
                            sq2.setEnabled(true);
                            sq2.setTag(animal5[2]);
                        } else if (j.equals("4") || j.equals("11") || j.equals("18") || j.equals("27") || j.equals("35") || j.equals("43")) {
                            sq2.setImageResource(animal5[3]);
                            sq2.setEnabled(true);
                            sq2.setTag(animal5[3]);
                        } else if (j.equals("3") || j.equals("12") || j.equals("20") || j.equals("29") || j.equals("36") || j.equals("44")) {
                            sq2.setImageResource(animal5[4]);
                            sq2.setEnabled(true);
                            sq2.setTag(animal5[4]);
                        } else if (j.equals("5") || j.equals("13") || j.equals("21") || j.equals("28") || j.equals("37") || j.equals("45")) {
                            sq2.setImageResource(animal5[5]);
                            sq2.setEnabled(true);
                            sq2.setTag(animal5[5]);
                        } else if (j.equals("9") || j.equals("15") || j.equals("22") || j.equals("32") || j.equals("39") || j.equals("46")) {
                            sq2.setImageResource(animal5[6]);
                            sq2.setEnabled(true);
                            sq2.setTag(animal5[6]);
                        } else if (j.equals("7") || j.equals("10") || j.equals("23") || j.equals("31") || j.equals("38") || j.equals("47")) {
                            sq2.setImageResource(animal5[7]);
                            sq2.setEnabled(true);
                            sq2.setTag(animal5[7]);
                        }
                        startTime = System.currentTimeMillis();
                        sq2.postDelayed(this, 1750);
                        Log.d("int", String.valueOf(i));
                        duration = duration + 1750;
                        i++;
                    }

                    /*---------------------------------------------------------------------------------*/

                    if (AgeActivity.age == 6 || AgeActivity.age == 7) {
                        textView2.setText(LanguageSetter.getresources().getString(R.string.divg2));

                        if (i >= 0 && i < 5) {
                            sq1.setImageResource(R.drawable.cute_giraffe);
                            correctImage = R.drawable.cute_giraffe;
                        } else if (i >= 5 && i < 10) {
                            sq1.setImageResource(R.drawable.cute_donkey);
                            correctImage = R.drawable.cute_donkey;
                        } else if (i >= 10 && i < 15) {
                            sq1.setImageResource(R.drawable.cute_elephant);
                            correctImage = R.drawable.cute_elephant;
                        } else if (i >= 15 && i < 20) {
                            sq1.setImageResource(R.drawable.cute_zebra);
                            correctImage = R.drawable.cute_zebra;
                        } else if (i >= 20 && i < 25) {
                            sq1.setImageResource(R.drawable.cute_deer);
                            correctImage = R.drawable.cute_deer;
                        } else if (i >= 25 && i < 30) {
                            sq1.setImageResource(R.drawable.cute_horse);
                            correctImage = R.drawable.cute_horse;
                        } else if (i >= 30 && i < 35) {
                            sq1.setImageResource(R.drawable.cute_snail);
                            correctImage = R.drawable.cute_snail;
                        } else if (i >= 35 && i < 40) {
                            sq1.setImageResource(R.drawable.cute_unicorn);
                            correctImage = R.drawable.cute_unicorn;
                        }

                        String j = String.valueOf(i);

                        if (j.equals("0") || j.equals("8") || j.equals("16") || j.equals("24") || j.equals("34") || j.equals("40")) {
                            sq2.setImageResource(animal7[0]);
                            sq2.setEnabled(true);
                            sq2.setTag(animal7[0]);
                        } else if (j.equals("1") || j.equals("6") || j.equals("17") || j.equals("25") || j.equals("33") || j.equals("41")) {
                            sq2.setImageResource(animal7[1]);
                            sq2.setEnabled(true);
                            sq2.setTag(animal7[1]);
                        } else if (j.equals("2") || j.equals("14") || j.equals("19") || j.equals("26") || j.equals("30") || j.equals("42")) {
                            sq2.setImageResource(animal7[2]);
                            sq2.setEnabled(true);
                            sq2.setTag(animal7[2]);
                        } else if (j.equals("4") || j.equals("11") || j.equals("18") || j.equals("27") || j.equals("35") || j.equals("43")) {
                            sq2.setImageResource(animal7[3]);
                            sq2.setEnabled(true);
                            sq2.setTag(animal7[3]);
                        } else if (j.equals("3") || j.equals("12") || j.equals("20") || j.equals("29") || j.equals("36") || j.equals("44")) {
                            sq2.setImageResource(animal7[4]);
                            sq2.setEnabled(true);
                            sq2.setTag(animal7[4]);
                        } else if (j.equals("5") || j.equals("13") || j.equals("21") || j.equals("28") || j.equals("37") || j.equals("45")) {
                            sq2.setImageResource(animal7[5]);
                            sq2.setEnabled(true);
                            sq2.setTag(animal7[5]);
                        } else if (j.equals("9") || j.equals("15") || j.equals("22") || j.equals("32") || j.equals("39") || j.equals("46")) {
                            sq2.setImageResource(animal7[6]);
                            sq2.setEnabled(true);
                            sq2.setTag(animal7[6]);
                        } else if (j.equals("7") || j.equals("10") || j.equals("23") || j.equals("31") || j.equals("38") || j.equals("47")) {
                            sq2.setImageResource(animal7[7]);
                            sq2.setEnabled(true);
                            sq2.setTag(animal7[7]);
                        }
                        startTime = System.currentTimeMillis();
                        sq2.postDelayed(this, 1500);
                        Log.d("int", String.valueOf(i));
                        duration = duration + 1500;
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
                        meanReactionTime = (int) Math.ceil(totalReactionTime / noOfCorrectResponses); // ms Error = +/- 0.10 ms
                        // 1111.000 -> rounded to floor value 1111
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
                    Intent intent = new Intent(getApplicationContext(), DACompleteScreen.class);
                    finish();
                    mp.pause();
                    startActivity(intent);
                }

            }
        }.run();

        sq2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp2 = MediaPlayer.create(getApplicationContext(), R.raw.button_click);
                mp2.start();
                clickedTime = System.currentTimeMillis();
                reactionTime = ( clickedTime - startTime );
                clickedImage = (int) sq2.getTag();
                if (correctImage == clickedImage) {
                    totalReactionTime = totalReactionTime + reactionTime;
                    Log.d("correct " , startTime + " " + clickedTime + " " + reactionTime);
                    noOfCorrectResponses++;
                    sq2.setEnabled(false);
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
        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_DIVIDED_ATTENTION, params, CODE_POST_REQUEST);
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
                Log.d("######################################################### anyText", s);
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
                "CREATE TABLE IF NOT EXISTS dividedAttention (\n" +
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

        String insertSQL = "INSERT INTO dividedAttention \n" +
                "(childID, totalCorrectResponses, noOfCorrectResponses, noOfCommissionErrors, noOfOmmissionErrors, meanReactionTime, totalDuration)\n" +
                "VALUES \n" +
                "(?, ?, ?, ?, ?, ?, ?);";

        mDatabase.execSQL(insertSQL, new Integer[]{child_ID, total_correct_responses, no_of_correct_responses, no_of_commission_errors, no_of_ommission_errors, mean_reaction_time, total_duration});

        //Toast.makeText(this, "Data Added Successfully", Toast.LENGTH_SHORT).show();
    }

    /*************************************************************************************************/


}