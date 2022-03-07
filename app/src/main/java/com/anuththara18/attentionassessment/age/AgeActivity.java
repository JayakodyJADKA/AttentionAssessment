package com.anuththara18.attentionassessment.age;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anuththara18.attentionassessment.R;
import com.anuththara18.attentionassessment.consentform.ConsentFormActivity;
import com.anuththara18.attentionassessment.consentform.SinhalaConsentFormActivity;
import com.anuththara18.attentionassessment.db.Api;
import com.anuththara18.attentionassessment.db.RequestHandler;
import com.anuththara18.attentionassessment.details.ParentDetailsActivity;
import com.anuththara18.attentionassessment.focused.FocusedAttentionGame1;
import com.anuththara18.attentionassessment.gender.GenderActivity;
import com.anuththara18.attentionassessment.home.NavigationDrawerActivity;
import com.anuththara18.attentionassessment.language.LanguageActivity;
import com.anuththara18.attentionassessment.language.LanguageSetter;
import com.anuththara18.attentionassessment.selective.SelectiveAttentionGame1;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class AgeActivity extends AppCompatActivity {

    ImageButton right_btn, left_btn;
    ImageView bunny_img;
    TextView age_txt, next, previous, selectAge;
    public static int age = 4;

    int width;
    int count;
    float rightFromXDelta, rightToXDelta, leftFromXDelta, leftToXDelta;

    public static final String DATABASE_NAME = "childData";
    SQLiteDatabase mDatabase;

    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

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
        setContentView(R.layout.activity_age);

        right_btn = (ImageButton)findViewById(R.id.right_btn);
        left_btn = (ImageButton)findViewById(R.id.left_btn);
        bunny_img = (ImageView)findViewById(R.id.bunny_img);
        age_txt = (TextView)findViewById(R.id.age_txt);
        next = (TextView)findViewById(R.id.next);
        previous = (TextView)findViewById(R.id.previous);
        selectAge = (TextView)findViewById(R.id.selectAge);

        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);

        next.setText(LanguageSetter.getresources().getString(R.string.next));
        previous.setText(LanguageSetter.getresources().getString(R.string.previous));
        selectAge.setText(LanguageSetter.getresources().getString(R.string.age));

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

        count = 1;

        right_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( count < 4 ) {
                    getDimensions();
                    TranslateAnimation animate = new TranslateAnimation(rightFromXDelta, rightToXDelta, 0, 0);
                    animate.setDuration(500);
                    animate.setFillAfter(true);
                    bunny_img.startAnimation(animate);
                    age_txt.startAnimation(animate);
                    count++;
                    Log.d("tag", String.valueOf(count));
                    setAge();
                }
            }
        });

        left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( count > 1 ) {
                    getDimensions();
                    TranslateAnimation animate = new TranslateAnimation(leftFromXDelta, leftToXDelta, 0, 0);
                    animate.setDuration(500);
                    animate.setFillAfter(true);
                    bunny_img.startAnimation(animate);
                    age_txt.startAnimation(animate);
                    count--;
                    Log.d("tag", String.valueOf(count));
                    setAge();
                }
            }

        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //saveDataToOnlineDB();
                createTable();
                saveDataToLocalDB();
                if ( LanguageActivity.text.equals("English")) {
                    startActivity(new Intent(AgeActivity.this, ConsentFormActivity.class));
                }
                else {
                    startActivity(new Intent(AgeActivity.this, SinhalaConsentFormActivity.class));
                }

                //startActivity(new Intent(AgeActivity.this, NavigationDrawerActivity.class));
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AgeActivity.this, GenderActivity.class);
                startActivity(intent);
            }
        });

    }

    /*************************************************************************************************/

    public void getDimensions() {
        if ( count == 1 ) {
            leftFromXDelta = 0;
            leftToXDelta = 0;
            rightFromXDelta = 0;
            rightToXDelta = width / 5.5f;
        }
        else if ( count == 2 ) {
            rightToXDelta = 2 * (width / 5.5f);
            rightFromXDelta = 1 * (width / 5.5f);
            leftFromXDelta = 1 * (width / 5.5f);
            leftToXDelta = 0;
        }
        else if ( count == 3 ) {
            rightToXDelta = 3 * (width / 5.5f);
            rightFromXDelta = 2 * (width / 5.5f);
            leftFromXDelta = 2 * (width / 5.5f);
            leftToXDelta = 1 * (width / 5.5f);
        }
        else if ( count == 4 ) {
            leftFromXDelta = 3 * (width / 5.5f);
            leftToXDelta = 2 * (width / 5.5f);
        }
    }

    /*************************************************************************************************/

    public void setAge() {
        if ( count == 1 ) {
            age = 4;
            age_txt.setText("4");
            age_txt.setPadding(0, 0, 0,0 );
            Log.d("age", String.valueOf(age));
        }
        else if ( count == 2 ) {
            age = 5;
            age_txt.setText("5");
            age_txt.setPadding(10, 0, 0,0 );
            Log.d("age", String.valueOf(age));
        }
        else if ( count == 3 ) {
            age = 6;
            age_txt.setText("6");
            age_txt.setPadding(15, 0, 0,0 );
            Log.d("age", String.valueOf(age));
        }
        else if ( count == 4 ) {
            age = 7;
            age_txt.setText("7");
            age_txt.setPadding(25, 0, 0,0 );
            Log.d("age", String.valueOf(age));
        }
    }

    /*************************************************************************************************/

    /*
    private void saveDataToOnlineDB() {

        String gender;
        if (GenderActivity.gender == 2) {
            gender = "Male";
        }
        else {
            gender = "Female";
        }
        String age = String.valueOf(AgeActivity.age);
        String name = ParentDetailsActivity.child_name;
        String email = ParentDetailsActivity.parent_email;
        String contact = ParentDetailsActivity.parent_contact;

        HashMap<String, String> params = new HashMap<>();
        params.put("child_gender", gender);
        params.put("child_age", age);
        params.put("child_name", name);
        params.put("parent_email", email);
        params.put("parent_contact", contact);

        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_FOCUSED_ATTENTION, params, CODE_POST_REQUEST);
        request.execute();
    }
    */

    /*************************************************************************************************/

    /*
    //inner class to perform network request extending an AsyncTask
    private class PerformNetworkRequest extends AsyncTask<Void, Void, String> {

        //the url where we need to send the request
        String url;

        //the parameters
        HashMap<String, String> params;

        //the request code to define whether it is a GET or POST
        int requestCode;

        //constructor to initialize values
        PerformNetworkRequest(String url, HashMap<String, String> params, int requestCode) {
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
    */

    /*************************************************************************************************/

    private void createTable() {
        mDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS childData (\n" +
                        "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                        "    child_gender String NOT NULL,\n" +
                        "    child_age String NOT NULL,\n" +
                        "    child_name String ,\n" +
                        "    parent_email String ,\n" +
                        "    parent_contact String \n" +
                        ");"
        );
    }

    /*************************************************************************************************/

    private void saveDataToLocalDB() {

        String gender;
        if (GenderActivity.gender == 2) {
            gender = "Male";
        }
        else {
            gender = "Female";
        }
        String age = String.valueOf(AgeActivity.age);
        String name = ParentDetailsActivity.child_name;
        String email = ParentDetailsActivity.parent_email;
        String contact = ParentDetailsActivity.parent_contact;

        String insertSQL = "INSERT INTO childData \n" +
                "(child_gender, child_age, child_name, parent_email, parent_contact)\n" +
                "VALUES \n" +
                "(?, ?, ?, ?, ?);";

        mDatabase.execSQL(insertSQL, new String[]{gender, age, name, email, contact});

        Toast.makeText(this, "Data Added Successfully", Toast.LENGTH_SHORT).show();
    }

    /*************************************************************************************************/

}