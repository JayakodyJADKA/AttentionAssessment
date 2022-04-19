package com.anuththara18.attentionassessment.selective;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.anuththara18.attentionassessment.BuildConfig;
import com.anuththara18.attentionassessment.R;
import com.anuththara18.attentionassessment.age.AgeActivity;
import com.anuththara18.attentionassessment.consentform.ParentsConsentDatabaseHelper;
import com.anuththara18.attentionassessment.details.ParentDetailsActivity;
import com.anuththara18.attentionassessment.gender.GenderActivity;
import com.anuththara18.attentionassessment.home.NavigationDrawerActivity;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.MANAGE_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class SelectiveACompleteScreen extends AppCompatActivity {

    ImageView complete;

    List<Selective> dataList;
    SQLiteDatabase mDatabase;

    // declaring width and height
    // for our PDF file.
    int pageHeight = 1520;
    int pagewidth = 792;

    // creating a bitmap variable
    // for storing our images
    Bitmap bmp, bmp2, scaledbmp, scaledbmp2;
    public static ParentsConsentDatabaseHelper sqLiteHelper;

    // constant code for runtime permissions
    private static final int PERMISSION_REQUEST_CODE = 200;


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
        setContentView(R.layout.activity_complete_screen2);

        sqLiteHelper = new ParentsConsentDatabaseHelper(this, "ParentsConsent.sqlite", null, 1);

        complete = findViewById(R.id.complete);

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                finish();
                startActivity(intent);
            }
        });

        /*******************************************************************************************/

        try {
            Cursor cursor = sqLiteHelper.getData("SELECT * FROM ParentsConsent");

            while (cursor.moveToNext()) {
                byte[] image = cursor.getBlob(0);
                bmp2 = BitmapFactory.decodeByteArray(image, 0, image.length);
                //fingerprintImageView.setImageBitmap(bmp);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // initializing our variables.
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.selective);
        scaledbmp = Bitmap.createScaledBitmap(bmp, 140, 140, false);
        //scaledbmp2 = Bitmap.createScaledBitmap(bmp2, 200, 200, false);
        scaledbmp2 = BITMAP_RESIZER(bmp2, 400, 400);

        // below code is used for
        // checking our permissions.
        if (checkPermission()) {
            //Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        } else {
            requestPermission();
        }

        /*******************************************************************************************/

        dataList = new ArrayList<>();

        //opening the database
        mDatabase = openOrCreateDatabase(SelectiveAttentionGame1.DATABASE_NAME, MODE_PRIVATE, null);

        /*******************************************************************************************/

        //this part will display the data in the list

        //we used rawQuery(sql, selectionargs) for fetching all the employees
        Cursor cursorEmployees = mDatabase.rawQuery("SELECT * FROM selectiveAttention", null);

        //if the cursor has some data
        if (cursorEmployees.moveToFirst()) {
            //looping through all the records
            do {
                //pushing each record in the employee list
                dataList.add(new Selective(
                        cursorEmployees.getInt(0),
                        cursorEmployees.getInt(1),
                        cursorEmployees.getInt(2),
                        cursorEmployees.getInt(3),
                        cursorEmployees.getInt(4),
                        cursorEmployees.getInt(5),
                        cursorEmployees.getInt(6),
                        cursorEmployees.getInt(7)
                ));
            } while (cursorEmployees.moveToNext());
        }
        //closing the cursor
        cursorEmployees.close();

        /*******************************************************************************************/

        String csv = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/SelectiveAttention.csv"); // Here csv file name is MyCsvFile.csv

        // csv will create inside phone storage.
        CSVWriter writer = null;
        try {
            writer = new CSVWriter(new FileWriter(csv));

            String[] entries = {"id","child_gender","child_age","level","total_correct_responses","correct_responses","commission_errors","omission_errors","mean_reaction_time","total_duration","diagnosis"};
            writer.writeNext(entries);

            List<String[]> data = new ArrayList<String[]>();

            for (int i = 0; i < dataList.size(); i++) {

                Selective gameData = dataList.get(i);
                data.add(new String[]{ String.valueOf(gameData.getId()),
                        String.valueOf(String.valueOf(gameData.getChildID()).charAt(0)),
                        String.valueOf(String.valueOf(gameData.getChildID()).charAt(1)),
                        String.valueOf(String.valueOf(gameData.getChildID()).charAt(2)),
                        String.valueOf(gameData.getTotalCorrectResponses()),
                        String.valueOf(gameData.getNoOfCorrectResponses()),
                        String.valueOf(gameData.getNoOfCommissionErrors()),
                        String.valueOf(gameData.getNoOfOmmissionErrors()),
                        String.valueOf(gameData.getMeanReactionTime()),
                        String.valueOf(gameData.getTotalDuration()),
                        String.valueOf(ParentDetailsActivity.diagnosis)
                });
            }

            writer.writeAll(data); // data is adding to csv

            writer.close();
            //callRead();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*******************************************************************************************/

        PdfDocument pdfDocument = new PdfDocument();

        Paint paint = new Paint();
        Paint title = new Paint();

        PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(pagewidth, pageHeight, 1).create();

        PdfDocument.Page myPage = pdfDocument.startPage(mypageInfo);

        Canvas canvas = myPage.getCanvas();

        canvas.drawBitmap(scaledbmp, 56, 40, paint);
        canvas.drawBitmap(scaledbmp2, 400, 0, paint);

        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        title.setTextSize(20);
        title.setColor(ContextCompat.getColor(this, R.color.orange));

        canvas.drawText("Selective Attention", 250, 80, title);

        title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        title.setColor(ContextCompat.getColor(this, R.color.black));
        title.setTextSize(15);

        canvas.drawText("Age : " + AgeActivity.age, 150, 250, title);

        String gender;
        if (GenderActivity.gender == 2) {
            gender = "Male";
        }
        else {
            gender = "Female";
        }
        canvas.drawText("Gender: " + gender, 150, 300, title);
        canvas.drawText("Child Name: " + ParentDetailsActivity.child_name, 150, 350, title);
        canvas.drawText("Contact No: " + ParentDetailsActivity.parent_contact, 150, 450, title);
        canvas.drawText("Email: " + ParentDetailsActivity.parent_email, 150, 500, title);

        title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        title.setColor(ContextCompat.getColor(this, R.color.black));
        title.setTextSize(15);

        //title.setTextAlign(Paint.Align.CENTER);

        int space = 0;
        // Printing elements one by one
        for (int i = 0; i < dataList.size(); i++) {
            //getting employee of the specified position
            Selective data = dataList.get(i);

            Log.d("********************************", "ID : " + String.valueOf(data.getId()) + ", \t" +
                    "C: " + String.valueOf(data.getChildID()) + ", \t" +
                    "TCR: " + String.valueOf(data.getTotalCorrectResponses()) + ", \t" +
                    "CR: " + String.valueOf(data.getNoOfCorrectResponses()) + ", \t" +
                    "CE: " + String.valueOf(data.getNoOfCommissionErrors()) + ", \t" +
                    "OE: " + String.valueOf(data.getNoOfOmmissionErrors()) + ", \t" +
                    "MRT: " + String.valueOf(data.getMeanReactionTime()) + ", \t" +
                    "TD: " + String.valueOf(data.getTotalDuration()) );

            canvas.drawText("ID : " + String.valueOf(data.getId()) + ", \t" +
                    "C: " + String.valueOf(data.getChildID()) + ", \t" +
                    "TCR: " + String.valueOf(data.getTotalCorrectResponses()) + ", \t" +
                    "CR: " + String.valueOf(data.getNoOfCorrectResponses()) + ", \t" +
                    "CE: " + String.valueOf(data.getNoOfCommissionErrors()) + ", \t" +
                    "OE: " + String.valueOf(data.getNoOfOmmissionErrors()) + ", \t" +
                    "MRT: " + String.valueOf(data.getMeanReactionTime()) + ", \t" +
                    "TD: " + String.valueOf(data.getTotalDuration()) + " ", 150, 550 + space, title);

            space = space + 50;
        }

        pdfDocument.finishPage(myPage);

        File file = new File(Environment.getExternalStorageDirectory(), "SelectiveAttention.pdf");

        try {
            pdfDocument.writeTo(new FileOutputStream(file));
            //Toast.makeText(getApplicationContext(), "PDF file generated successfully.", Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();
        }
        pdfDocument.close();

    }


    /**************************************************************************************************/

    private boolean checkPermission() {
        // checking of permissions.
        int permission1 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int permission2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        int permission3 = ContextCompat.checkSelfPermission(getApplicationContext(), MANAGE_EXTERNAL_STORAGE);
        return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED && permission3 == PackageManager.PERMISSION_GRANTED;
    }

    /**************************************************************************************************/

    private void requestPermission() {
        // requesting permissions if not provided.
        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE, MANAGE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {

                // after requesting permissions we are showing
                // users a toast message of permission granted.
                boolean writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if (writeStorage && readStorage) {
                    //Toast.makeText(this, "Permission Granted..", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission Denied.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }

    /**************************************************************************************************/

    public Bitmap BITMAP_RESIZER(Bitmap bitmap,int newWidth,int newHeight) {
        Bitmap scaledBitmap = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);

        float ratioX = newWidth / (float) bitmap.getWidth();
        float ratioY = newHeight / (float) bitmap.getHeight();
        float middleX = newWidth / 2.0f;
        float middleY = newHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bitmap, middleX - bitmap.getWidth() / 2, middleY - bitmap.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

        return scaledBitmap;

    }
}
