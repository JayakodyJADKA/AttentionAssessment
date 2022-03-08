package com.anuththara18.attentionassessment.emailfiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import com.anuththara18.attentionassessment.R;
import com.anuththara18.attentionassessment.language.LanguageSetter;

import java.io.File;
import java.util.ArrayList;

public class EmailDataFilesActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_data_files);

        Context context = getApplicationContext(); ;

        button = findViewById(R.id.button);

        button.setText(LanguageSetter.getresources().getString(R.string.send));

        String csv = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/SelectiveAttention.csv");
        String pdf = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/SelectiveAttention.pdf");
        String csv1 = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/AlternatingAttention.csv");
        String pdf1 = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/AlternatingAttention.pdf");
        String csv2 = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/DividedAttention.csv");
        String pdf2 = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/DividedAttention.pdf");
        String csv3 = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/FocusedAttention.csv");
        String pdf3 = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/FocusedAttention.pdf");
        String csv4 = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/SustainedAttention.csv");
        String pdf4 = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/SustainedAttention.pdf");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"it19114040@my.sliit.lk"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Attention Assessment Data");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "body text");

                File file = new File(csv);
                File file1 = new File(pdf);
                File file2 = new File(csv1);
                File file3 = new File(pdf1);
                File file4 = new File(csv2);
                File file5 = new File(pdf2);
                File file6 = new File(csv3);
                File file7 = new File(pdf3);
                File file8 = new File(csv4);
                File file9 = new File(pdf4);

                Uri uri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", file);
                Uri uri1 = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", file1);
                Uri uri2 = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", file2);
                Uri uri3 = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", file3);
                Uri uri4 = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", file4);
                Uri uri5 = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", file5);
                Uri uri6 = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", file6);
                Uri uri7 = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", file7);
                Uri uri8 = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", file8);
                Uri uri9 = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", file9);

                ArrayList<Uri> uris = new ArrayList<Uri>();

                uris.add(uri);
                uris.add(uri1);
                uris.add(uri2);
                uris.add(uri3);
                uris.add(uri4);
                uris.add(uri5);
                uris.add(uri6);
                uris.add(uri7);
                uris.add(uri8);
                uris.add(uri9);

                emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);

                //startActivityForResult(Intent.createChooser(emailIntent, "Sending multiple attachment"), 12345);
                //emailIntent.putExtra(Intent.EXTRA_STREAM, uri);

                startActivity(Intent.createChooser(emailIntent, "Pick an Email provider"));
            }
        });
    }
}