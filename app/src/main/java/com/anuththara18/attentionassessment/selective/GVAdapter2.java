package com.anuththara18.attentionassessment.selective;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.anuththara18.attentionassessment.R;

import java.util.ArrayList;

public class GVAdapter2 extends ArrayAdapter<GridModel> {
    public GVAdapter2(@NonNull Context context, ArrayList<GridModel> gridModelArrayList) {
        super(context, 0, gridModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listitemView = convertView;

        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.

            if (SelectiveAttentionGame1.columns == 3) {
                listitemView = LayoutInflater.from(getContext()).inflate(R.layout.card_item, parent, false);
            }
            else if (SelectiveAttentionGame1.columns == 4) {
                listitemView = LayoutInflater.from(getContext()).inflate(R.layout.card_item2, parent, false);
            }
            else if (SelectiveAttentionGame1.columns == 5) {
                listitemView = LayoutInflater.from(getContext()).inflate(R.layout.card_item3, parent, false);
            }

            //listitemView = LayoutInflater.from(getContext()).inflate(R.layout.card_item3, parent, false);

            /*
            for ( int i = 0; i < SelectiveAttentionGame1.missedResponses.size(); i++ ) {
                if (position == SelectiveAttentionGame1.missedResponses.get(i)) {
                    if (SelectiveAttentionGame1.columns == 3) {
                        listitemView = LayoutInflater.from(getContext()).inflate(R.layout.yellow_card1, parent, false);
                    }
                    else if (SelectiveAttentionGame1.columns == 4) {
                        listitemView = LayoutInflater.from(getContext()).inflate(R.layout.yellow_card2, parent, false);
                    }
                    else if (SelectiveAttentionGame1.columns == 5) {
                        listitemView = LayoutInflater.from(getContext()).inflate(R.layout.yellow_card, parent, false);
                    }
                }
            }
             */

            for ( int i = 0; i < SelectiveAttentionGame1.correctResponses.size(); i++ ) {
                if (position == SelectiveAttentionGame1.correctResponses.get(i)) {
                    if (SelectiveAttentionGame1.columns == 3) {
                        listitemView = LayoutInflater.from(getContext()).inflate(R.layout.green_card1, parent, false);
                    }
                    else if (SelectiveAttentionGame1.columns == 4) {
                        listitemView = LayoutInflater.from(getContext()).inflate(R.layout.green_card2, parent, false);
                    }
                    else if (SelectiveAttentionGame1.columns == 5) {
                        listitemView = LayoutInflater.from(getContext()).inflate(R.layout.green_card, parent, false);
                    }
                }
            }

            for ( int i = 0; i < SelectiveAttentionGame1.incorrectResponses.size(); i++ ) {
                if (position == SelectiveAttentionGame1.incorrectResponses.get(i)) {
                    if (SelectiveAttentionGame1.columns == 3) {
                        listitemView = LayoutInflater.from(getContext()).inflate(R.layout.red_card1, parent, false);
                    }
                    else if (SelectiveAttentionGame1.columns == 4) {
                        listitemView = LayoutInflater.from(getContext()).inflate(R.layout.red_card2, parent, false);
                    }
                    else if (SelectiveAttentionGame1.columns == 5) {
                        listitemView = LayoutInflater.from(getContext()).inflate(R.layout.red_card, parent, false);
                    }
                }
            }



        }

        GridModel gridModel = getItem(position);
        ImageView gridImage = listitemView.findViewById(R.id.gridImage);
        gridImage.setImageResource(gridModel.getImgid());

        /*
        for ( int i = 0; i < SelectiveAttentionGame1.missedResponses.size(); i++ ) {
            if (position == SelectiveAttentionGame1.missedResponses.get(i)) {
                gridImage.setImageResource(R.drawable.exclamation);
            }
        }
         */

        for ( int i = 0; i < SelectiveAttentionGame1.correctResponses.size(); i++ ) {
            if (position == SelectiveAttentionGame1.correctResponses.get(i)) {
                gridImage.setImageResource(R.drawable.tick);
            }
        }

        for ( int i = 0; i < SelectiveAttentionGame1.incorrectResponses.size(); i++ ) {
            if (position == SelectiveAttentionGame1.incorrectResponses.get(i)) {
                gridImage.setImageResource(R.drawable.cross);
            }
        }


        return listitemView;

    }
}