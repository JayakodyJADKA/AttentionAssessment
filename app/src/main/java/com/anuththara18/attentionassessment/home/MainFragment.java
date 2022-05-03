package com.anuththara18.attentionassessment.home;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
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


    /*************************************************************************************************/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                //Toast.makeText(getContext(), "predescnjdfcfd", Toast.LENGTH_SHORT).show();
                alert();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        // The callback can be enabled or disabled here or in handleOnBackPressed()
    }

    /*************************************************************************************************/

    private void alert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setMessage("Do you really want to quit the game?");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        getActivity().finish();
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


    /*************************************************************************************************/



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

