package com.anuththara18.attentionassessment.details;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.anuththara18.attentionassessment.R;
import com.anuththara18.attentionassessment.age.AgeActivity;
import com.anuththara18.attentionassessment.consentform.ConsentFormActivity;
import com.anuththara18.attentionassessment.consentform.SinhalaConsentFormActivity;
import com.anuththara18.attentionassessment.gender.GenderActivity;
import com.anuththara18.attentionassessment.language.LanguageActivity;
import com.anuththara18.attentionassessment.language.LanguageSetter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParentDetailsActivity extends AppCompatActivity {

    TextView name, email, contact, opt1, opt2, opt3, req, diag;
    EditText childName, parentEmail, parentContact;
    public static String child_name, parent_email, parent_contact, diagnosis;
    RadioGroup radioGroup;
    RadioButton genderradioButton;
    ImageButton next, previous;

    public static int nav = 1;

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
        setContentView(R.layout.activity_parent_details);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        contact = findViewById(R.id.contact);
        next = findViewById(R.id.next);
        previous = findViewById(R.id.previous);
        opt1 = findViewById(R.id.opt1);
        opt2 = findViewById(R.id.opt2);
        opt3 = findViewById(R.id.opt3);
        req = findViewById(R.id.req);
        diag = findViewById(R.id.diag);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);

        childName = findViewById(R.id.childName);
        parentEmail  = findViewById(R.id.parentEmail);
        parentContact  = findViewById(R.id.parentContact);

        name.setText(LanguageSetter.getresources().getString(R.string.childName));
        email.setText(LanguageSetter.getresources().getString(R.string.parentEmail));
        contact.setText(LanguageSetter.getresources().getString(R.string.parentContact));
        opt1.setText(LanguageSetter.getresources().getString(R.string.optional));
        opt2.setText(LanguageSetter.getresources().getString(R.string.optional));
        opt3.setText(LanguageSetter.getresources().getString(R.string.optional));
        req.setText(LanguageSetter.getresources().getString(R.string.req));
        diag.setText(LanguageSetter.getresources().getString(R.string.diagnosis));

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Animation animZoomOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out);
                        next.startAnimation(animZoomOut);
                        handler.postDelayed(this, 750);

                    }
                });
            }
        }, 0);

        child_name = childName.getText().toString();
        parent_email = parentEmail.getText().toString();
        parent_contact = parentContact.getText().toString();

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParentDetailsActivity.this, AgeActivity.class);
                startActivity(intent);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                genderradioButton = (RadioButton) findViewById(selectedId);
               if (!isEmail(parentEmail)) {
                    Toast.makeText(ParentDetailsActivity.this, LanguageSetter.getresources().getString(R.string.email), Toast.LENGTH_SHORT).show();
                    parentEmail.setError(LanguageSetter.getresources().getString(R.string.email));
                }
                else if (!isPhone(parentContact)) {
                    Toast.makeText(ParentDetailsActivity.this, LanguageSetter.getresources().getString(R.string.contact), Toast.LENGTH_SHORT).show();
                    parentContact.setError(LanguageSetter.getresources().getString(R.string.contact));
                }
                else if(selectedId == -1){
                    Toast.makeText(ParentDetailsActivity.this,LanguageSetter.getresources().getString(R.string.empty), Toast.LENGTH_SHORT).show();
                }
                else{
                    diagnosis = (String) genderradioButton.getText();
                    //Toast.makeText(ParentDetailsActivity.this,genderradioButton.getText(), Toast.LENGTH_SHORT).show();
                   nav = 1;
                   if (LanguageActivity.text.equals("English")) {
                       finish();
                       startActivity(new Intent(ParentDetailsActivity.this, ConsentFormActivity.class));
                   } else {
                       finish();
                       startActivity(new Intent(ParentDetailsActivity.this, SinhalaConsentFormActivity.class));
                   }
                }
            }
        });
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (TextUtils.isEmpty(email) || Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isPhone(EditText text) {
        CharSequence phone = text.getText().toString();
        Pattern p = Pattern.compile("(07/91)?[0-9]{10}");
        Matcher m = p.matcher(phone);
        return TextUtils.isEmpty(phone) || (m.find() && m.group().equals(phone));
    }

    /*************************************************************************************************/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        alert();
    }

    /*************************************************************************************************/

    private void alert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Do you really want to quit the game?");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
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

}