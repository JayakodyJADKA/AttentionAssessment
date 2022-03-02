package com.anuththara18.attentionassessment.consentform;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.anuththara18.attentionassessment.R;

import java.util.List;

public class ConsentFormAdapter extends RecyclerView.Adapter<ConsentFormAdapter.ConsentFormVH> {

    private static final String TAG = "ConsentFormAdapter";
    List<ConsentForm> consentFormList;

    /**********************************************************************************************************/

    public ConsentFormAdapter(List<ConsentForm> consentFormList) {
        this.consentFormList = consentFormList;
    }

    /**********************************************************************************************************/

    @NonNull
    @Override
    public ConsentFormVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.consent_form_data_rows, parent, false);
        return new ConsentFormVH(view);
    }

    /**********************************************************************************************************/

    @Override
    public void onBindViewHolder(@NonNull ConsentFormVH holder, int position) {
        ConsentForm movie = consentFormList.get(position);
        holder.titleTextView.setText(movie.getTitle());
        holder.descriptions.setText(movie.getDescription());


        boolean isExpanded = consentFormList.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

    }

    /**********************************************************************************************************/

    @Override
    public int getItemCount() {
        return consentFormList.size();
    }

    /**********************************************************************************************************/

    class ConsentFormVH extends RecyclerView.ViewHolder {

        private static final String TAG = "ConsentFormVH";

        ConstraintLayout expandableLayout;
        TextView titleTextView, descriptions;

        public ConsentFormVH(@NonNull final View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptions = itemView.findViewById(R.id.descriptions);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);

            titleTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ConsentForm consentForm = consentFormList.get(getAdapterPosition());
                    consentForm.setExpanded(!consentForm.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }

}
