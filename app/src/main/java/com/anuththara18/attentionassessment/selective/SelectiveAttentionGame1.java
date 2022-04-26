package com.anuththara18.attentionassessment.selective;

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
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import com.anuththara18.attentionassessment.map.Map1Activity;
import com.anuththara18.attentionassessment.map.Map2Activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import static com.anuththara18.attentionassessment.map.Map1Activity.comp1;
import static com.anuththara18.attentionassessment.map.Map1Activity.comp2;

public class SelectiveAttentionGame1 extends AppCompatActivity {

    // get reaction time - if image is clicked before 3 seconds
    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    public static final String DATABASE_NAME = "selectiveAttention";
    SQLiteDatabase mDatabase;

    GridView gridView;
    ImageView main_img, cross_btn;
    ImageButton imageButton;
    ArrayList<GridModel> gridModelArrayList;
    GVAdapter adapter;

    public static ArrayList<Integer> correctResponses;
    public static ArrayList<Integer> incorrectResponses;
    public static int columns;
    int rows, noOfObjects;
    int gridSize = 0;
    int num = 0;
    int image = 0;
    String image_name;

    SimpleDateFormat simpleDateFormat;
    Calendar calendar;
    Date startDate, endDate;
    TextView textview;

    // test variables
    int totalCorrectResponses = 0;
    int noOfCorrectResponses = 0;
    int noOfCommissionErrors = 0;
    int noOfOmmissionErrors = 0;
    long completionTime = 0;

    int a, b, c, d, e, f, g, h, ii, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, aa;
    String a1, b1, c1, d1, e1, f1, g1, h1, i1, j1, k1, l1, m1, n1, o1, p1, q1, r1, s1, t1, u1, v1, w1, x1, y1, z1, aa1;

    int age = AgeActivity.age;
    int level = Map2Activity.level;
    Random random;
    long gameEnd;

    MediaPlayer mp;
    int clickCount = 0;

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
        setContentView(R.layout.activity_selective_attention_game1);

        main_img = findViewById(R.id.main_img);
        gridView = findViewById(R.id.gridView);
        imageButton = findViewById(R.id.imageButton);
        cross_btn = (ImageView) findViewById(R.id.cross_btn);
        textview = findViewById(R.id.textview);

        textview.setText(LanguageSetter.getresources().getString(R.string.select));

