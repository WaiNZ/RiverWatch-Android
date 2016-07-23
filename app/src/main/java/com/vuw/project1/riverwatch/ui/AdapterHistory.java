package com.vuw.project1.riverwatch.ui;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vuw.project1.riverwatch.R;
import com.vuw.project1.riverwatch.objects.Incident_Object;

import java.util.ArrayList;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.ShowViewHolder> {
    private Context mContext;
    private ArrayList<Incident_Object> mContent;
    private Callback mCallback;
    public AdapterHistory(Context mContext, ArrayList<Incident_Object> mContent, Callback mCallback) {
        this.mContext = mContext;
        this.mContent = mContent;
        this.mCallback = mCallback;
    }
    @Override
    public int getItemCount() {
        return mContent.size();
    }
    @Override
    public void onBindViewHolder(final ShowViewHolder viewHolder, int position) {
        final Incident_Object obj = mContent.get(position);
        viewHolder.title.setText(obj.name);
        viewHolder.message.setText(obj.description);
        Glide.with(mContext)
                .load(Uri.parse(obj.image))
                .placeholder(null)
                .crossFade()
                .centerCrop()
                .into(viewHolder.image);
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.open(obj);
            }
        });
    }
    @Override
    public ShowViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.element_history, viewGroup, false);
        return new AdapterHistory.ShowViewHolder(itemView);
    }
    class ShowViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView title;
        TextView message;
        ImageView image;
        ShowViewHolder(View v) {
            super(v);
            view = v;
            title = (TextView) v.findViewById(R.id.title);
            message = (TextView) v.findViewById(R.id.message);
            image = (ImageView) v.findViewById(R.id.image);
        }
    }
    interface Callback {
        void open(Incident_Object obj);
    }
}