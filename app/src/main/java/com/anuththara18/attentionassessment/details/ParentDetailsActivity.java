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

    TextView name, email, contact, next, previous;
    EditText childName, parentEmail, parentContact;

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

        childName = findViewById(R.id.childName);
        parentEmail  = findViewById(R.id.parentEmail);
        parentContact  = findViewById(R.id.parentContact);

        next.setText(LanguageSetter.getresources().getString(R.string.next));
        previous.setText(LanguageSetter.getresources().getString(R.string.previous));
        name.setText(LanguageSetter.getresources().getString(R.string.childName));
        email.setText(LanguageSetter.getresources().getString(R.string.parentEmail));
        contact.setText(LanguageSetter.getresources().getString(R.string.parentContact));

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