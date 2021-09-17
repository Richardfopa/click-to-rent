package com.orange.click_2_rent;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.LinkedList;


public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.HistoryListViewHolder> {
    private LinkedList<ClientUtil> client;
    private LayoutInflater mInflater;
    public HistoryListAdapter(Context context, LinkedList<ClientUtil> client){
        mInflater = LayoutInflater.from(context);
       this.client = client;
    }
    @NonNull
    @Override
    public HistoryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =mInflater.inflate(R.layout.historique_client_item, parent, false);

        return new HistoryListViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryListViewHolder holder, int position) {
        ClientUtil clientUtil = client.get(position);
        holder.image.setImageResource(clientUtil.getmImage());
        holder.service_text.setText(clientUtil.getmService());
        holder.note_text.setText(Integer.toString(clientUtil.getmNote()));

    }


    @Override
    public int getItemCount() {
        return client.size();
    }

    public class HistoryListViewHolder extends RecyclerView.ViewHolder {
        public final ImageView image;
        public final TextView service_text;
        public final TextView note_text;
        final HistoryListAdapter mAdapter;

        public HistoryListViewHolder(@NonNull View itemView, HistoryListAdapter adapter) {
            super(itemView);
            this.mAdapter = adapter;
            image = itemView.findViewById(R.id.history_item_image);
            service_text = itemView.findViewById(R.id.history_item_service);
            note_text = itemView.findViewById(R.id.history_item_note);
        }
    }
}
