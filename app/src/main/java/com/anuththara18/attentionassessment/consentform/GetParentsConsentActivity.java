package com.anuththara18.attentionassessment.consentform;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.anuththara18.attentionassessment.R;
import com.anuththara18.attentionassessment.language.LanguageSetter;

import java.io.ByteArrayOutputStream;

public class GetParentsConsentActivity extends AppCompatActivity {

    TextView textView3, next;
    Button btnGetSignature;
    public static ImageView fingerprintImageView;
    static int flag2 = 0;

    private static final int STORAGE_PERMISSION_CODE = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_parents_consent);

        textView3 = (TextView) findViewById(R.id.textView3);
        next = (TextView) findViewById(R.id.next);
        btnGetSignature = (Button)findViewById(R.id.btnGetSignature);
        fingerprintImageView = (ImageView)findViewById(R.id.fingerprintImageView);

        //textView3.setText(LanguageSetter.getresources().getString(R.string.provideConsent));
        //btnSaveFingerPrint.setText(LanguageSetter.getresources().getString(R.string.startTest));
        //btnGetSignature.setText(LanguageSetter.getresources().getString(R.string.getSignature));

        checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);



        /************************************************************************************/

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( flag2 == 1) {
                    saveImage();
                    //Intent intent = new Intent(getApplicationContext(), QuestionnaireMainScreen.class);
                    //startActivity(intent);
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
            /************************************************
             * **********************************************
             * **********************************************
             */

            Toast.makeText(getApplicationContext(), "Consent Saved Successfully!", Toast.LENGTH_SHORT).show();
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

    /************************************************************************************/


    // Function to check and request permission.
    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(com.anuththara18.attentionassessment.consentform.GetParentsConsentActivity.this, permission) == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(com.anuththara18.attentionassessment.consentform.GetParentsConsentActivity.this, new String[] { permission }, requestCode);
        }
        else {
            //Toast.makeText(GetParentsConsentActivity.this, "Permission already granted", Toast.LENGTH_SHORT).show();
        }
    }

    // This function is called when the user accepts or decline the permission.
    // Request Code is used to check which permission called this function.
    // This request code is provided when the user is prompt for permission.

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode,
                permissions,
                grantResults);

        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(com.anuththara18.attentionassessment.consentform.GetParentsConsentActivity.this, "Storage Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(com.anuththara18.attentionassessment.consentform.GetParentsConsentActivity.this, "Storage Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

}