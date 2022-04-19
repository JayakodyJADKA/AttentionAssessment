package com.anuththara18.attentionassessment.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.anuththara18.attentionassessment.R;
import com.anuththara18.attentionassessment.alternating.AlternatingAttentionGame1;
import com.anuththara18.attentionassessment.dividedattention.DividedAttentionGame1;
import com.anuththara18.attentionassessment.focused.FocusedAttentionGame1;
import com.anuththara18.attentionassessment.map.Map1Activity;
import com.anuththara18.attentionassessment.map.Map2Activity;
import com.anuththara18.attentionassessment.sustained.BirdChoosingActivity;
import com.anuththara18.attentionassessment.videos.IntroductoryVideoLandscapeActivity;
import com.anuththara18.attentionassessment.videos.IntroductoryVideoPortraitActivity;

@SuppressLint("ValidFragment")

public class MainFragment extends Fragment {

    public static String game = " ";

    CardView focusedAttention, selectiveAttention, dividedAttention, sustainedAttention, alternatingAttention, alternatingAttention2;

    public static com.anuththara18.attentionassessment.home.MainFragment newInstance() {
        return (new com.anuththara18.attentionassessment.home.MainFragment());
    }

    public MainFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        focusedAttention = view.findViewById(R.id.focusedAttention);
        selectiveAttention = view.findViewById(R.id.selectiveAttention);
        dividedAttention = view.findViewById(R.id.dividedAttention);
        sustainedAttention = view.findViewById(R.id.sustainedAttention);
        //alternatingAttention = view.findViewById(R.id.alternatingAttention);
        alternatingAttention2 = view.findViewById(R.id.alternatingAttention2);

        final MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.button1);

        focusedAttention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                game = "focused";
                Intent intent = new Intent(getContext(), Map1Activity.class);
                startActivity(intent);
            }
        });

        sustainedAttention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                game = "sustained";
                Intent intent = new Intent(getContext(), IntroductoryVideoPortraitActivity.class);
                startActivity(intent);
            }
        });

        selectiveAttention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                game = "selective";
                Intent intent = new Intent(getContext(), Map2Activity.class);
                startActivity(intent);
            }
        });

        dividedAttention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                game = "divided";
                Intent intent = new Intent(getContext(), IntroductoryVideoPortraitActivity.class);
                startActivity(intent);
            }
        });

        alternatingAttention2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                game = "alternating";
                Intent intent = new Intent(getContext(), IntroductoryVideoLandscapeActivity.class);
                startActivity(intent);
            }
        });

        return view;

    }


}
/*
    public class FirstFragment extends Fragment {
        TextView textView;
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment, container, false);
            textView = view.findViewById(R.id.text);
            textView.setText("first");
            return view;
        }
    }*/