        mp = MediaPlayer.create(getApplicationContext(), R.raw.selective);
        mp.start();

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.d("#############", "restart");
                mp.start();
            }

        });

        long gameStart = System.currentTimeMillis();
        Log.d("startTime", String.valueOf(gameStart));

        //creating a database
        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);

        random = new Random();
        correctResponses = new ArrayList<>();
        incorrectResponses = new ArrayList<>();

        // level 1 & 2
        a = R.drawable.ladybird;
        a1 = "ladybird";
        b = R.drawable.blue_buterfly;
        b1 = "blue_buterfly";
        c = R.drawable.yellow_bee;
        c1 = "yellow_bee";
        d = R.drawable.green_bird;
        d1 = "green_bird";
        e = R.drawable.pink_flower;
        e1 = "pink_flower";
        f = R.drawable.bear;
        f1 = "bear";
        g = R.drawable.pink_pig;
        g1 = "pink_pig";

        image =  num = random.nextInt(100);

        if ( age >= 4 && age <= 5 ) {
            if (level == 1) {
                // 2 objects
                num = random.nextInt(100);
                if(num % 2 == 0) {
                    main_img.setImageResource(a);
                    image_name = a1;
                }
                else {
                    main_img.setImageResource(b);
                    image_name = b1;
                }
            }
            else if (level == 2) {
                // 3 objects
                num = random.nextInt(120);
                if(num >= 0 && num < 40) {
                    main_img.setImageResource(a);
                    image_name = a1;
                }
                else if(num >= 40 && num < 80) {
                    main_img.setImageResource(b);
                    image_name = b1;
                }
                else if(num >= 80 && num < 120) {
                    main_img.setImageResource(c);
                    image_name = c1;
                }
            }
            else if (level == 3) {
                // 4 objects
                num = random.nextInt(120);
                if(num >= 0 && num < 30) {
                    main_img.setImageResource(a);
                    image_name = a1;
                }
                else if(num >= 30 && num < 60) {
                    main_img.setImageResource(b);
                    image_name = b1;
                }
                else if(num >= 60 && num < 90) {
                    main_img.setImageResource(c);
                    image_name = c1;
                }
                else if(num >= 90 && num < 120) {
                    main_img.setImageResource(d);
                    image_name = d1;
                }
            }
            else if (level == 4) {
                num = random.nextInt(120);
                if(num >= 0 && num < 30) {
                    main_img.setImageResource(a);
                    image_name = a1;
                }
                else if(num >= 30 && num < 60) {
                    main_img.setImageResource(b);
                    image_name = b1;
                }
                else if(num >= 60 && num < 90) {
                    main_img.setImageResource(c);
                    image_name = c1;
                }
                else if(num >= 90 && num < 120) {
                    main_img.setImageResource(d);
                    image_name = d1;
                }
            }
            else if (level == 5) {
                num = random.nextInt(100);
                if(num >= 0 && num < 20) {
                    main_img.setImageResource(a);
                    image_name = a1;
                }
                else if(num >= 20 && num < 40) {
                    main_img.setImageResource(b);
                    image_name = b1;
                }
                else if(num >= 40 && num < 60) {
                    main_img.setImageResource(c);
                    image_name = c1;
                }
                else if(num >= 60 && num < 80) {
                    main_img.setImageResource(d);
                    image_name = d1;
                }
                else if(num >= 80 && num < 100) {
                    main_img.setImageResource(e);
                    image_name = e1;
                }
            }
        }

        if (age >= 6 && age <= 7 ) {
            if (level == 1) {
                // 4 objects
                num = random.nextInt(120);
                if (num >= 0 && num < 30) {
                    main_img.setImageResource(a);
                    image_name = a1;
                }
                else if (num >= 30 && num < 60) {
                    main_img.setImageResource(b);
                    image_name = b1;
                }
                else if (num >= 60 && num < 90) {
                    main_img.setImageResource(c);
                    image_name = c1;
                }
                else if (num >= 90 && num < 120) {
                    main_img.setImageResource(d);
                    image_name = d1;
                }
            } else if (level == 2) {
                // 5 objects
                num = random.nextInt(100);
                if (num >= 0 && num < 20) {
                    main_img.setImageResource(a);
                    image_name = a1;
                }
                else if (num >= 20 && num < 40) {
                    main_img.setImageResource(b);
                    image_name = b1;
                }
                else if (num >= 40 && num < 60) {
                    main_img.setImageResource(c);
                    image_name = c1;
                }
                else if (num >= 60 && num < 80) {
                    main_img.setImageResource(d);
                    image_name = d1;
                }
                else if (num >= 80 && num < 100) {
                    main_img.setImageResource(e);
                    image_name = e1;
                }
            } else if (level == 3) {
                // 6 objects
                num = random.nextInt(120);
                if (num >= 0 && num < 20) {
                    main_img.setImageResource(a);
                    image_name = a1;
                } else if (num >= 20 && num < 40) {
                    main_img.setImageResource(b);
                    image_name = b1;
                } else if (num >= 40 && num < 60) {
                    main_img.setImageResource(c);
                    image_name = c1;
                } else if (num >= 60 && num < 80) {
                    main_img.setImageResource(d);
                    image_name = d1;
                } else if (num >= 80 && num < 100) {
                    main_img.setImageResource(e);
                    image_name = e1;
                } else if (num >= 100 && num < 120) {
                    main_img.setImageResource(f);
                    image_name = f1;
                }
            } else if (level == 4) {
                // 6 objects
                num = random.nextInt(120);
                if (num >= 0 && num < 20) {
                    main_img.setImageResource(a);
                    image_name = a1;
                } else if (num >= 20 && num < 40) {
                    main_img.setImageResource(b);
                    image_name = b1;
                } else if (num >= 40 && num < 60) {
                    main_img.setImageResource(c);
                    image_name = c1;
                } else if (num >= 60 && num < 80) {
                    main_img.setImageResource(d);
                    image_name = d1;
                } else if (num >= 80 && num < 100) {
                    main_img.setImageResource(e);
                    image_name = e1;
                } else if (num >= 100 && num < 120) {
                    main_img.setImageResource(f);
                    image_name = f1;
                }
            } else if (level == 5) {
                // 7 objects
                num = random.nextInt(140);
                if (num >= 0 && num < 20) {
                    main_img.setImageResource(a);
                    image_name = a1;
                } else if (num >= 20 && num < 40) {
                    main_img.setImageResource(b);
                    image_name = b1;
                } else if (num >= 40 && num < 60) {
                    main_img.setImageResource(c);
                    image_name = c1;
                } else if (num >= 60 && num < 80) {
                    main_img.setImageResource(d);
                    image_name = d1;
                } else if (num >= 80 && num < 100) {
                    main_img.setImageResource(e);
                    image_name = e1;
                } else if (num >= 100 && num < 120) {
                    main_img.setImageResource(f);
                    image_name = f1;
                } else if (num >= 120 && num < 140) {
                    main_img.setImageResource(g);
                    image_name = g1;
                }
            }
        }

        getGridSize();


        do  {
            totalCorrectResponses = 0;
            formGrid();
            for ( int i = 0; i < gridSize; i++) {
                GridModel data = (GridModel) gridModelArrayList.get(i);
                if ( data.getImage_name().equals(image_name)){
                    totalCorrectResponses++;
                }
            }
            Log.d("@@@@@@@@@@@@@@@@@@@", String.valueOf(totalCorrectResponses));
        } while (totalCorrectResponses < 6);

        try {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            startDate = simpleDateFormat.parse(String.valueOf(simpleDateFormat.format(calendar.getTime())));
            Log.d("startTime", String.valueOf(simpleDateFormat.format(calendar.getTime())));
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                GridModel data = (GridModel) gridModelArrayList.get(position);
                //Toast.makeText(SelectiveAttentionGame1.this, "" + position + data.getImage_name() + data.getImgid(), Toast.LENGTH_SHORT).show();
                if ( data.getImage_name().equals(image_name) ) {
                    noOfCorrectResponses++;
                    correctResponses.add(position);
                    gridView.setAdapter(adapter);
                }
                else {
                    // incorrect clicks
                    noOfCommissionErrors++;
                    incorrectResponses.add(position);
                    gridView.setAdapter(adapter);
                }
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ( clickCount == 0 ) {
                    try {
                        gameEnd = System.currentTimeMillis();
                        Log.d("endTime", String.valueOf(gameEnd));
                    } catch (Exception parseException) {
                        parseException.printStackTrace();
                    }
                    completionTime = gameEnd - gameStart;
                    // missed clicks
                    noOfOmmissionErrors = totalCorrectResponses - noOfCorrectResponses;
                    Log.d("total", String.valueOf(totalCorrectResponses));
                    Log.d("correctResponses", String.valueOf(noOfCorrectResponses));
                    Log.d("omissionErrors", String.valueOf(totalCorrectResponses - noOfCorrectResponses));
                    Log.d("commissionErrors", String.valueOf(noOfCommissionErrors));
                    Log.d("duration", String.valueOf(completionTime));

                    GVAdapter2 adapter = new GVAdapter2(getApplicationContext(), gridModelArrayList);
                    gridView.setAdapter(adapter);
                    gridView.setEnabled(false);

                    if (Map2Activity.level == 1){
                        Map2Activity.comp1 = 1;
                    }
                    else if (Map2Activity.level == 2){
                        Map2Activity.comp2 = 1;
                        Log.d("***************", "level 2 done");
                    }
                    else if (Map2Activity.level == 3){
                        Map2Activity.comp3 = 1;
                    }
                    else if (Map2Activity.level == 4){
                        Map2Activity.comp4 = 1;
                    }
                    else if (Map2Activity.level == 5){
                        Map2Activity.comp5 = 1;
                    }

                    saveDataToOnlineDB();
                    createTable();
                    saveDataToLocalDB();
                    clickCount++;
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), SelectiveACompleteScreen.class);
                    finish();
                    mp.pause();
                    startActivity(intent);
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

    private void formGrid() {

        gridModelArrayList = new ArrayList<GridModel>();

        for ( int i = 1; i <= gridSize; i++) {
            if ( age >= 4 && age <= 5 ) {
                if (level == 1) {
                    // 2 objects
                    num = random.nextInt(100);
                    if(num % 2 == 0) {
                        gridModelArrayList.add(new GridModel(a1, a));
                    }
                    else {
                        gridModelArrayList.add(new GridModel(b1, b));
                    }
                }
                else if (level == 2) {
                    // 3 objects
                    num = random.nextInt(120);
                    if(num >= 0 && num < 40) {
                        gridModelArrayList.add(new GridModel(a1, a));
                    }
                    else if(num >= 40 && num < 80) {
                        gridModelArrayList.add(new GridModel(b1, b));
                    }
                    else if(num >= 80 && num < 120) {
                        gridModelArrayList.add(new GridModel(c1, c));
                    }
                }
                else if (level == 3) {
                    // 4 objects
                    num = random.nextInt(120);
                    if(num >= 0 && num < 30) {
                        gridModelArrayList.add(new GridModel(a1, a));
                    }
                    else if(num >= 30 && num < 60) {
                        gridModelArrayList.add(new GridModel(b1, b));
                    }
                    else if(num >= 60 && num < 90) {
                        gridModelArrayList.add(new GridModel(c1, c));
                    }
                    else if(num >= 90 && num < 120) {
                        gridModelArrayList.add(new GridModel(d1, d));
                    }
                }
                else if (level == 4) {
                    // 4 objects
                    num = random.nextInt(120);
                    if(num >= 0 && num < 30) {
                        gridModelArrayList.add(new GridModel(a1, a));
                    }
                    else if(num >= 30 && num < 60) {
                        gridModelArrayList.add(new GridModel(b1, b));
                    }
                    else if(num >= 60 && num < 90) {
                        gridModelArrayList.add(new GridModel(c1, c));
                    }
                    else if(num >= 90 && num < 120) {
                        gridModelArrayList.add(new GridModel(d1, d));
                    }
                }
                else if (level == 5) {
                    // 5 objects
                    num = random.nextInt(100);
                    if(num >= 0 && num < 20) {
                        gridModelArrayList.add(new GridModel(a1, a));
                    }
                    else if(num >= 20 && num < 40) {
                        gridModelArrayList.add(new GridModel(b1, b));
                    }
                    else if(num >= 40 && num < 60) {
                        gridModelArrayList.add(new GridModel(c1, c));
                    }
                    else if(num >= 60 && num < 80) {
                        gridModelArrayList.add(new GridModel(d1, d));
                    }
                    else if(num >= 80 && num < 100) {
                        gridModelArrayList.add(new GridModel(e1, e));
                    }
                }
            }

            if (age >= 6 && age <= 7 ){
                if (level == 1) {
                    // 4 objects
                    num = random.nextInt(120);
                    if(num >= 0 && num < 30) {
                        gridModelArrayList.add(new GridModel(a1, a));
                    }
                    else if(num >= 30 && num < 60) {
                        gridModelArrayList.add(new GridModel(b1, b));
                    }
                    else if(num >= 60 && num < 90) {
                        gridModelArrayList.add(new GridModel(c1, c));
                    }
                    else if(num >= 90 && num < 120) {
                        gridModelArrayList.add(new GridModel(d1, d));
                    }
                }
                else if (level == 2) {
                    // 5 objects
                    num = random.nextInt(100);
                    if(num >= 0 && num < 20) {
                        gridModelArrayList.add(new GridModel(a1, a));
                    }
                    else if(num >= 20 && num < 40) {
                        gridModelArrayList.add(new GridModel(b1, b));
                    }
                    else if(num >= 40 && num < 60) {
                        gridModelArrayList.add(new GridModel(c1, c));
                    }
                    else if(num >= 60 && num < 80) {
                        gridModelArrayList.add(new GridModel(d1, d));
                    }
                    else if(num >= 80 && num < 100) {
                        gridModelArrayList.add(new GridModel(e1, e));
                    }
                }
                else if (level == 3) {
                    // 6 objects
                    num = random.nextInt(120);
                    if(num >= 0 && num < 20) {
                        gridModelArrayList.add(new GridModel(a1, a));
                    }
                    else if(num >= 20 && num < 40) {
                        gridModelArrayList.add(new GridModel(b1, b));
                    }
                    else if(num >= 40 && num < 60) {
                        gridModelArrayList.add(new GridModel(c1, c));
                    }
                    else if(num >= 60 && num < 80) {
                        gridModelArrayList.add(new GridModel(d1, d));
                    }
                    else if(num >= 80 && num < 100) {
                        gridModelArrayList.add(new GridModel(e1, e));
                    }
                    else if(num >= 100 && num < 120) {
                        gridModelArrayList.add(new GridModel(f1, f));
                    }
                }
                else if (level == 4) {
                    // 6 objects
                    num = random.nextInt(120);
                    if(num >= 0 && num < 20) {
                        gridModelArrayList.add(new GridModel(a1, a));
                    }
                    else if(num >= 20 && num < 40) {
                        gridModelArrayList.add(new GridModel(b1, b));
                    }
                    else if(num >= 40 && num < 60) {
                        gridModelArrayList.add(new GridModel(c1, c));
                    }
                    else if(num >= 60 && num < 80) {
                        gridModelArrayList.add(new GridModel(d1, d));
                    }
                    else if(num >= 80 && num < 100) {
                        gridModelArrayList.add(new GridModel(e1, e));
                    }
                    else if(num >= 100 && num < 120) {
                        gridModelArrayList.add(new GridModel(f1, f));
                    }
                }
                else if (level == 5) {
                    // 7 objects
                    num = random.nextInt(140);
                    if(num >= 0 && num < 20) {
                        gridModelArrayList.add(new GridModel(a1, a));
                    }
                    else if(num >= 20 && num < 40) {
                        gridModelArrayList.add(new GridModel(b1, b));
                    }
                    else if(num >= 40 && num < 60) {
                        gridModelArrayList.add(new GridModel(c1, c));
                    }
                    else if(num >= 60 && num < 80) {
                        gridModelArrayList.add(new GridModel(d1, d));
                    }
                    else if(num >= 80 && num < 100) {
                        gridModelArrayList.add(new GridModel(e1, e));
                    }
                    else if(num >= 100 && num < 120) {
                        gridModelArrayList.add(new GridModel(f1, f));
                    }
                    else if(num >= 120 && num < 140) {
                        gridModelArrayList.add(new GridModel(g1, g));
                    }
                }
            }

        }

        adapter = new GVAdapter(this, gridModelArrayList);
        gridView.setAdapter(adapter);

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

        String child = String.valueOf(GenderActivity.gender) + String.valueOf(AgeActivity.age) + String.valueOf(Map2Activity.level);
        int child_ID = Integer.parseInt(child);
        int total_correct_responses = totalCorrectResponses;
        int no_of_correct_responses = noOfCorrectResponses;
        int no_of_ommission_errors = noOfOmmissionErrors;
        int no_of_commission_errors = noOfCommissionErrors;
        int mean_reaction_time = 0;
        int total_duration = (int) completionTime;

        HashMap<String, Integer> params = new HashMap<>();
        params.put("childID", child_ID);
        params.put("totalCorrectResponses", total_correct_responses);
        params.put("noOfCorrectResponses", no_of_correct_responses);
        params.put("noOfCommissionErrors", no_of_commission_errors);
        params.put("noOfOmmissionErrors", no_of_ommission_errors);
        params.put("meanReactionTime", mean_reaction_time);
        params.put("totalDuration", total_duration);

        //Calling the create hero API
        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_SELECTIVE_ATTENTION, params, CODE_POST_REQUEST);
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
                "CREATE TABLE IF NOT EXISTS selectiveAttention (\n" +
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

        String child = String.valueOf(GenderActivity.gender) + String.valueOf(AgeActivity.age) + String.valueOf(Map2Activity.level);
        int child_ID = Integer.parseInt(child);
        int total_correct_responses = totalCorrectResponses;
        int no_of_correct_responses = noOfCorrectResponses;
        int no_of_ommission_errors = noOfOmmissionErrors;
        int no_of_commission_errors = noOfCommissionErrors;
        int mean_reaction_time = 0;
        int total_duration = (int) completionTime;

        String insertSQL = "INSERT INTO selectiveAttention \n" +
                "(childID, totalCorrectResponses, noOfCorrectResponses, noOfCommissionErrors, noOfOmmissionErrors, meanReactionTime, totalDuration)\n" +
                "VALUES \n" +
                "(?, ?, ?, ?, ?, ?, ?);";

        mDatabase.execSQL(insertSQL, new Integer[]{child_ID, total_correct_responses, no_of_correct_responses, no_of_commission_errors, no_of_ommission_errors, mean_reaction_time, total_duration});

        //Toast.makeText(this, "Data Added Successfully", Toast.LENGTH_SHORT).show();
    }

    /*************************************************************************************************/


    public void getGridSize(){
        // ages 4 & 5
        if (age >= 4 && age <= 5 && level == 1) {
            columns = 3;
            rows = 3;
            gridSize = 9;
            gridView.setNumColumns(3);
            gridView.setHorizontalSpacing(-200);
            gridView.setVerticalSpacing(40);
            noOfObjects = 2;
        }
        else if (age >= 4 && age <= 5 && level == 2) {
            columns = 3;
            rows = 4;
            gridSize = 12;
            gridView.setNumColumns(3);
            gridView.setHorizontalSpacing(-240);
            gridView.setVerticalSpacing(30);
            noOfObjects = 3;
        }
        else if (age >= 4 && age <= 5 && level == 3) {
            columns = 4;
            rows = 4;
            gridSize = 16;
            gridView.setNumColumns(4);
            gridView.setHorizontalSpacing(-290);
            noOfObjects = 4;
        }
        else if (age >= 4 && age <= 5 && level == 4) {
            columns = 4;
            rows = 5;
            gridSize = 20;
            gridView.setNumColumns(4);
            gridView.setHorizontalSpacing(-220);
            noOfObjects = 5;
        }
        else if (age >= 4 && age <= 5 && level == 5) {
            columns = 5;
            rows = 5;
            gridSize = 25;
            gridView.setNumColumns(5);
            gridView.setHorizontalSpacing(-220);
            noOfObjects = 5;
        }

        // ages 6 & 7
        if (age >= 6 && age <= 7 && level == 1) {
            columns = 3;
            rows = 4;
            gridSize = 12;
            gridView.setNumColumns(3);
            gridView.setHorizontalSpacing(-240);
            gridView.setVerticalSpacing(30);
            noOfObjects = 4;
        }
        else if (age >= 6 && age <= 7 && level == 2) {
            columns = 4;
            rows = 4;
            gridSize = 16;
            gridView.setNumColumns(4);
            gridView.setHorizontalSpacing(-290);
            noOfObjects = 5;
        }
        else if (age >= 6 && age <= 7 && level == 3) {
            columns = 4;
            rows = 5;
            gridSize = 20;
            gridView.setNumColumns(4);
            gridView.setHorizontalSpacing(-220);
            noOfObjects = 6;
        }
        else if (age >= 6 && age <= 7 && level == 4) {
            columns = 5;
            rows = 5;
            gridSize = 25;
            gridView.setNumColumns(5);
            gridView.setHorizontalSpacing(-220);
            noOfObjects = 6;
        }
        else if (age >= 6 && age <= 7 && level == 5) {
            columns = 5;
            rows = 6;
            gridSize = 30;
            gridView.setNumColumns(5);
            gridView.setHorizontalSpacing(-220);
            noOfObjects = 7;
        }
        /*
        columns = 5;
        rows = 6;
        gridSize = 30;
        gridView.setNumColumns(5);
        gridView.setHorizontalSpacing(-220);
        noOfObjects = 7; // 4 same coloured
        */
    }

}

