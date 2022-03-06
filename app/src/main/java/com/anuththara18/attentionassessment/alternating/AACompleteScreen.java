package com.anuththara18.attentionassessment.alternating;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
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

import com.anuththara18.attentionassessment.R;
import com.anuththara18.attentionassessment.age.AgeActivity;
import com.anuththara18.attentionassessment.gender.GenderActivity;
import com.anuththara18.attentionassessment.home.NavigationDrawerActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.MANAGE_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

;

public class AACompleteScreen extends AppCompatActivity {

    ImageView complete;

    List<Alternating> dataList;
    SQLiteDatabase mDatabase;

    // declaring width and height
    // for our PDF file.
    int pageHeight = 1520;
    int pagewidth = 792;

    // creating a bitmap variable
    // for storing our images
    Bitmap bmp, scaledbmp;

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

        complete = findViewById(R.id.complete);

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                finish();
                startActivity(intent);
            }
        });

        dataList = new ArrayList<>();

        //opening the database
        mDatabase = openOrCreateDatabase(AlternatingAttentionGame1.DATABASE_NAME, MODE_PRIVATE, null);

        /*******************************************************************************************/

        // initializing our variables.
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.focused);
        scaledbmp = Bitmap.createScaledBitmap(bmp, 140, 140, false);

        // below code is used for
        // checking our permissions.
        if (checkPermission()) {
            //Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        } else {
            requestPermission();
        }

        /*******************************************************************************************/

        //this part will display the data in the list

        //we used rawQuery(sql, selectionargs) for fetching all the employees
        Cursor cursorEmployees = mDatabase.rawQuery("SELECT * FROM alternatingAttention", null);

        //if the cursor has some data
        if (cursorEmployees.moveToFirst()) {
            //looping through all the records
            do {
                //pushing each record in the employee list
                dataList.add(new Alternating(
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

        // generate our PDF file.
        // creating an object variable
        // for our PDF document.
        PdfDocument pdfDocument = new PdfDocument();

        // two variables for paint "paint" is used
        // for drawing shapes and we will use "title"
        // for adding text in our PDF file.
        Paint paint = new Paint();
        Paint title = new Paint();

        // we are adding page info to our PDF file
        // in which we will be passing our pageWidth,
        // pageHeight and number of pages and after that
        // we are calling it to create our PDF.
        PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(pagewidth, pageHeight, 1).create();

        // below line is used for setting
        // start page for our PDF file.
        PdfDocument.Page myPage = pdfDocument.startPage(mypageInfo);

        // creating a variable for canvas
        // from our page of PDF.
        Canvas canvas = myPage.getCanvas();

        // below line is used to draw our image on our PDF file.
        // the first parameter of our drawbitmap method is
        // our bitmap
        // second parameter is position from left
        // third parameter is position from top and last
        // one is our variable for paint.
        canvas.drawBitmap(scaledbmp, 56, 40, paint);

        // below line is used for adding typeface for
        // our text which we will be adding in our PDF file.
        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        // below line is used for setting text size
        // which we will be displaying in our PDF file.
        title.setTextSize(20);

        // below line is sued for setting color
        // of our text inside our PDF file.
        title.setColor(ContextCompat.getColor(this, R.color.purple));

        // below line is used to draw text in our PDF file.
        // the first parameter is our text, second parameter
        // is position from start, third parameter is position from top
        // and then we are passing our variable of paint which is title.
        canvas.drawText("Alternating Attention", 250, 80, title);


        title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        title.setColor(ContextCompat.getColor(this, R.color.black));
        title.setTextSize(15);

        canvas.drawText("Age : " + AgeActivity.age, 150, 250, title);

        String gender;
        if (GenderActivity.gender == 0) {
            gender = "Male";
        }
        else {
            gender = "Female";
        }
        canvas.drawText("Gender: " + gender, 150, 300, title);
        /*
        canvas.drawText("Child Name: " + gender, 150, 350, title);
        canvas.drawText("Parent Name: " + gender, 150, 400, title);
        canvas.drawText("Contact No: " + gender, 150, 450, title);
        canvas.drawText("Email: " + gender, 150, 500, title);

         */

        // similarly we are creating another text and in this
        // we are aligning this text to center of our PDF file.
        title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        title.setColor(ContextCompat.getColor(this, R.color.black));
        title.setTextSize(15);

        // below line is used for setting
        // our text to center of PDF.
        //title.setTextAlign(Paint.Align.CENTER);

        int space = 0;
        // Printing elements one by one
        for (int i = 0; i < dataList.size(); i++) {
            //getting employee of the specified position
            Alternating data = dataList.get(i);

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
                    "TD: " + String.valueOf(data.getTotalDuration()) + " ", 150, 350 + space, title);

            space = space + 50;
        }

        // after adding all attributes to our
        // PDF file we will be finishing our page.
        pdfDocument.finishPage(myPage);

        // below line is used to set the name of
        // our PDF file and its path.
        File file = new File(Environment.getExternalStorageDirectory(), "AlternatingAttention.pdf");

        try {
            // after creating a file name we will
            // write our PDF file to that location.
            pdfDocument.writeTo(new FileOutputStream(file));

            /*
           // Uri path = Uri.fromFile(file);
            Uri path = FileProvider.getUriForFile(FACompleteScreen.this, BuildConfig.APPLICATION_ID + ".provider", file);

            Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
            pdfIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            pdfIntent.setDataAndType(path, "application/pdf");
            pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try{
                startActivity(pdfIntent);
            }catch(ActivityNotFoundException e){
                Toast.makeText(getApplicationContext(), "No Application available to view PDF", Toast.LENGTH_SHORT).show();
            }

             */

            // below line is to print toast message
            // on completion of PDF generation.
            Toast.makeText(getApplicationContext(), "PDF file generated successfully.", Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            // below line is used
            // to handle error
            e.printStackTrace();
        }
        // after storing our pdf to that
        // location we are closing our PDF file.
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

                /*
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                Uri uri = Uri.fromParts("package", this.getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);

                 */

                // after requesting permissions we are showing
                // users a toast message of permission granted.
                boolean writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if (writeStorage && readStorage) {
                    //Toast.makeText(this, "Permission Granted..", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission Denined.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }

    /**************************************************************************************************/

}
