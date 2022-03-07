package com.anuththara18.attentionassessment.details;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anuththara18.attentionassessment.R;
import com.anuththara18.attentionassessment.age.AgeActivity;
import com.anuththara18.attentionassessment.gender.GenderActivity;
import com.anuththara18.attentionassessment.language.LanguageActivity;
import com.anuththara18.attentionassessment.language.LanguageSetter;

public class ParentDetailsActivity extends AppCompatActivity {

    TextView name, email, contact, next, previous, opt1, opt2, opt3;
    EditText childName, parentEmail, parentContact;
    public static String child_name, parent_email, parent_contact;

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

        childName = findViewById(R.id.childName);
        parentEmail  = findViewById(R.id.parentEmail);
        parentContact  = findViewById(R.id.parentContact);

        next.setText(LanguageSetter.getresources().getString(R.string.next));
        previous.setText(LanguageSetter.getresources().getString(R.string.previous));
        name.setText(LanguageSetter.getresources().getString(R.string.childName));
        email.setText(LanguageSetter.getresources().getString(R.string.parentEmail));
        contact.setText(LanguageSetter.getresources().getString(R.string.parentContact));
        opt1.setText(LanguageSetter.getresources().getString(R.string.optional));
        opt2.setText(LanguageSetter.getresources().getString(R.string.optional));
        opt3.setText(LanguageSetter.getresources().getString(R.string.optional));

        child_name = childName.getText().toString();
        parent_email = parentEmail.getText().toString();
        parent_contact = parentContact.getText().toString();

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParentDetailsActivity.this, LanguageActivity.class);
                startActivity(intent);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( name == null ) {
                    Toast.makeText(getApplicationContext(), "Please select a language", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(ParentDetailsActivity.this, GenderActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}