/*
        if ( age >= 4 && age <= 5 ) {
            if (level == 1) {
                // 2 objects
                num = random.nextInt(100);
                if(num % 2 == 0) {
                    main_img.setImageResource(a);
                    image_name = a1;
                }
                else {
                    main_img.setImageResource(b);
                    image_name = b1;
                }
            }
            else if (level == 2) {
                // 3 objects
                num = random.nextInt(120);
                if(num >= 0 && num < 40) {
                    main_img.setImageResource(c);
                }
                else if(num >= 40 && num < 80) {
                    main_img.setImageResource(d);
                }
                else if(num >= 80 && num < 120) {
                    main_img.setImageResource(e);
                }
            }
            else if (level == 3) {
                // 4 objects
                num = random.nextInt(120);
                if(num >= 0 && num < 30) {
                    main_img.setImageResource(f);
                }
                else if(num >= 30 && num < 60) {
                    main_img.setImageResource(g);
                }
                else if(num >= 60 && num < 90) {
                    main_img.setImageResource(h);
                }
                else if(num >= 90 && num < 120) {
                    main_img.setImageResource(j);
                }
            }
            else if (level == 4) {
                // 4 objects - 2 same coloured
                num = random.nextInt(120);
                if(num >= 0 && num < 30) {
                    main_img.setImageResource(l);
                }
                else if(num >= 30 && num < 60) {
                    main_img.setImageResource(m);
                }
                else if(num >= 60 && num < 90) {
                    main_img.setImageResource(p);
                }
                else if(num >= 90 && num < 120) {
                    main_img.setImageResource(q);
                }
            }
            else if (level == 5) {
                // 5 objects - 2 same coloured
                num = random.nextInt(100);
                if(num >= 0 && num < 20) {
                    main_img.setImageResource(y);
                }
                else if(num >= 20 && num < 40) {
                    main_img.setImageResource(z);
                }
                else if(num >= 40 && num < 60) {
                    main_img.setImageResource(t);
                }
                else if(num >= 60 && num < 80) {
                    main_img.setImageResource(w);
                }
                else if(num >= 80 && num < 100) {
                    main_img.setImageResource(x);
                }
            }
        }

        if (age >= 6 && age <= 7 ){
            if (level == 1) {
                // 4 objects
                num = random.nextInt(120);
                if(num >= 0 && num < 30) {
                    main_img.setImageResource(a);
                }
                else if(num >= 30 && num < 60) {
                    main_img.setImageResource(b);
                }
                else if(num >= 60 && num < 90) {
                    main_img.setImageResource(c);
                }
                else if(num >= 90 && num < 120) {
                    main_img.setImageResource(d);
                }
            }
            else if (level == 2) {
                // 5 objects
                num = random.nextInt(100);
                if(num >= 0 && num < 20) {
                    main_img.setImageResource(a);
                }
                else if(num >= 20 && num < 40) {
                    main_img.setImageResource(b);
                }
                else if(num >= 40 && num < 60) {
                    main_img.setImageResource(c);
                }
                else if(num >= 60 && num < 80) {
                    main_img.setImageResource(d);
                }
                else if(num >= 80 && num < 100) {
                    main_img.setImageResource(e);
                }
            }
            else if (level == 3) {
                // 6 objects - 2 same coloured
                num = random.nextInt(120);
                if(num >= 0 && num < 20) {
                    main_img.setImageResource(f);
                }
                else if(num >= 20 && num < 40) {
                    main_img.setImageResource(g);
                }
                else if(num >= 40 && num < 60) {
                    main_img.setImageResource(h);
                }
                else if(num >= 60 && num < 80) {
                    main_img.setImageResource(ii);
                }
                else if(num >= 80 && num < 100) {
                    main_img.setImageResource(j);
                }
                else if(num >= 100 && num < 120) {
                    main_img.setImageResource(k);
                }
            }
            else if (level == 4) {
                // 6 objects - 3 same coloured
                num = random.nextInt(120);
                if(num >= 0 && num < 20) {
                    main_img.setImageResource(l);
                }
                else if(num >= 20 && num < 40) {
                    main_img.setImageResource(m);
                }
                else if(num >= 40 && num < 60) {
                    main_img.setImageResource(n);
                }
                else if(num >= 60 && num < 80) {
                    main_img.setImageResource(o);
                }
                else if(num >= 80 && num < 100) {
                    main_img.setImageResource(p);
                }
                else if(num >= 100 && num < 120) {
                    main_img.setImageResource(q);
                }
            }
            else if (level == 5) {
                // 7 objects - 4 same coloured
                num = random.nextInt(140);
                if(num >= 0 && num < 20) {
                    main_img.setImageResource(r);
                }
                else if(num >= 20 && num < 40) {
                    main_img.setImageResource(s);
                }
                else if(num >= 40 && num < 60) {
                    main_img.setImageResource(t);
                }
                else if(num >= 60 && num < 80) {
                    main_img.setImageResource(u);
                }
                else if(num >= 80 && num < 100) {
                    main_img.setImageResource(v);
                }
                else if(num >= 100 && num < 120) {
                    main_img.setImageResource(w);
                }
                else if(num >= 120 && num < 140) {
                    main_img.setImageResource(x);
                }
            }
        } */

