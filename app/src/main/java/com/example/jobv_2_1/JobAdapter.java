package com.example.jobv_2_1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {

    private Context mContext;
    private List<JobItem> jobItems;

    public JobAdapter(Context context, List<JobItem> jobItemArrayList){
        jobItems = jobItemArrayList;
        mContext = context;
        Log.d("Testing", "JobAdapter: ");
    }



    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("Testing", "onCreateViewHolder: ");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_item, parent,false);

        return new JobViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        Log.d("Testing", "onBindViewHolder: ");
        JobItem currentItem = jobItems.get(position);


        String glogo = currentItem.getLogo();
        String gbewerber = currentItem.getBewerber_Kontakt();
        String gbundesland = currentItem.getBundesland();
        String geinsatzort = currentItem.getEinsatzort();
        String gaktivbis = currentItem.getStelle_Aktiv_bis();


        holder.mBewerber_Kontakt.setText(gbewerber);
        holder.mBundesland.setText(gbundesland);
        holder.mEinsatzort.setText(geinsatzort);
        holder.mStelle_Aktiv_bis.setText(gaktivbis);

        Picasso.get().load(glogo).into(holder.mlogo);
    }

    @Override
    public int getItemCount() {

        Log.d("Testing", "getItemCount: ");
        return jobItems.size();
    }

    public static class JobViewHolder extends RecyclerView.ViewHolder{

        public ImageView mlogo;
        public TextView mBewerber_Kontakt;
        public TextView mBundesland;
        public TextView mEinsatzort;
        public TextView mStelle_Aktiv_bis;
        public RelativeLayout relativeLayout;
//        public TextView mAnscheiben_zur_Stelle;
//        public TextView mBezeichnung_der_Stelle;
//        public TextView mE_Mail;
//        public TextView mstreet;
//        public TextView manspreshpartner;
//        public TextView mumzeit;
//        public TextView mabteilung;


        public JobViewHolder(@NonNull View itemView) {
            super(itemView);

            Log.d("Testing", "JobViewHolder: ");
            mlogo = (ImageView) itemView.findViewById(R.id.job_logo);
            mBewerber_Kontakt = (TextView) itemView.findViewById(R.id.bewerber);
            mBundesland = (TextView) itemView.findViewById(R.id.bundesland);
            mEinsatzort = (TextView)  itemView.findViewById(R.id.einsatzort);
            mStelle_Aktiv_bis = (TextView) itemView.findViewById(R.id.activbis);
            relativeLayout = itemView.findViewById(R.id.recycle_view);

        }
    }
}
