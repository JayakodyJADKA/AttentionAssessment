package com.anuththara18.attentionassessment.consentform;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anuththara18.attentionassessment.R;
import com.anuththara18.attentionassessment.details.ParentDetailsActivity;
import com.anuththara18.attentionassessment.home.NavigationDrawerActivity;
import com.anuththara18.attentionassessment.language.LanguageSetter;

import java.util.ArrayList;
import java.util.List;

public class ConsentFormActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<ConsentForm> consentFormList;
    private Boolean[] chkArr;
    TextView textView, next;

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
        setContentView(R.layout.activity_consent_form);

        recyclerView = findViewById(R.id.recyclerView);
        textView = findViewById(R.id.textView);
        next = findViewById(R.id.next);

        if (ParentDetailsActivity.nav == 0) {
            next.setVisibility(View.INVISIBLE);
            next.setEnabled(false);
        }
        else {
            next.setVisibility(View.VISIBLE);
            next.setEnabled(true);
        }

        textView.setText(LanguageSetter.getresources().getString(R.string.consentForm));
        next.setText(LanguageSetter.getresources().getString(R.string.proceed));

        initData();
        initRecyclerView();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GetParentsConsentActivity.class);
                startActivity(intent);
            }
        });


    }

    private void initRecyclerView() {
        ConsentFormAdapter consentFormAdapter = new ConsentFormAdapter(consentFormList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(consentFormAdapter);
    }

    private void initData() {
        consentFormList = new ArrayList<>();
        consentFormList.add(new ConsentForm("Introduction", "Your child is being asked to participate in a research study. This research is being conducted to observe the behaviour of your child in order to develop an automated tool for screening attention impairments in young children. When you are invited to participate in research, you have the right to be informed about the study procedures so that you can decide whether you want to consent to participation. This form may contain words that you do not know. Please ask the researcher to explain any words or information that you do not understand. \n\n" +
                "You have the right to know what you will be asked to do so that you can decide whether or not to be in the study. Your participation is voluntary . Your participation is not mandatory. You may refuse to be in the study, and it is your right. If you do not want to continue to be in the study, you may withdraw at any time without penalty or loss of benefits to which you are otherwise entitled."));
        consentFormList.add(new ConsentForm("Why is this study being done?", "The purpose of this research is to observe the behaviour of your child in order to help children who are not yet diagnosed in order to develop an automated screening tool for children with attention impairments. The child’s attention will be monitored through attention analysis."));
        consentFormList.add(new ConsentForm("What am I being asked to do?", "You will be asked to provide consent in order to collect data related to the attention of your child. Furthermore, your data will be confidential, and it will not be shared with anyone else and your child’s identity will not be revealed at any cost."));
        consentFormList.add(new ConsentForm("What are the benefits of being in the study?", "Your participation will benefit the country since this is the first time a technology-based solution is proposed and developed in Sri Lanka for children with attention impairments."));
        consentFormList.add(new ConsentForm("What are the risks of being in the study?", "There are no risks involved to the child when participating to this research."));
        consentFormList.add(new ConsentForm("What are the costs of being in the study?", "There is no cost to you."));
        consentFormList.add(new ConsentForm("Confidentiality", "The data collected will be stored securely in a dedicated storage device at Sri Lanka Institute of Information Technology. Only the research team has the access to data. The researchers will ensure the confidentiality of the information collected where the child’s identity will not be revealed at any instance and the data will be used only for research purposes and will be destroyed in a secure manner once the research is complete."));
        consentFormList.add(new ConsentForm("Will I be compensated for participating in the study?", "You will receive no payment for taking part in this study."));
        consentFormList.add(new ConsentForm("What are my rights as a participant?", "Participation in this study is voluntary. Your participation is not mandatory. You will also be informed of any new information discovered during the course of this study that might influence your child’s health, welfare, or willingness to be in this study."));
        consentFormList.add(new ConsentForm("Who do I contact if I have questions, concerns, or complaints?", "Please contact the research assistant or the research team if you have questions about the research."));
        consentFormList.add(new ConsentForm("Whom do I call if I have questions or problems?", "If you have any questions regarding your rights as a participant in this research and/or concerns about the study, or if you feel under any pressure to enroll or to continue to participate in this study, you may contact the Dr Pradeepa Samarasinghe, Principle Investigator at Sri Lanka Institute of Information Technology at 0723537952 or pradeepa.s@sliit.lk ."));
    }

}