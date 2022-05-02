package com.anuththara18.attentionassessment.consentform;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anuththara18.attentionassessment.R;
import com.anuththara18.attentionassessment.language.LanguageSetter;

import java.util.ArrayList;
import java.util.List;

public class SinhalaConsentFormActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<ConsentForm> consentFormList;
    private Boolean[] chkArr;
    TextView textView;
    ImageButton next;

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

        textView.setText(LanguageSetter.getresources().getString(R.string.consentForm));

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

        initData();
        initRecyclerView();

        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
        consentFormList.add(new ConsentForm("හැඳින්වීම", "ඔබේ දරුවා පර්යේෂණ අධ්\u200Dයයනයකට යොදාගැනීම සඳහා විමසනු ලැබේ. මෙම පර්යේෂණය ඔබේ දරුවාගේ හැසිරීම් රටා නිරීක්ෂණය කිරීම සඳහා සිදු කරනු ලබන අතර එමගින් අවධානය දුර්වලතා හෝ ඊට සමාන ආබාධ තත්ත්වයන් ගෙන් යුතු දරුවන් සඳහා ස්වයංක්\u200Dරිය රෝග තත්ත්ව පරීක්ෂණ මෙවලමක්/ පද්ධතියක් සංවර්ධනය කරනු/ නිපදවනු ලැබේ. මෙම පර්යේෂණය සඳහා සහභාගී වීමට ඔබට ආරාධනා ලැබුවහොත් ඔබට අධ්\u200Dයයන ක්\u200Dරම පිළිබඳව දැනුවත් වීමට අයිතියක් ඇති අතර එම කරුණු සලකා බලා ඔබට පර්යේෂණය සඳහා සහභාගී වීමට ඇති කැමැත්ත තීරණය කළ හැකිය. මෙම ආකෘති පත්\u200Dරයේ ඔබ නොදන්නා වචන ඇතුලත් විය හැකිය. කරුණාකර ඔබට තේරුම් ගැනීමට අපහසු වචන හෝ තොරතුරු ඇතොත් පර්යේෂකයාගෙන් විමසන්න  \n\n" +
                "ඔබගෙන් විමසීමට නියමිත තොරතුරු පිළිබඳව දැනුවත් වීමට ඔබට අයිතියක් ඇති අතර පර්යේෂණය සඳහා සහභාගී වීම හෝ නොවීම ඔබට තීරණය කළ හැකිය. ඔබගේ සහභාගිත්වය ස්වේච්ඡාවෙන් සිදුවිය යුත්තකි. එය අනිවාර්ය නැත. මෙම අධ්\u200Dයයනය ඔබට ප්\u200Dරතික්ෂේප කළ හැකිය, එය ඔබේ අයිතියකි.  ඔබට මෙම අධ්\u200Dයයනයේ දිගටම යෙදී සිටීම අනවශ්\u200Dය යයි හැඟේ නම් ඔබට කිසිදු බාධාවකින් හෝ ප්\u200Dරතිලාභ අහිමිවිමකින් තොරව ඕනෑම වේලාවක ඉවත් විය හැකිය."));
        consentFormList.add(new ConsentForm("මෙම පර්යේෂණය සිදු කරන්නේ ඇයි? ", "මෙම පර්යේෂණයේ අරමුණ වන්නේ ඔබේ දරුවාගේ හැසිරීම් රටා නිරීක්ෂණය කිරීමටත්, එමගින් තවමත් රෝග ලක්ෂණ හඳුනා නොගත් අවධානය දුර්වලතා ඇති දරුවන්ට උපකාර වන ස්වයංක්\u200Dරීය පරීක්ෂණ මෙවලමක්/ පද්ධතියක් සංවර්ධනය කිරීමයි. අවධානය විශ්ලේෂණය මගින් දරුවාගේ අවධානය නිරීක්ෂණය කරනු ලැබේ."));
        consentFormList.add(new ConsentForm("ඔබට කරන්නට ඇත්තේ කුමක්ද? ", "ඔබගේ දරුවාගේ අවධානයට අදාළ දත්ත ලබා ගැනීමට කැමැත්ත ඔබෙන් විමසනු ලැබේ. මෙම දත්තවල රහස්\u200Dයභාවය සුරැකෙන බවත් එය වෙනත් කිසිදු අයෙකු අතට පත් නොවන අතර ඔබේ දරුවාගේ අනන්\u200Dයතාවය කිසිවිටෙකත් අනාවරණය නොවන බවත් තවදුරටත් දන්වා සිටිමු."));
        consentFormList.add(new ConsentForm("මෙම අධ්\u200Dයයනයේ රැඳී සිටීමෙන් අත්වෙන වාසි", "මෙම තාක්ෂණය පදනම් කරගත් මෙවලම අවධානය දුර්වලතා සඳහා ලංකාවේ ප්\u200Dරථම වරට හඳුන්වා දී සංවර්ධනය කිරීම හේතුවෙන්  ඔබගේ සහභාගිත්වය රටට වැඩදායි වේ. "));
        consentFormList.add(new ConsentForm("මෙම අධ්\u200Dයයනයේ රැඳී සිටීමේ ඇති අවදානම් තත්ත්වයන් ", "මෙම අධ්\u200Dයයනයේ රැඳී සිටීමෙන් ඔබේ දරුවට බලපාන කිසිදු අයුරක අවදානම් තත්ත්වයක් නැත."));
        consentFormList.add(new ConsentForm("මෙම අධ්\u200Dයයනයේ රැඳී සිටීමට ඔබට දැරීමට සිදුවන වියදම්", "ඔබට කිසිදු ආකාරයක වියදමක් දැරීමට සිදු නොවනු ඇත."));
        consentFormList.add(new ConsentForm("විශ්වසනීය භාවය", "එක්රැස් කරගන්නා සියලුම දත්ත ශ්\u200Dරී ලංකා තොරතුරු තාක්ෂණ ආයතනයේ (SLIIT) මේ වෙනුවෙන්ම වෙන් වූ දත්ත ගබඩා කිරීමේ උපකරණයක ආරක්ෂිතව තැන්පත් කර ඇත. එම දත්ත පරිහරණය කළ හැක්කේ පර්යේෂක කණ්ඩායමට පමණි. පර්යේෂක කණ්ඩායම එක්රැස් කරගත් සියලුම දත්ත වල විශ්වසනීය බව හා දරුවාගේ අනන්\u200Dයතාවය කිසිදු අයුරකින් අනාවරණය  නොවන බවටත් සහතික වන අතර මෙම දත්ත පර්යේෂණ කටයුතු සඳහා පමණක් යොදාගන්නා බවටත් පර්යේෂණය අවසානයේ ආරක්\u200Dෂිත ලෙස එම සියලුම දත්ත විනාශ කරන බවටත් සහතික වේ."));
        consentFormList.add(new ConsentForm("මෙම අධ්\u200Dයයනයට සහභාගී වීම සඳහා ඔබට ගෙවීමක් සිදු කරනවාද ?", "මෙම අධ්\u200Dයයනයට සහභාගී වීම සඳහා ඔබට කිසිදු ගෙවීමක් සිදු නොකරයි. "));
        consentFormList.add(new ConsentForm("මෙම පර්යේෂණයට සහභාගී වන්නෙක් වශයෙන් ඔබට ඇති අයිතිවාසිකම්", "මෙම සහභාගිත්වය ස්වේච්ඡාවෙන් සිදුවන්නකි. ඔබේ සහභාගිත්වය අනිවාර්ය නැත. මෙම අධ්\u200Dයයනය මගින් ඔබේ දරුවාගේ යහපත් සෞඛ්\u200Dයය, සුභසිද්ධිය හෝ පර්යේෂණයේ රැඳී සිටීමට ඇති කැමැත්ත ට තුඩුදෙන ඕනෑම අලුත් තොරතුරු පිළිබඳව ඔබට දැනුම්වත් විය හැකිය."));
        consentFormList.add(new ConsentForm("ඔබට යම් කාරණයක්, ගැටළුවක් හෝ පැමිණිල්ලක් ඇත්නම් සම්බන්ධ කරගත යුතු අය", "ඔබට දැනගැනීමට අවශ්\u200Dය යම් කාරණයක්, ගැටළුවක් හෝ පැමිණිල්ලක් ඇත්නම් කරුණාකර ඔබේ පර්යේෂණ සහායකවරයා හෝ පර්යේෂණ කණ්ඩායම සම්බන්ධ කරගන්න."));
        consentFormList.add(new ConsentForm("ඔබට යම් ප්\u200Dරශ්නයක් හෝ ගැටළුවක් ඇත්නම් දුරකථනයෙන් සම්බන්ධ කරගත හැකි අය ", "මෙම පර්යේෂණය සඳහා සහභාගී වීමේදී ඔබේ අයිතිවාසිකම් පිළිබඳව හෝ පර්යේෂණය හා සම්බන්ධ කිසියම් කාරණයක් දැනගැනීමට අවශ්\u200Dය නම් සහ මෙම පර්යේෂණය සමග සම්බන්ධ වීමට හෝ තවදුරටත් කරගෙන යාමට ඔබට යම් අපහසුතාවයක් ඇත්නම් 0723537952 දුරකථන අංකයෙන් හෝ pradeepa.s@sliit.lk විද්\u200Dයුත් තැපැල් ලිපිනයෙන්, ශ්\u200Dරී ලංකා තොරතුරු තාක්ෂණ ආයතනයේ ප්\u200Dරධාන පර්යේෂිකා (principal investigator) ආචාර්ය ප්\u200Dරදීපා සමරසිංහ මහත්මිය සම්බන්ධ කරගැනීමට හැකියාව ඇත. "));
    }

}