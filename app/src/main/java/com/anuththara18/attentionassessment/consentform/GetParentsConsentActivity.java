package com.anuththara18.attentionassessment.consentform;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.anuththara18.attentionassessment.R;
import com.anuththara18.attentionassessment.home.NavigationDrawerActivity;
import com.anuththara18.attentionassessment.language.LanguageSetter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static android.Manifest.permission.MANAGE_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class GetParentsConsentActivity extends AppCompatActivity {

    TextView textView3, next;
    Button btnGetSignature;
    public static ImageView fingerprintImageView;
    static int flag2 = 0;

    // constant code for runtime permissions
    private static final int PERMISSION_REQUEST_CODE = 200;

    public static ParentsConsentDatabaseHelper sqLiteHelper;
    private static final int STORAGE_PERMISSION_CODE = 101;

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
        setContentView(R.layout.activity_get_parents_consent);

        textView3 = (TextView) findViewById(R.id.textView3);
        next = (TextView) findViewById(R.id.next);
        btnGetSignature = (Button)findViewById(R.id.btnGetSignature);
        fingerprintImageView = (ImageView)findViewById(R.id.fingerprintImageView);

        textView3.setText(LanguageSetter.getresources().getString(R.string.provideconsent));
        next.setText(LanguageSetter.getresources().getString(R.string.proceed));
        btnGetSignature.setText(LanguageSetter.getresources().getString(R.string.getSignature));

        //textView3.setText(LanguageSetter.getresources().getString(R.string.provideConsent));
        //btnSaveFingerPrint.setText(LanguageSetter.getresources().getString(R.string.startTest));
        //btnGetSignature.setText(LanguageSetter.getresources().getString(R.string.getSignature));

        /*******************************************************************************************/

        // below code is used for
        // checking our permissions.
        if (checkPermission()) {
            //Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        } else {
            requestPermission();
        }

        /*******************************************************************************************/

        sqLiteHelper = new ParentsConsentDatabaseHelper(this, "ParentsConsent.sqlite", null, 1);

        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS ParentsConsent(signature BLOB)");

        /************************************************************************************/

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( flag2 == 1) {
                    saveImage();
                }
                else if ( flag2 == 0 ){
                    Toast.makeText(getApplicationContext(), "Please Provide your Signature", Toast.LENGTH_SHORT).show();
                }

            }
        });

        /************************************************************************************/

        btnGetSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GetSignatureActivity.class);
                startActivity(intent);
                flag2 = 1;
            }
        });

    }

    /************************************************************************************/

    public void saveImage() {

        try{
            sqLiteHelper.insertData(imageViewToByte(fingerprintImageView));
            Toast.makeText(getApplicationContext(), "Consent Saved Successfully!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
            startActivity(intent);
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Consent not Saved!", Toast.LENGTH_SHORT).show();

            e.printStackTrace();
        }

    }

    /************************************************************************************/

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
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