/*
            if ( age >= 4 && age <= 5 ) {
                if (level == 1) {
                    // 2 objects
                    num = random.nextInt(100);
                    if(num % 2 == 0) {
                        gridModelArrayList.add(new GridModel(a1, a));
                    }
                    else {
                        gridModelArrayList.add(new GridModel(b1, b));
                    }
                }
                else if (level == 2) {
                    // 3 objects
                    num = random.nextInt(120);
                    if(num >= 0 && num < 40) {
                        gridModelArrayList.add(new GridModel(c1, c));
                    }
                    else if(num >= 40 && num < 80) {
                        gridModelArrayList.add(new GridModel(d1, d));
                    }
                    else if(num >= 80 && num < 120) {
                        gridModelArrayList.add(new GridModel(e1, e));
                    }
                }
                else if (level == 3) {
                    // 4 objects
                    num = random.nextInt(120);
                    if(num >= 0 && num < 30) {
                        gridModelArrayList.add(new GridModel(f1, f));
                    }
                    else if(num >= 30 && num < 60) {
                        gridModelArrayList.add(new GridModel(g1, g));
                    }
                    else if(num >= 60 && num < 90) {
                        gridModelArrayList.add(new GridModel(h1, h));
                    }
                    else if(num >= 90 && num < 120) {
                        gridModelArrayList.add(new GridModel(j1, j));
                    }
                }
                else if (level == 4) {
                    // 4 objects - 2 same coloured
                    num = random.nextInt(120);
                    if(num >= 0 && num < 30) {
                        gridModelArrayList.add(new GridModel(l1, l));
                    }
                    else if(num >= 30 && num < 60) {
                        gridModelArrayList.add(new GridModel(m1, m));
                    }
                    else if(num >= 60 && num < 90) {
                        gridModelArrayList.add(new GridModel(p1, p));
                    }
                    else if(num >= 90 && num < 120) {
                        gridModelArrayList.add(new GridModel(q1, q));
                    }
                }
                else if (level == 5) {
                    // 5 objects - 2 same coloured
                    num = random.nextInt(100);
                    if(num >= 0 && num < 20) {
                        gridModelArrayList.add(new GridModel(y1, y));
                    }
                    else if(num >= 20 && num < 40) {
                        gridModelArrayList.add(new GridModel(z1, z));
                    }
                    else if(num >= 40 && num < 60) {
                        gridModelArrayList.add(new GridModel(t1, t));
                    }
                    else if(num >= 60 && num < 80) {
                        gridModelArrayList.add(new GridModel(w1, w));
                    }
                    else if(num >= 80 && num < 100) {
                        gridModelArrayList.add(new GridModel(x1, x));
                    }
                }
            }

            if (age >= 6 && age <= 7 ){
                if (level == 1) {
                    // 4 objects
                    num = random.nextInt(120);
                    if(num >= 0 && num < 30) {
                        gridModelArrayList.add(new GridModel(a1, a));
                    }
                    else if(num >= 30 && num < 60) {
                        gridModelArrayList.add(new GridModel(b1, b));
                    }
                    else if(num >= 60 && num < 90) {
                        gridModelArrayList.add(new GridModel(c1, c));
                    }
                    else if(num >= 90 && num < 120) {
                        gridModelArrayList.add(new GridModel(d1, d));
                    }
                }
                else if (level == 2) {
                    // 5 objects
                    num = random.nextInt(100);
                    if(num >= 0 && num < 20) {
                        gridModelArrayList.add(new GridModel(a1, a));
                    }
                    else if(num >= 20 && num < 40) {
                        gridModelArrayList.add(new GridModel(b1, b));
                    }
                    else if(num >= 40 && num < 60) {
                        gridModelArrayList.add(new GridModel(c1, c));
                    }
                    else if(num >= 60 && num < 80) {
                        gridModelArrayList.add(new GridModel(d1, d));
                    }
                    else if(num >= 80 && num < 100) {
                        gridModelArrayList.add(new GridModel(e1, e));
                    }
                }
                else if (level == 3) {
                    // 6 objects - 2 same coloured
                    num = random.nextInt(120);
                    if(num >= 0 && num < 20) {
                        gridModelArrayList.add(new GridModel(f1, f));
                    }
                    else if(num >= 20 && num < 40) {
                        gridModelArrayList.add(new GridModel(g1, g));
                    }
                    else if(num >= 40 && num < 60) {
                        gridModelArrayList.add(new GridModel(h1, h));
                    }
                    else if(num >= 60 && num < 80) {
                        gridModelArrayList.add(new GridModel(i1, ii));
                    }
                    else if(num >= 80 && num < 100) {
                        gridModelArrayList.add(new GridModel(j1, j));
                    }
                    else if(num >= 100 && num < 120) {
                        gridModelArrayList.add(new GridModel(k1, k));
                    }
                }
                else if (level == 4) {
                    // 6 objects - 3 same coloured
                    num = random.nextInt(120);
                    if(num >= 0 && num < 20) {
                        gridModelArrayList.add(new GridModel(l1, l));
                    }
                    else if(num >= 20 && num < 40) {
                        gridModelArrayList.add(new GridModel(m1, m));
                    }
                    else if(num >= 40 && num < 60) {
                        gridModelArrayList.add(new GridModel(n1, n));
                    }
                    else if(num >= 60 && num < 80) {
                        gridModelArrayList.add(new GridModel(o1, o));
                    }
                    else if(num >= 80 && num < 100) {
                        gridModelArrayList.add(new GridModel(p1, p));
                    }
                    else if(num >= 100 && num < 120) {
                        gridModelArrayList.add(new GridModel(q1, q));
                    }
                }
                else if (level == 5) {
                    // 7 objects - 4 same coloured
                    num = random.nextInt(140);
                    if(num >= 0 && num < 20) {
                        gridModelArrayList.add(new GridModel(r1, r));
                    }
                    else if(num >= 20 && num < 40) {
                        gridModelArrayList.add(new GridModel(s1, s));
                    }
                    else if(num >= 40 && num < 60) {
                        gridModelArrayList.add(new GridModel(t1, t));
                    }
                    else if(num >= 60 && num < 80) {
                        gridModelArrayList.add(new GridModel(u1, u));
                    }
                    else if(num >= 80 && num < 100) {
                        gridModelArrayList.add(new GridModel(v1, v));
                    }
                    else if(num >= 100 && num < 120) {
                        gridModelArrayList.add(new GridModel(w1, w));
                    }
                    else if(num >= 120 && num < 140) {
                        gridModelArrayList.add(new GridModel(x1, x));
                    }
                }
            }*/