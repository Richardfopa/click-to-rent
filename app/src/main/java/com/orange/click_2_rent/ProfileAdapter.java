package com.orange.click_2_rent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProfileAdapter extends ArrayAdapter<PersonProfile> {
    private Context mContext;
    private int mIcone;
    private ArrayList<PersonProfile> mPersonProfiles;
        public ProfileAdapter(@NonNull Context context, int icone, @NonNull ArrayList<PersonProfile> personProfiles) {
        super(context, icone, personProfiles);
        mContext = context;
        mIcone = icone;
        mPersonProfiles = personProfiles;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup container) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mIcone, container, false);
            ImageView icon = convertView.findViewById(R.id.item_icon);
            TextView description = convertView.findViewById(R.id.item_description);
            icon.setImageResource(getItem(position).getIcone());
            description.setText(getItem(position).getDescription());
        return convertView;
    }


}
